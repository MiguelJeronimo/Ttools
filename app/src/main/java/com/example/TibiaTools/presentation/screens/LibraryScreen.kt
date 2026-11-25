package com.example.TibiaTools.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.TibiaTools.domain.model.BoostedData
import com.example.TibiaTools.domain.model.CreatureListItem
import com.example.TibiaTools.domain.model.CreaturesData
import com.example.TibiaTools.presentation.components.CustomTab
import com.example.TibiaTools.presentation.components.Loader
import com.example.TibiaTools.presentation.components.NormalMonsterCard
import com.example.TibiaTools.presentation.components.RounderCardListContent
import com.example.TibiaTools.presentation.components.SearchTextField
import com.example.TibiaTools.presentation.components.SpellItem
import com.example.TibiaTools.presentation.routes.OptionsTab
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.presentation.viewmodel.ViewModelCreatures
import org.koin.androidx.compose.koinViewModel

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewmodelCreatures: ViewModelCreatures = koinViewModel()
){
    val creatures = viewmodelCreatures.creature.collectAsStateWithLifecycle()
    LibraryScreen(
        modifier = Modifier,
        onSearchCreature = {
            viewmodelCreatures.finCreature(name = it)
        },
        creatures = creatures.value
    )
}


@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    onSearchCreature: (creature:String)-> Unit = {},
    creatures: ViewModelCreatures.UIState
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) { innerPadding ->
        var tabSelection by remember { mutableStateOf(OptionsTab.CREATURES) }

        var searchText by rememberSaveable { mutableStateOf("") }
        val isError = searchText.isNotEmpty() && searchText.length <= 1

        var countCreatures by remember { mutableIntStateOf(0) }
        LazyVerticalGrid(
            modifier = modifier.padding(innerPadding),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(modifier = Modifier.fillMaxWidth()){
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Library",
                        style = MaterialTheme.typography.displayMediumEmphasized,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }

            stickyHeader {
                CustomTab(
                    modifier = Modifier.fillMaxWidth(),
                    destinations = OptionsTab.entries,
                    selectDestination = tabSelection,
                    onSelected = {
                        tabSelection = it
                    }
                )
            }

            when (tabSelection) {
                OptionsTab.CREATURES -> {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SearchTextField(
                            modifier = Modifier.fillMaxWidth(),
                            label = "Filter creatures",
                            value = searchText,
                            onValueChange = { searchText = it },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon"
                                )
                            },
                            trailingIcon = {
                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = "$countCreatures"
                                )
                            },
                            onSearchPlayer = onSearchCreature
                        )
                    }
                    when(val state = creatures){
                        is ViewModelCreatures.UIState.Idle->{}
                        is ViewModelCreatures.UIState.Loader->{
                            item {
                                Loader()
                            }
                        }
                        is ViewModelCreatures.UIState.Error->{
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
                        is ViewModelCreatures.UIState.Success-> {
                            state.data?.let {
                                countCreatures = it.creature_list.size
                                items(items = it.creature_list) { creature->
                                    NormalMonsterCard(
                                        description = creature.name,
                                        url = creature.imageUrl
                                    )
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.size(10.dp))
                            }
                        }
                    }
                }

                OptionsTab.SPELLS -> {
                    items(count = 20, span = { GridItemSpan(maxLineSpan) }) {
                        RounderCardListContent(
                            index = 0,
                            lasIndex = 20-1,
                            modifier = Modifier.fillMaxWidth().animateItem()
                        ){
                            SpellItem(
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "LibraryScreen Light Preview", showBackground = true)
@Composable
fun LibraryScreenPreview() {
    TibiaToolsTheme(darkTheme = false) {
        LibraryScreen(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            onSearchCreature = {},
            creatures = ViewModelCreatures.UIState.Success(
                data = CreaturesData(
                    boosted = BoostedData(
                        name = "Quara Predator Scout",
                        race = "quarapredatorscout",
                        imageUrl = "https://static.tibia.com/images/global/header/monsters/quarapredatorscout.gif",
                        featured = true
                    ),
                    creatureList = listOf(
                        CreatureListItem(name = "Acid Blob", race = "acidblob", imageUrl = "https://static.tibia.com/images/library/acidblob.gif"),
                        CreatureListItem(name = "Amazon", race = "amazon", imageUrl = "https://static.tibia.com/images/library/amazon.gif"),
                        CreatureListItem(name = "Demon", race = "demon", imageUrl = "https://static.tibia.com/images/library/demon.gif"),
                        CreatureListItem(name = "Dragon", race = "dragon", imageUrl = "https://static.tibia.com/images/library/dragon.gif")
                    )
                )
            )
        )
    }
}

@Preview(name = "LibraryScreen Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun LibraryScreenDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        LibraryScreen(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            onSearchCreature = {},
            creatures = ViewModelCreatures.UIState.Success(
                data = CreaturesData(
                    boosted = BoostedData(
                        name = "Quara Predator Scout",
                        race = "quarapredatorscout",
                        imageUrl = "https://static.tibia.com/images/global/header/monsters/quarapredatorscout.gif",
                        featured = true
                    ),
                    creatureList = listOf(
                        CreatureListItem(name = "Acid Blob", race = "acidblob", imageUrl = "https://static.tibia.com/images/library/acidblob.gif"),
                        CreatureListItem(name = "Amazon", race = "amazon", imageUrl = "https://static.tibia.com/images/library/amazon.gif"),
                        CreatureListItem(name = "Demon", race = "demon", imageUrl = "https://static.tibia.com/images/library/demon.gif"),
                        CreatureListItem(name = "Dragon", race = "dragon", imageUrl = "https://static.tibia.com/images/library/dragon.gif")
                    )
                )
            )
        )
    }
}
