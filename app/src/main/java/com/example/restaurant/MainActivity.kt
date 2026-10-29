package com.example.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurant.ui.components.Wordmark
import com.example.restaurant.ui.screens.*
import com.example.restaurant.ui.theme.*

enum class AppScreen(val label: String, val icon: ImageVector) {
    HOME("Home", Icons.Default.Home),
    MENU("Menu", Icons.Default.RestaurantMenu),
    STORY("Story", Icons.Default.AutoStories),
    GALLERY("Gallery", Icons.Default.PhotoLibrary),
    VISIT("Visit", Icons.Default.EventSeat)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmberSpiceTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }

    // Back handling: if not on Home, back button returns to Home
    BackHandler(enabled = currentScreen != AppScreen.HOME) {
        currentScreen = AppScreen.HOME
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DarkBackground,
        contentColor = TextLight,
        topBar = {
            TopAppBar(
                title = {
                    Wordmark()
                },
                actions = {
                    if (currentScreen != AppScreen.VISIT) {
                        Button(
                            onClick = { currentScreen = AppScreen.VISIT },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .testTag("top_reserve_button"),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EmberGold,
                                contentColor = DarkBackground
                            ),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text("Enquire", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface,
                    titleContentColor = TextLight
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = DarkSurface,
                contentColor = TextLight
            ) {
                AppScreen.values().forEach { screen ->
                    val isSelected = currentScreen == screen
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentScreen = screen },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.label
                            )
                        },
                        label = {
                            Text(
                                text = screen.label,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = DarkBackground,
                            selectedTextColor = EmberGold,
                            indicatorColor = EmberGold,
                            unselectedIconColor = TextMuted,
                            unselectedTextColor = TextMuted
                        ),
                        modifier = Modifier.testTag("nav_item_${screen.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                AppScreen.HOME -> HomeScreen(
                    onNavigateToMenu = { currentScreen = AppScreen.MENU },
                    onNavigateToVisit = { currentScreen = AppScreen.VISIT },
                    onNavigateToStory = { currentScreen = AppScreen.STORY },
                    onNavigateToGallery = { currentScreen = AppScreen.GALLERY }
                )
                AppScreen.MENU -> MenuScreen(
                    onNavigateToVisit = { currentScreen = AppScreen.VISIT }
                )
                AppScreen.STORY -> StoryScreen(
                    onNavigateToMenu = { currentScreen = AppScreen.MENU },
                    onNavigateToVisit = { currentScreen = AppScreen.VISIT }
                )
                AppScreen.GALLERY -> GalleryScreen()
                AppScreen.VISIT -> VisitScreen()
            }
        }
    }
}
