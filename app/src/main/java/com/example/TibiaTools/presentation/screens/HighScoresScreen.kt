package com.example.TibiaTools.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.TibiaTools.presentation.components.CustomLinearWavyProgressIndicator
import com.example.TibiaTools.presentation.components.FilterData
import com.example.TibiaTools.presentation.components.FilterList
import com.example.TibiaTools.presentation.components.PlayerInfo
import com.example.TibiaTools.presentation.components.PlayerLists
import com.example.TibiaTools.presentation.components.PodiumPlayer
import com.example.TibiaTools.presentation.components.SingleChipSelector
import com.example.TibiaTools.presentation.components.SingleSelectButton
import com.example.TibiaTools.presentation.components.TopPodiumSection
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.presentation.viewmodel.ViewModelHighScores
import org.koin.androidx.compose.koinViewModel


@Composable
fun HighScoresScreen(
    modifier: Modifier = Modifier,
    viewModelHigscores: ViewModelHighScores = koinViewModel()
) {
    val worlds = viewModelHigscores.worlds.collectAsStateWithLifecycle()
    HighScoresScreen(
        modifier = modifier,
        worldsState = worlds.value
    )
}
@Composable
fun HighScoresScreen(
    modifier: Modifier = Modifier,
    worldsState: ViewModelHighScores.UIStateWords
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var worldsList = listOf<FilterData>()
                    when(val worlds = worldsState){
                        is ViewModelHighScores.UIStateWords.Idle->{}
                        is ViewModelHighScores.UIStateWords.Loading ->{
                            CustomLinearWavyProgressIndicator()
                        }
                        is ViewModelHighScores.UIStateWords.Success ->{
                            worldsList = worlds.worlds?.worlds?.regularWorlds?.mapIndexed { index, world ->
                                FilterData(
                                    id = index,
                                    text = world.name,
                                    type = world.pvpType
                                )
                            }?: emptyList()
                        }
                        else->{}
                    }
                    FilterList(
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = "Select world",
                        options = worldsList
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    SingleSelectButton(
                        options = listOf("All", "EK", "RP", "MS", "ED")
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    SingleChipSelector(
                        modifier = Modifier.fillMaxWidth(),
                        options = listOf(
                            "Experience",
                            "Magic level",
                            "Sword",
                            "Axe",
                            "Distances"
                        )
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    TopPodiumSection(
                        modifier = Modifier.fillMaxWidth(),
                        players = listOf(
                            PodiumPlayer(
                                rank = 1,
                                name = "Lunatic Princess",
                                vocation = "EK",
                                level = 2418,
                            ),
                            PodiumPlayer(
                                rank = 2,
                                name = "Lunatic Princess",
                                vocation = "EK",
                                level = 2418,
                            ),
                            PodiumPlayer(
                                rank = 3,
                                name = "Lunatic Princess",
                                vocation = "EK",
                                level = 2418,
                            )
                        )
                    )
                }
            }

            val players = listOf(
                PlayerInfo(
                    rank = 1,
                    name = "Lunatic Princess",
                    world = "Antica",
                    vocation = "EK",
                    level = 2418,
                    experience = 235267.7
                ),
                PlayerInfo(
                    rank = 2,
                    name = "Lunatic Princess",
                    world = "Antica",
                    vocation = "EK",
                    level = 2418,
                    experience = 235267.7
                ),
                PlayerInfo(
                    rank = 3,
                    name = "Lunatic Princess",
                    world = "Antica",
                    vocation = "EK",
                    level = 2418,
                    experience = 235267.7
                ),
                PlayerInfo(
                    rank = 4,
                    name = "Lunatic Princess",
                    world = "Antica",
                    vocation = "EK",
                    level = 2418,
                    experience = 235267.7
                ),
                PlayerInfo(
                    rank = 5,
                    name = "Lunatic Princess",
                    world = "Antica",
                    vocation = "EK",
                    level = 2418,
                    experience = 235267.7
                )
            )

            itemsIndexed(
                items = players,
                key = { _, player -> player.rank },
            ) { index, player ->
                val isFirst = index == 0
                val isLast = index == players.lastIndex
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    shape = segmentShape(index, players.lastIndex),
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    if (isFirst) {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                    PlayerLists(
                        modifier = Modifier
                            .fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
                        playerInfo = player
                    )
                    if (isLast || !isFirst) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

private val CardCorner = 35.dp

private fun segmentShape(index: Int, lastIndex: Int): Shape = when {
    lastIndex == 0 -> RoundedCornerShape(CardCorner)
    index == 0 -> RoundedCornerShape(topStart = CardCorner, topEnd = CardCorner)
    index == lastIndex -> RoundedCornerShape(bottomStart = CardCorner, bottomEnd = CardCorner)
    else -> RectangleShape
}


@Preview(showBackground = true)
@Composable
fun HighScoresScreenPreview() {
    TibiaToolsTheme(darkTheme = true) {
        HighScoresScreen(
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}