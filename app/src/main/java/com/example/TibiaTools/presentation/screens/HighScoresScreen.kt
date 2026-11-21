package com.example.TibiaTools.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.TibiaTools.domain.model.HighScoreItem
import com.example.TibiaTools.presentation.components.CustomLinearWavyProgressIndicator
import com.example.TibiaTools.presentation.components.FilterData
import com.example.TibiaTools.presentation.components.FilterList
import com.example.TibiaTools.presentation.components.Loader
import com.example.TibiaTools.presentation.components.PlayerInfo
import com.example.TibiaTools.presentation.components.PlayerLists
import com.example.TibiaTools.presentation.components.PodiumPlayer
import com.example.TibiaTools.presentation.components.SingleChipSelector
import com.example.TibiaTools.presentation.components.TopPodiumSection
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.presentation.viewmodel.ViewModelHighScores
import com.example.TibiaTools.utils.RawResourceReader
import com.example.TibiaTools.utils.ex.toSpaceReplace
import com.example.ttools.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HighScoresScreen(
    modifier: Modifier = Modifier,
    viewModelHigscores: ViewModelHighScores = koinViewModel()
) {
    val worlds = viewModelHigscores.worlds.collectAsStateWithLifecycle()
    val ranks = viewModelHigscores.ranks.collectAsLazyPagingItems()
    HighScoresScreen(
        modifier = modifier,
        worldsState = worlds.value,
        ranks = ranks,
        onSelectWorld = {
            viewModelHigscores.setWorld(it.text)
        },
        onSelectCategory = {
            Log.i("HigScoresScreen", it)
            viewModelHigscores.setCategory(it.toSpaceReplace())
        }
    )
}

@Composable
fun HighScoresScreen(
    modifier: Modifier = Modifier,
    worldsState: ViewModelHighScores.UIStateWords,
    ranks: LazyPagingItems<HighScoreItem>? = null,
    onSelectWorld: (option: FilterData) -> Unit = {},
    onSelectCategory: (option: String) -> Unit = {}
) {
    val context = LocalContext.current
    val vocations = remember {
        RawResourceReader.readLines(context.resources.openRawResource(R.raw.vocations))
    }

    val categories = remember {
        RawResourceReader.readLines(context.resources.openRawResource(R.raw.categorias))
    }

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
                    when (val worlds = worldsState) {
                        is ViewModelHighScores.UIStateWords.Idle -> {}
                        is ViewModelHighScores.UIStateWords.Loading -> {
                            CustomLinearWavyProgressIndicator()
                        }
                        is ViewModelHighScores.UIStateWords.Success -> {
                            val regularWorlds = worlds.worlds?.worlds?.regularWorlds?.mapIndexed { index, world ->
                                FilterData(
                                    id = index + 1,
                                    text = world.name,
                                    type = world.pvpType
                                )
                            } ?: emptyList()

                            worldsList = listOf(FilterData(id = 0, text = "All")) + regularWorlds
                        }
                        else -> {}
                    }

                    FilterList(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Select world",
                        options = worldsList,
                        onSelectOption = onSelectWorld
                    )
//                    Spacer(modifier = Modifier.size(16.dp))
//                    SingleSelectButton(
//                        modifier = Modifier.fillMaxWidth(),
//                        options = listOf("All", "EK", "RP", "MS", "ED", "MK")
//                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    SingleChipSelector(
                        modifier = Modifier.fillMaxWidth(),
                        options = categories,
                        onClick = onSelectCategory
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
            ranks?.let {
                Log.i("HigScoresScreen", "state: ${it.loadState.refresh}")
                when (it.loadState.refresh) {
                    is LoadState.Loading if ranks.itemCount == 0 -> {
                        item {
                            Loader()
                        }
                    }
                    //vacio
                    is LoadState.NotLoading if it.itemCount == 0 -> {
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(
                                    text = "not data available",
                                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                                    fontWeight = FontWeight.Bold,
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }
                    }

                    is LoadState.Error -> {
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(
                                    text = "Something went wrong trying to get the data",
                                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                                    fontWeight = FontWeight.Bold,
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }
                    }

                    else -> {
                        item {
                            val tops = (0 until minOf(3, it.itemCount)).mapNotNull { index ->
                                it[index]?.let { top ->
                                    PodiumPlayer(
                                        rank = top.rank,
                                        name = top.name,
                                        level = top.level.toLong(),
                                        vocation = top.vocation
                                    )
                                }
                            }

                            TopPodiumSection(
                                modifier = Modifier.fillMaxWidth(),
                                players = tops
                            )
                        }

                        items(
                            count = it.itemCount,
                            key = { index ->
                                val item = it[index]
                                "${item?.name ?: ""}_${item?.rank ?: index}_$index"
                            }
                        ) { index ->
                            val item = it[index]
                            if (item != null && index >= 3) {
                                val player = PlayerInfo(
                                    rank = item.rank,
                                    name = item.name,
                                    world = item.world,
                                    vocation = item.vocation,
                                    level = item.level,
                                    value = item.value
                                )
                                val isLast = index == it.itemCount - 1
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateItem(),
                                    shape = segmentShape(index, it.itemCount - 1),
                                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                                    contentColor = MaterialTheme.colorScheme.onSurface
                                ) {
                                    PlayerLists(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 16.dp),
                                        playerInfo = player
                                    )
                                    if (!isLast) {
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }

                        if (it.loadState.append is LoadState.Loading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CustomLinearWavyProgressIndicator()
                                }
                            }
                        }
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
            modifier = Modifier.fillMaxSize(),
            worldsState = ViewModelHighScores.UIStateWords.Success()
        )
    }
}
