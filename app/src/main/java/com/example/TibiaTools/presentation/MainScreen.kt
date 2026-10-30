package com.example.TibiaTools.presentation

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.TibiaTools.presentation.components.MainBottonBar
import com.example.TibiaTools.presentation.components.MainDrawerMenu
import com.example.TibiaTools.presentation.routes.HomeRoutes
import com.example.TibiaTools.presentation.routes.Options
import com.example.TibiaTools.presentation.screens.HomeScreen
import com.example.TibiaTools.presentation.screens.ToolsScreen
import com.example.TibiaTools.utils.back
import com.example.TibiaTools.utils.navigateTo
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val backStack = rememberNavBackStack(HomeRoutes.Home)
    val expressiveScheme = MotionScheme.expressive()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    MainDrawerMenu(
        drawerState = drawerState,
        onBlessClick = {
            scope.launch { drawerState.close() }
            backStack.navigateTo(HomeRoutes.Tools)
        },
        onSharedExperienceClick = {
            scope.launch { drawerState.close() }
            backStack.navigateTo(HomeRoutes.Tools)
        },
        onStaminaClick = {
            scope.launch { drawerState.close() }
            backStack.navigateTo(HomeRoutes.Tools)
        }
    ) {
        Scaffold(
            bottomBar = {
                MainBottonBar(
                    onNavigate = { option ->
                        Log.d("Navigation", "OPTIONS: $option")
                        when (option) {
                            Options.HOME.name -> {
                                backStack.navigateTo(HomeRoutes.Home)
                            }
                            Options.TOOLS.name -> {
                                backStack.navigateTo(HomeRoutes.Tools)
                            }
                        }
                    }
                )
            },
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "TibiaTools",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            Log.d("Menu", "onClick main menu")
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.PersonSearch,
                                contentDescription = "Search"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { innerPadding ->
            NavDisplay(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = { keyScreen ->
                    when (keyScreen) {
                        HomeRoutes.Home -> NavEntry(HomeRoutes.Home) {
                            HomeScreen(
                                modifier = Modifier.fillMaxSize(),
                                onMenuClick = { },
                                onSearchClick = { },
                                onWorldsClick = { },
                                onToolClick = { tool ->
                                    Log.d("MainScreen", "Click en tools: $tool")
                                    backStack.navigateTo(HomeRoutes.Tools)
                                },
                                onNewsClick = { }
                            )
                        }
                        HomeRoutes.Tools -> NavEntry(HomeRoutes.Tools) {
                            ToolsScreen(
                                modifier = Modifier.fillMaxSize(),
                                onBackClick = { backStack.back() }
                            )
                        }
                        else -> error("Unknown screen: $keyScreen")
                    }
                },
                transitionSpec = {
                    (slideInHorizontally(
                        animationSpec = expressiveScheme.defaultSpatialSpec(),
                        initialOffsetX = { fullWidth -> (fullWidth * 0.15f).toInt() }
                    ) + fadeIn(
                        animationSpec = expressiveScheme.defaultEffectsSpec()
                    ) + scaleIn(
                        animationSpec = expressiveScheme.defaultSpatialSpec(),
                        initialScale = 0.95f
                    )).togetherWith(
                        slideOutHorizontally(
                            animationSpec = expressiveScheme.defaultSpatialSpec(),
                            targetOffsetX = { fullWidth -> -(fullWidth * 0.10f).toInt() }
                        ) + fadeOut(
                            animationSpec = expressiveScheme.defaultEffectsSpec()
                        ) + scaleOut(
                            animationSpec = expressiveScheme.defaultSpatialSpec(),
                            targetScale = 0.92f
                        )
                    )
                },
                popTransitionSpec = {
                    (slideInHorizontally(
                        animationSpec = expressiveScheme.defaultSpatialSpec(),
                        initialOffsetX = { fullWidth -> -(fullWidth * 0.10f).toInt() }
                    ) + fadeIn(
                        animationSpec = expressiveScheme.defaultEffectsSpec()
                    ) + scaleIn(
                        animationSpec = expressiveScheme.defaultSpatialSpec(),
                        initialScale = 0.92f
                    )).togetherWith(
                        slideOutHorizontally(
                            animationSpec = expressiveScheme.defaultSpatialSpec(),
                            targetOffsetX = { fullWidth -> (fullWidth * 0.15f).toInt() }
                        ) + fadeOut(
                            animationSpec = expressiveScheme.defaultEffectsSpec()
                        ) + scaleOut(
                            animationSpec = expressiveScheme.defaultSpatialSpec(),
                            targetScale = 0.95f
                        )
                    )
                },
            )
        }
    }
}