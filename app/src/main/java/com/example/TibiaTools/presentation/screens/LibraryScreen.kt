package com.example.TibiaTools.presentation.screens

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
import androidx.compose.material3.HorizontalDivider
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
import com.example.TibiaTools.domain.model.SpellListItem
import com.example.TibiaTools.domain.model.SpellsData
import com.example.TibiaTools.presentation.components.CustomTab
import com.example.TibiaTools.presentation.components.Loader
import com.example.TibiaTools.presentation.components.NormalMonsterCard
import com.example.TibiaTools.presentation.components.RounderCardListContent
import com.example.TibiaTools.presentation.components.SearchTextField
import com.example.TibiaTools.presentation.components.SingleChipSelector
import com.example.TibiaTools.presentation.components.SpellItem
import com.example.TibiaTools.presentation.routes.FilterOptionSpells
import com.example.TibiaTools.presentation.routes.GroupSpell
import com.example.TibiaTools.presentation.routes.OptionsTab
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.presentation.viewmodel.ViewModelCreatures
import com.example.TibiaTools.presentation.viewmodel.ViewModelSpells
import org.koin.androidx.compose.koinViewModel

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewmodelCreatures: ViewModelCreatures = koinViewModel(),
    viewModelSpells: ViewModelSpells = koinViewModel(),
    onSpell: (id: String) -> Unit= {}
){
    val creatures = viewmodelCreatures.creature.collectAsStateWithLifecycle()
    val spells = viewModelSpells.spells.collectAsStateWithLifecycle()
    LibraryScreen(
        modifier = modifier,
        onSearchCreature = {
            viewmodelCreatures.finCreature(name = it)
        },
        creatures = creatures.value,
        spells = spells.value,
        onSelectCategory = {
            viewModelSpells.setFilterType(it)
        },
        onSpell = onSpell
    )
}


@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    onSearchCreature: (creature:String)-> Unit = {},
    creatures: ViewModelCreatures.UIState,
    spells: ViewModelSpells.UIState,
    onSelectCategory: (option: String) -> Unit = {},
    onSpell: (id: String) -> Unit= {}
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) { innerPadding ->
        var tabSelection by rememberSaveable { mutableStateOf(OptionsTab.CREATURES) }

        var searchText by rememberSaveable { mutableStateOf("") }

        var countCreatures by remember { mutableIntStateOf(0) }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
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
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Loader()
                            }
                        }
                        is ViewModelCreatures.UIState.Error->{
                            item(span = { GridItemSpan(maxLineSpan) }) {
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
                                        modifier = Modifier.padding(bottom = 8.dp),
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
                    when(val state = spells){
                        is ViewModelSpells.UIState.Idle -> {}
                        is ViewModelSpells.UIState.Loader -> {
                            item(span = { GridItemSpan(maxLineSpan) }){
                                Loader()
                            }
                        }
                        is ViewModelSpells.UIState.Error -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
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
                        is ViewModelSpells.UIState.Success -> {
                            item(span = { GridItemSpan(maxLineSpan) }){
                              Column(modifier = Modifier.fillMaxWidth()) {
                                  val options = FilterOptionSpells.entries.map { it.type }
                                  SingleChipSelector(
                                      modifier = Modifier.fillMaxWidth(),
                                      options = options,
                                      onClick = onSelectCategory
                                  )
                                  Spacer(modifier = Modifier.size(10.dp))
                              }
                            }
                            state.data?.let {
                                items(
                                    count = it.spellsList.size,
                                    span = { GridItemSpan(maxLineSpan) }
                                ) { index->
                                    RounderCardListContent(
                                        index = index,
                                        lasIndex = it.spellsList.size - 1,
                                        modifier = Modifier.fillMaxWidth().animateItem()
                                    ){
                                        val spell = it.spellsList[index]
                                        val first = index == 0
                                        SpellItem(
                                            modifier = Modifier.fillMaxWidth(),
                                            name = spell.name,
                                            formula = spell.formula,
                                            level = spell.level,
                                            mana = spell.mana,
                                            type = spell.type,
                                            onClick = {
                                                onSpell(spell.spellId)
                                            }
                                        )
                                        if(!first){
                                            HorizontalDivider()
                                        }
                                    }
                                }
                            }
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
            ),
            spells = ViewModelSpells.UIState.Success(
                data = SpellsData(
                    spellsList = listOf(
                        SpellListItem(name = "Animate Dead Rune", formula = "adana mort", level = "27", mana = "600", type = GroupSpell.SUPPORT),
                        SpellListItem(name = "Light", formula = "utevo", level = "0", mana = "20", type = GroupSpell.SUPPORT),
                        SpellListItem(name = "Great Fire Wave", formula = "exevo gran flam hur", level = "38", mana = "120", type = GroupSpell.ATTACK),
                        SpellListItem(name = "Ultimate Healing", formula = "exura vita", level = "30", mana = "160", type = GroupSpell.HEALING)
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
            ),
            spells = ViewModelSpells.UIState.Success(
                data = SpellsData(
                    spellsList = listOf(
                        SpellListItem(name = "Animate Dead Rune", formula = "adana mort", level = "27", mana = "600", type = GroupSpell.SUPPORT),
                        SpellListItem(name = "Light", formula = "utevo", level = "0", mana = "20", type = GroupSpell.SUPPORT),
                        SpellListItem(name = "Great Fire Wave", formula = "exevo gran flam hur", level = "38", mana = "120", type = GroupSpell.ATTACK),
                        SpellListItem(name = "Ultimate Healing", formula = "exura vita", level = "30", mana = "160", type = GroupSpell.HEALING)
                    )
                )
            )
        )
    }
}
