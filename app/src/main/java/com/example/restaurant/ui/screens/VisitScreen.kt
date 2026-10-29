package com.example.restaurant.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurant.R
import com.example.restaurant.data.RestaurantData
import com.example.restaurant.ui.components.ConceptBanner
import com.example.restaurant.ui.components.OpeningHoursList
import com.example.restaurant.ui.components.SectionHeading
import com.example.restaurant.ui.theme.*

@Composable
fun VisitScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Enquiry state
    var guestName by remember { mutableStateOf("") }
    var guestContact by remember { mutableStateOf("") }
    var partySize by remember { mutableStateOf(2) }
    var selectedDate by remember { mutableStateOf("Tonight") }
    var selectedTime by remember { mutableStateOf("19:30") }
    var notes by remember { mutableStateOf("") }
    var enquirySubmitted by remember { mutableStateOf(false) }

    // FAQ expansion state
    val expandedFaqs = remember { mutableStateMapOf<Int, Boolean>() }

    val dateOptions = listOf("Tonight", "Tomorrow", "This Weekend")
    val timeOptions = listOf("12:30", "13:30", "19:00", "19:30", "20:30", "21:00")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("visit_screen"),
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
                    painter = painterResource(id = R.drawable.interior),
                    contentDescription = "Dining room",
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
                        text = "VISIT US",
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pull up a chair by the fire.",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextLight
                    )
                    Text(
                        text = "Opening hours, location and table enquiries.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }
        }

        // Opening Hours Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SectionHeading(
                    eyebrow = "Schedule",
                    title = "Opening Hours",
                    intro = "Walk-ins are warmly welcomed. Reservations are recommended for weekend dinners."
                )
                OpeningHoursList()
            }
        }

        // Location & Directions Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "LOCATION",
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Address",
                            tint = EmberGold,
                            modifier = Modifier.size(24.dp)
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = RestaurantData.businessName,
                                fontWeight = FontWeight.Bold,
                                color = TextLight,
                                fontSize = 16.sp
                            )
                            Text(
                                text = RestaurantData.address,
                                color = TextMuted,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Restaurant Address", RestaurantData.address)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Address copied to clipboard", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(4.dp),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                            )
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, tint = TextLight, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Copy", color = TextLight, fontSize = 12.sp)
                        }

                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${Uri.encode(RestaurantData.address)}"))
                                try {
                                    context.startActivity(intent)
                                } catch (_: Exception) {
                                    Toast.makeText(context, "Opening maps: ${RestaurantData.address}", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EmberGold,
                                contentColor = DarkBackground
                            )
                        ) {
                            Icon(Icons.Default.Directions, contentDescription = null, tint = DarkBackground, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Directions", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // Table Enquiry Form
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "TABLE ENQUIRIES",
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = EmberGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Send us a note",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextLight
                    )
                    Text(
                        text = "Starting an enquiry does not automatically book a table — our host will confirm availability.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )

                    if (enquirySubmitted) {
                        Surface(
                            color = DarkSurfaceVariant,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = EmberGold,
                                    modifier = Modifier.size(40.dp)
                                )
                                Text(
                                    text = "Enquiry Received",
                                    fontWeight = FontWeight.Bold,
                                    color = TextLight,
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "Thank you, $guestName. We have logged your request for $partySize guests ($selectedDate at $selectedTime).",
                                    color = TextMuted,
                                    fontSize = 13.sp,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                                TextButton(onClick = { enquirySubmitted = false }) {
                                    Text("Send another enquiry", color = EmberGold)
                                }
                            }
                        }
                    } else {
                        // Guest Name
                        OutlinedTextField(
                            value = guestName,
                            onValueChange = { guestName = it },
                            label = { Text("Your Name") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmberGold,
                                unfocusedBorderColor = DarkBorder,
                                focusedTextColor = TextLight,
                                unfocusedTextColor = TextLight,
                                focusedLabelColor = EmberGold,
                                unfocusedLabelColor = TextMuted
                            )
                        )

                        // Contact
                        OutlinedTextField(
                            value = guestContact,
                            onValueChange = { guestContact = it },
                            label = { Text("Phone or Email") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmberGold,
                                unfocusedBorderColor = DarkBorder,
                                focusedTextColor = TextLight,
                                unfocusedTextColor = TextLight,
                                focusedLabelColor = EmberGold,
                                unfocusedLabelColor = TextMuted
                            )
                        )

                        // Party Size Selector
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "Party Size: $partySize ${if (partySize == 1) "Guest" else "Guests"}",
                                color = TextLight,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                (1..6).forEach { num ->
                                    val isSelected = partySize == num
                                    OutlinedButton(
                                        onClick = { partySize = num },
                                        modifier = Modifier.weight(1f),
                                        contentPadding = PaddingValues(4.dp),
                                        shape = RoundedCornerShape(4.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            containerColor = if (isSelected) EmberGold else DarkSurfaceVariant
                                        ),
                                        border = ButtonDefaults.outlinedButtonBorder.copy(
                                            brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) EmberGold else DarkBorder)
                                        )
                                    ) {
                                        Text(
                                            text = "$num",
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) DarkBackground else TextLight,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }

                        // Date Preference
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Date", color = TextLight, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                dateOptions.forEach { opt ->
                                    val isSelected = selectedDate == opt
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { selectedDate = opt },
                                        label = { Text(opt, fontSize = 11.sp) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            containerColor = DarkSurfaceVariant,
                                            labelColor = TextMuted,
                                            selectedContainerColor = EmberGold,
                                            selectedLabelColor = DarkBackground
                                        ),
                                        border = FilterChipDefaults.filterChipBorder(
                                            enabled = true,
                                            selected = isSelected,
                                            borderColor = if (isSelected) EmberGold else DarkBorder
                                        )
                                    )
                                }
                            }
                        }

                        // Time Preference
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Time Slot", color = TextLight, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                timeOptions.take(4).forEach { t ->
                                    val isSelected = selectedTime == t
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = { selectedTime = t },
                                        label = { Text(t, fontSize = 11.sp) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            containerColor = DarkSurfaceVariant,
                                            labelColor = TextMuted,
                                            selectedContainerColor = EmberGold,
                                            selectedLabelColor = DarkBackground
                                        ),
                                        border = FilterChipDefaults.filterChipBorder(
                                            enabled = true,
                                            selected = isSelected,
                                            borderColor = if (isSelected) EmberGold else DarkBorder
                                        )
                                    )
                                }
                            }
                        }

                        // Notes
                        OutlinedTextField(
                            value = notes,
                            onValueChange = { notes = it },
                            label = { Text("Special Requests / Dietary Notes") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 2,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmberGold,
                                unfocusedBorderColor = DarkBorder,
                                focusedTextColor = TextLight,
                                unfocusedTextColor = TextLight,
                                focusedLabelColor = EmberGold,
                                unfocusedLabelColor = TextMuted
                            )
                        )

                        // Submit Button
                        Button(
                            onClick = {
                                if (guestName.isBlank()) {
                                    Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
                                } else {
                                    enquirySubmitted = true
                                    Toast.makeText(context, "Enquiry submitted!", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("submit_enquiry_btn"),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EmberGold,
                                contentColor = DarkBackground
                            )
                        ) {
                            Text("Submit Table Enquiry", fontWeight = FontWeight.Bold)
                        }

                        // Direct Email Button
                        OutlinedButton(
                            onClick = {
                                val subject = Uri.encode("Table enquiry: $guestName ($partySize guests, $selectedDate)")
                                val body = Uri.encode("Name: $guestName\nGuests: $partySize\nDate: $selectedDate\nTime: $selectedTime\nNotes: $notes")
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:${RestaurantData.email}?subject=$subject&body=$body")
                                }
                                try {
                                    context.startActivity(intent)
                                } catch (_: Exception) {
                                    Toast.makeText(context, "Email: ${RestaurantData.email}", Toast.LENGTH_LONG).show()
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                            )
                        ) {
                            Icon(Icons.Default.Mail, contentDescription = null, tint = TextLight, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Send Email to ${RestaurantData.email}", color = TextLight, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // Questions / FAQs
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SectionHeading(
                    eyebrow = "Questions",
                    title = "Frequently Asked Questions"
                )

                RestaurantData.faqs.forEachIndexed { index, faq ->
                    val isExpanded = expandedFaqs[index] == true
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expandedFaqs[index] = !isExpanded },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkSurface),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = androidx.compose.ui.graphics.SolidColor(DarkBorder)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = faq.question,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextLight,
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = EmberGold
                                )
                            }
                            AnimatedVisibility(visible = isExpanded) {
                                Column(modifier = Modifier.padding(top = 10.dp)) {
                                    HorizontalDivider(color = DarkBorder.copy(alpha = 0.5f))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = faq.answer,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = TextMuted,
                                        lineHeight = 20.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
