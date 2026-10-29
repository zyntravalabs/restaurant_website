package com.example.restaurant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.restaurant.R
import com.example.restaurant.data.GalleryItem
import com.example.restaurant.data.RestaurantData
import com.example.restaurant.ui.components.ConceptBanner
import com.example.restaurant.ui.theme.*

@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("All") }
    var activeViewerIndex by remember { mutableStateOf<Int?>(null) }
    val categories = listOf("All", "Dishes", "Kitchen", "Interior")

    val filteredItems = remember(selectedCategory) {
        if (selectedCategory == "All") {
            RestaurantData.galleryItems
        } else {
            RestaurantData.galleryItems.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("gallery_screen"),
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
                    painter = painterResource(id = R.drawable.spices),
                    contentDescription = "Spices",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    DarkBackground.copy(alpha = 0.5f),
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
                        text = "GALLERY",
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Smoke, brass and saffron.",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextLight
                    )
                    Text(
                        text = "A look inside the kitchen and dining room.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
        }

        // Category Filter Chips
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { cat ->
                    val isSelected = selectedCategory == cat
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategory = cat },
                        label = {
                            Text(
                                text = cat.uppercase(),
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
        }

        // Photo Grid Cards
        itemsIndexed(filteredItems) { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { activeViewerIndex = index }
                    .testTag("gallery_item_$index"),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                )
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(if (item.wide) 220.dp else 180.dp)
                    ) {
                        Image(
                            painter = painterResource(id = item.drawableRes),
                            contentDescription = item.alt,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Surface(
                            color = DarkBackground.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(topStart = 4.dp),
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            Text(
                                text = item.category.uppercase(),
                                fontSize = 10.sp,
                                letterSpacing = 1.sp,
                                color = EmberGold,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                    Text(
                        text = item.alt,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextLight,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }

    // Interactive Photo Lightbox Dialog
    activeViewerIndex?.let { currentIndex ->
        val currentPhoto = filteredItems.getOrNull(currentIndex)
        if (currentPhoto != null) {
            Dialog(
                onDismissRequest = { activeViewerIndex = null },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBackground.copy(alpha = 0.95f))
                        .padding(16.dp)
                ) {
                    // Close button
                    IconButton(
                        onClick = { activeViewerIndex = null },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(DarkSurfaceVariant.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close photo viewer",
                            tint = TextLight
                        )
                    }

                    // Photo and caption
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = currentPhoto.drawableRes),
                            contentDescription = currentPhoto.alt,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 420.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Text(
                            text = currentPhoto.alt,
                            color = TextLight,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Text(
                            text = "${currentIndex + 1} of ${filteredItems.size}",
                            color = EmberGold,
                            fontSize = 12.sp,
                            letterSpacing = 1.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Navigation arrows
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FilledIconButton(
                                onClick = {
                                    val newIdx = if (currentIndex > 0) currentIndex - 1 else filteredItems.size - 1
                                    activeViewerIndex = newIdx
                                },
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = DarkSurfaceVariant,
                                    contentColor = TextLight
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Previous photo"
                                )
                            }

                            FilledIconButton(
                                onClick = {
                                    val newIdx = (currentIndex + 1) % filteredItems.size
                                    activeViewerIndex = newIdx
                                },
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = DarkSurfaceVariant,
                                    contentColor = TextLight
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Next photo"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
