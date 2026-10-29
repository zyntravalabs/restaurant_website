package com.example.restaurant.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.restaurant.data.RestaurantData
import com.example.restaurant.ui.components.ConceptBanner
import com.example.restaurant.ui.components.CtaBand
import com.example.restaurant.ui.theme.*

@Composable
fun StoryScreen(
    onNavigateToMenu: () -> Unit,
    onNavigateToVisit: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("story_screen"),
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
                    .height(240.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tandoor),
                    contentDescription = "Clay tandoor with skewers",
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
                        text = "OUR STORY",
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "A kitchen built around the fire.",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextLight
                    )
                    Text(
                        text = "Ember & Spice is a restaurant concept showing what modern Indian dining looks like.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
        }

        // Narrative Section
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
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "THE IDEA",
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Regional India, one plate at a time.",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextLight
                    )
                    Text(
                        text = "The concept draws on home kitchens across the country — Punjab's glowing charcoal tandoors, Lucknow's dough-sealed dum pots, Chettinad's aromatic black pepper and Rajasthan's smoked Mathania chillies — and brings them to one warm, lantern-lit room.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted,
                        lineHeight = 22.sp
                    )
                    Text(
                        text = "Every detail here, from the bespoke menu to the operating hours, is crafted with passion for rich gastronomy.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Interior Atmosphere Image
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.interior),
                    contentDescription = "Lantern lit dining room",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // The Three Pillars
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "OUR FOUNDATIONS",
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                    color = EmberGold,
                    fontWeight = FontWeight.Bold
                )

                RestaurantData.pillars.forEach { pillar ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkSurface),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = pillar.number,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmberGold.copy(alpha = 0.5f)
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = pillar.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = TextLight
                                )
                                Text(
                                    text = pillar.text,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextMuted,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // CTA Band
        item {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                CtaBand(
                    onPlanVisitClick = onNavigateToVisit,
                    onViewMenuClick = onNavigateToMenu
                )
            }
        }
    }
}
