package com.example.restaurant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.restaurant.data.MenuItem
import com.example.restaurant.data.RestaurantData
import com.example.restaurant.ui.components.*
import com.example.restaurant.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToMenu: () -> Unit,
    onNavigateToVisit: () -> Unit,
    onNavigateToStory: () -> Unit,
    onNavigateToGallery: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedDish by remember { mutableStateOf<MenuItem?>(null) }
    val featuredDishes = remember { RestaurantData.menuItems.filter { it.featured }.take(4) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Concept disclaimer
        item {
            ConceptBanner()
        }

        // Hero Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hero),
                    contentDescription = "Indian feast with butter chicken and biryani beside flames",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    DarkBackground.copy(alpha = 0.85f),
                                    DarkBackground
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "TANDOOR · DUM · TADKA",
                        fontSize = 11.sp,
                        letterSpacing = 2.5.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "A Taste of India,\nCrafted with Fire.",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextLight,
                        lineHeight = 36.sp
                    )
                    Text(
                        text = "Regional recipes cooked over charcoal and copper — from Awadhi biryanis to smoky Rajasthani curries.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onNavigateToMenu,
                            modifier = Modifier.testTag("hero_explore_menu"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EmberGold,
                                contentColor = DarkBackground
                            ),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text("Explore Menu", fontWeight = FontWeight.Bold)
                        }
                        OutlinedButton(
                            onClick = onNavigateToVisit,
                            modifier = Modifier.testTag("hero_plan_visit"),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(TextLight.copy(alpha = 0.5f))
                            ),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text("Plan Visit", color = TextLight)
                        }
                    }
                }
            }
        }

        // Our Kitchen Spotlight
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.spices),
                        contentDescription = "Whole Indian spices in brass bowls",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "OUR KITCHEN",
                            fontSize = 11.sp,
                            letterSpacing = 2.sp,
                            color = EmberGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Spices ground each morning.\nFires lit by noon.",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextLight
                        )
                        Text(
                            text = "Ember & Spice imagines a kitchen where every masala is roasted and pounded in-house, breads are slapped against a clay tandoor to order, and biryanis are sealed with dough and cooked slowly on dum.",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted,
                            lineHeight = 20.sp
                        )
                        TextButton(
                            onClick = onNavigateToStory,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text("Read our story →", color = EmberGold, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Signatures Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                SectionHeading(
                    eyebrow = "Signatures",
                    title = "Dishes worth the journey",
                    intro = "Handcrafted regional specialties slow-cooked to perfection."
                )
            }
        }

        // Featured Dishes List
        items(featuredDishes.size) { index ->
            val dish = featuredDishes[index]
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                DishCard(
                    item = dish,
                    onClick = { selectedDish = dish }
                )
            }
        }

        // View Full Menu Button
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                OutlinedButton(
                    onClick = onNavigateToMenu,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(EmberGold)
                    )
                ) {
                    Text("View Full Menu (${RestaurantData.menuItems.size} Dishes)", color = EmberGold)
                }
            }
        }

        // The Experience Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.interior),
                        contentDescription = "Lantern-lit dining room",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "THE EXPERIENCE",
                            fontSize = 11.sp,
                            letterSpacing = 2.sp,
                            color = EmberGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Lantern light, brass and a long, slow dinner.",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextLight
                        )
                        Text(
                            text = "Seating for intimate dinners, family tables and private celebrations of up to forty guests.",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted,
                            lineHeight = 20.sp
                        )
                        TextButton(
                            onClick = onNavigateToGallery,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text("Explore photo gallery →", color = EmberGold, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Opening Hours section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SectionHeading(
                    eyebrow = "Visit",
                    title = "Open six days a week",
                    intro = "Walk-ins welcome; enquiries recommended for weekends and groups."
                )
                OpeningHoursList()
            }
        }

        // CTA Band
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                CtaBand(
                    onPlanVisitClick = onNavigateToVisit,
                    onViewMenuClick = onNavigateToMenu
                )
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
