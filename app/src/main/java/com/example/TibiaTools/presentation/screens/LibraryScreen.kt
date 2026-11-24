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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.presentation.components.CustomTab
import com.example.TibiaTools.presentation.components.NormalMonsterCard
import com.example.TibiaTools.presentation.components.SearchTextField
import com.example.TibiaTools.presentation.routes.OptionsTab
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) { innerPadding ->
        var tabSelection by remember { mutableStateOf(OptionsTab.CREATURES) }

        var searchText by rememberSaveable { mutableStateOf("") }
        val isError = searchText.isNotEmpty() && searchText.length <= 1
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
                            label = "Search player",
                            value = searchText,
                            onValueChange = { searchText = it },
                            isError = isError,
                            errorMessage = "Player name must be at least 3 characters long",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon"
                                )
                            },
                            onSearchPlayer = { }
                        )
                    }

                    items(20) {
                        NormalMonsterCard(
                            description = "Acid Blob",
                            url = "https://static.tibia.com/images/library/acidblob.gif"
                        )
                    }
                }

                OptionsTab.SPELLS -> {
                    items(count = 20, span = { GridItemSpan(maxLineSpan) }) {
                        Text("Texto de ejemplo")
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
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
    }
}

@Preview(name = "LibraryScreen Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun LibraryScreenDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        LibraryScreen(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
    }
}
