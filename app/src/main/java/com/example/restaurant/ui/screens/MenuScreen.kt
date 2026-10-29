package com.example.restaurant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurant.R
import com.example.restaurant.data.MenuCategory
import com.example.restaurant.data.MenuItem
import com.example.restaurant.data.RestaurantData
import com.example.restaurant.ui.components.ConceptBanner
import com.example.restaurant.ui.components.DishCard
import com.example.restaurant.ui.components.DishDetailDialog
import com.example.restaurant.ui.theme.*

@Composable
fun MenuScreen(
    onNavigateToVisit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(MenuCategory.ALL) }
    var vegOnly by remember { mutableStateOf(false) }
    var selectedDish by remember { mutableStateOf<MenuItem?>(null) }

    val filteredDishes = remember(searchQuery, selectedCategory, vegOnly) {
        val q = searchQuery.trim().lowercase()
        RestaurantData.menuItems.filter { item ->
            val matchesCategory = selectedCategory == MenuCategory.ALL || item.category == selectedCategory
            val matchesQuery = q.isEmpty() ||
                    item.name.lowercase().contains(q) ||
                    item.description.lowercase().contains(q)
            val matchesVeg = !vegOnly || item.veg
            matchesCategory && matchesQuery && matchesVeg
        }
    }

    val hasActiveFilter = searchQuery.isNotEmpty() || selectedCategory != MenuCategory.ALL || vegOnly

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("menu_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            ConceptBanner()
        }

        // Hero Header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.biryani),
                    contentDescription = "Biryani",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    DarkBackground.copy(alpha = 0.6f),
                                    DarkBackground
                                )
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "THE MENU",
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Cooked over coal, copper and time.",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextLight
                    )
                    Text(
                        text = "Prices are demonstration values in Indian rupees.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
        }

        // Search & Filter Controls
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Search Input
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("menu_search_input"),
                    placeholder = { Text("Search dishes or ingredients…", color = TextMuted, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TextMuted
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear search",
                                    tint = TextMuted
                                )
                            }
                        }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkSurface,
                        unfocusedContainerColor = DarkSurface,
                        focusedBorderColor = EmberGold,
                        unfocusedBorderColor = DarkBorder,
                        focusedTextColor = TextLight,
                        unfocusedTextColor = TextLight
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                // Category Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MenuCategory.values().forEach { category ->
                        val isSelected = selectedCategory == category
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = category },
                            label = {
                                Text(
                                    text = category.displayName.uppercase(),
                                    fontSize = 11.sp,
                                    letterSpacing = 1.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = DarkSurface,
                                labelColor = TextMuted,
                                selectedContainerColor = EmberGold,
                                selectedLabelColor = DarkBackground
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = if (isSelected) EmberGold else DarkBorder
                            ),
                            shape = RoundedCornerShape(4.dp)
                        )
                    }
                }

                // Veg filter and result count row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = vegOnly,
                            onClick = { vegOnly = !vegOnly },
                            label = { Text("Veg Only", fontSize = 12.sp) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Eco,
                                    contentDescription = null,
                                    tint = if (vegOnly) DarkBackground else VegGreen,
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = DarkSurface,
                                labelColor = TextMuted,
                                selectedContainerColor = VegGreen,
                                selectedLabelColor = DarkBackground
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = vegOnly,
                                borderColor = if (vegOnly) VegGreen else DarkBorder
                            )
                        )

                        Text(
                            text = "${filteredDishes.size} ${if (filteredDishes.size == 1) "dish" else "dishes"}",
                            fontSize = 13.sp,
                            color = TextMuted
                        )
                    }

                    if (hasActiveFilter) {
                        TextButton(
                            onClick = {
                                searchQuery = ""
                                selectedCategory = MenuCategory.ALL
                                vegOnly = false
                            }
                        ) {
                            Text("Reset filters", color = EmberGold, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // Dish Items or Empty state
        if (filteredDishes.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    border = CardDefaults.outlinedCardBorder().copy(
                        brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "No dishes match.",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextLight
                        )
                        Text(
                            text = "Try another keyword or change your category filter.",
                            color = TextMuted,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                searchQuery = ""
                                selectedCategory = MenuCategory.ALL
                                vegOnly = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EmberGold,
                                contentColor = DarkBackground
                            ),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text("Show all dishes", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            items(filteredDishes) { dish ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    DishCard(
                        item = dish,
                        onClick = { selectedDish = dish }
                    )
                }
            }
        }
    }

    selectedDish?.let { dish ->
        DishDetailDialog(
            item = dish,
            onDismiss = { selectedDish = null },
            onEnquire = {
                selectedDish = null
                onNavigateToVisit()
            }
        )
    }
}
