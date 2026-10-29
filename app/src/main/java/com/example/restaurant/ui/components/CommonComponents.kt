package com.example.restaurant.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.restaurant.data.MenuItem
import com.example.restaurant.data.RestaurantData
import com.example.restaurant.ui.theme.*

@Composable
fun ConceptBanner(modifier: Modifier = Modifier) {
    Surface(
        color = DarkSurfaceVariant,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Website concept by ${RestaurantData.agency} — ${RestaurantData.businessName} is a fictional restaurant.",
            fontSize = 11.sp,
            color = TextMuted,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)
        )
    }
}

@Composable
fun Wordmark(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Ember ",
                color = TextLight,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "& ",
                color = EmberGold,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Spice",
                color = TextLight,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "INDIAN KITCHEN",
            fontSize = 8.sp,
            letterSpacing = 2.sp,
            color = TextMuted,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun SectionHeading(
    eyebrow: String,
    title: String,
    intro: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = eyebrow.uppercase(),
            fontSize = 11.sp,
            letterSpacing = 2.5.sp,
            color = EmberGold,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = TextLight
        )
        if (!intro.isNullOrBlank()) {
            Text(
                text = intro,
                style = MaterialTheme.typography.bodyMedium,
                color = TextMuted,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun OpeningHoursList(
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RestaurantData.openingHours.forEachIndexed { index, hour ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = hour.days,
                        fontWeight = FontWeight.Medium,
                        color = TextLight,
                        fontSize = if (compact) 13.sp else 14.sp
                    )
                    Text(
                        text = hour.hours,
                        color = TextMuted,
                        fontSize = if (compact) 12.sp else 13.sp
                    )
                }
                if (index < RestaurantData.openingHours.size - 1) {
                    HorizontalDivider(color = DarkBorder.copy(alpha = 0.5f))
                }
            }
        }
    }
}

@Composable
fun CtaBand(
    onPlanVisitClick: () -> Unit,
    onViewMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = DarkSurface,
        modifier = modifier.fillMaxWidth(),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "AN EVENING BY THE FIRE",
                fontSize = 11.sp,
                letterSpacing = 2.sp,
                color = EmberGold,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Plan your visit to Ember & Spice.",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextLight
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onPlanVisitClick,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("cta_plan_visit"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EmberGold,
                        contentColor = DarkBackground
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("Plan Your Visit", fontWeight = FontWeight.Bold)
                }
                OutlinedButton(
                    onClick = onViewMenuClick,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("cta_see_menu"),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(EmberGold)
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("See Menu", color = EmberGold)
                }
            }
        }
    }
}

@Composable
fun DishDetailDialog(
    item: MenuItem,
    onDismiss: () -> Unit,
    onEnquire: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            border = CardDefaults.outlinedCardBorder().copy(
                brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
            )
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.drawableRes),
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(DarkBackground.copy(alpha = 0.7f), RoundedCornerShape(20.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextLight
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextLight,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = RestaurantData.formatPrice(item.price),
                            color = EmberGold,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Eco,
                                contentDescription = null,
                                tint = if (item.veg) VegGreen else NonVegBrown,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (item.veg) "Vegetarian" else "Non-Vegetarian",
                                color = TextMuted,
                                fontSize = 13.sp
                            )
                        }

                        if (item.spice != null && item.spice > 0) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                repeat(item.spice) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = null,
                                        tint = EmberSpiceRed,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Spice ${item.spice}/3",
                                    color = TextMuted,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }

                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextLight,
                        lineHeight = 22.sp
                    )

                    Button(
                        onClick = {
                            onDismiss()
                            onEnquire()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmberGold,
                            contentColor = DarkBackground
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text("Enquire About Table", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
