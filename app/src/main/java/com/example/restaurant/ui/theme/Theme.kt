package com.example.restaurant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = EmberGold,
    onPrimary = Color(0xFF141210),
    primaryContainer = Color(0xFF382A15),
    onPrimaryContainer = EmberGold,
    secondary = EmberSpiceRed,
    onSecondary = Color.White,
    background = DarkBackground,
    onBackground = TextLight,
    surface = DarkSurface,
    onSurface = TextLight,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextMuted,
    outline = DarkBorder
)

@Composable
fun EmberSpiceTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
