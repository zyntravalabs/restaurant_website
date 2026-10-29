package com.example.restaurant.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurant.data.MenuItem
import com.example.restaurant.data.RestaurantData
import com.example.restaurant.ui.theme.DarkBorder
import com.example.restaurant.ui.theme.DarkSurface
import com.example.restaurant.ui.theme.EmberGold
import com.example.restaurant.ui.theme.EmberSpiceRed
import com.example.restaurant.ui.theme.NonVegBrown
import com.example.restaurant.ui.theme.TextLight
import com.example.restaurant.ui.theme.TextMuted
import com.example.restaurant.ui.theme.VegGreen

@Composable
fun DishCard(
    item: MenuItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("dish_card_${item.id}")
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(id = item.drawableRes),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                if (item.featured) {
                    Surface(
                        color = EmberGold,
                        shape = RoundedCornerShape(bottomEnd = 6.dp),
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Text(
                            text = "SIGNATURE",
                            color = DarkSurface,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = TextLight,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = RestaurantData.formatPrice(item.price),
                        color = EmberGold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted,
                    lineHeight = 18.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Eco,
                            contentDescription = if (item.veg) "Vegetarian" else "Non-vegetarian",
                            tint = if (item.veg) VegGreen else NonVegBrown,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (item.veg) "Vegetarian" else "Non-veg",
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                    }

                    if (item.spice != null && item.spice > 0) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            repeat(item.spice) {
                                Icon(
                                    imageVector = Icons.Default.LocalFireDepartment,
                                    contentDescription = "Spice level ${item.spice}",
                                    tint = EmberSpiceRed,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
