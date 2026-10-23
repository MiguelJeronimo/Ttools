package com.example.TibiaTools.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.TibiaTools.presentation.components.MainBottonBar
import com.example.TibiaTools.presentation.routes.HomeRoutes
import com.example.TibiaTools.presentation.screens.HomeScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberSaveable { mutableStateListOf<HomeRoutes>(HomeRoutes.Home) }
    Scaffold(
        bottomBar = {
            MainBottonBar()
        },
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = {keyScreen->
                when(keyScreen){
                    HomeRoutes.Home -> NavEntry(HomeRoutes.Home) {
                        HomeScreen(
                            modifier = modifier,
                            onMenuClick = { backStack.add(HomeRoutes.Home) },
                            onSearchClick = { backStack.add(HomeRoutes.Home) },
                            onWorldsClick = { backStack.add(HomeRoutes.Home) },
                            onToolClick = { tool -> backStack.add(HomeRoutes.Home) },
                            onNewsClick = { news -> backStack.add(HomeRoutes.Home) }
                        )
                    }
                }
            }
//            entryDecorators = listOf(// entrys decoradores para el backstack de compose navigation
//                rememberSaveableStateHolderNavEntryDecorator(),
//                rememberViewModelStoreNavEntryDecorator()
//            ),
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
//    TibiaToolsTheme(darkTheme = true) {
//        HomeContent(
//            totalOnline = 17445,
//            totalWorlds = 96,
//            rashidCity = "Carlin",
//            boostedCreatureName = "Dragon",
//            boostedCreatureImage = "https://static.tibia.com/images/library/dragon.gif",
//            boostedBossName = "Ferumbras",
//            boostedBossImage = "https://static.tibia.com/images/library/ferumbras.gif",
//            newsList = sampleNewsList
//        )
//    }
}
