package com.example.TibiaTools.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.TibiaTools.domain.model.CharacterDetail
import com.example.TibiaTools.domain.model.CharactersData
import com.example.TibiaTools.presentation.components.AchievementsData
import com.example.TibiaTools.presentation.components.AchievementsList
import com.example.TibiaTools.presentation.components.CharacterCard
import com.example.TibiaTools.presentation.components.CharacterCardModel
import com.example.TibiaTools.presentation.components.CustomCard
import com.example.TibiaTools.presentation.components.ListDataCard
import com.example.TibiaTools.presentation.components.ListDataCardModel
import com.example.TibiaTools.presentation.components.Loader
import com.example.TibiaTools.presentation.components.SearchTextField
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.TibiaTools.presentation.viewmodel.ViewModelPlayers
import com.example.ttools.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchPlayerScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModelPlayers = koinViewModel()
){
    val playerState = viewModel.player.collectAsStateWithLifecycle()
    SearchPlayerScreen(
        modifier = modifier,
        onSearchPlayer = { name->
            viewModel.searchPlayer(name = name)
        },
        state = playerState.value
    )
}
@Composable
fun SearchPlayerScreen(
    modifier: Modifier = Modifier,
    onSearchPlayer: (String) -> Unit = {},
    state: ViewModelPlayers.UiState
){
    var searchText by rememberSaveable { mutableStateOf("") }
    val isError = searchText.isNotEmpty() && searchText.length <= 1

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
            Spacer(modifier = Modifier.size(16.dp))
            SearchTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Search player",
                value = searchText,
                onValueChange = { searchText = it },
                isError = isError,
                errorMessage = "Player name must be at least 3 characters long",
                onSearchPlayer = onSearchPlayer
            )
            Spacer(modifier = Modifier.size(16.dp))
            when (state){
                is ViewModelPlayers.UiState.Idle-> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Search for a player to see their information.",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                is ViewModelPlayers.UiState.Loading-> {
                    Loader()
                }
                is ViewModelPlayers.UiState.Success-> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth().weight(1f)
                    ) {
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                PrimaryCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    details = state.player.character?.character
                                )
                                val details = state.player.character

                                Spacer(modifier = Modifier.size(20.dp))
                                ListDataCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    data = details?.getListDataCards() ?: emptyList()
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(
                                    text = "Displayed achievements",
                                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                                    fontWeight = FontWeight.Bold,
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                val achievements = listOf(
                                    AchievementsData("Hat Hunter", 3),
                                    AchievementsData("Heartbreaker", 1),
                                    AchievementsData("Starless Night", 1),
                                    AchievementsData("Achievement 1", 3),
                                    AchievementsData("Hat Hunter", 4),
                                    AchievementsData("Starless Night 3", 2)
                                )
                                AchievementsList(
                                    modifier = Modifier.fillMaxWidth(),
                                    achievements = achievements
                                )
                            }
                        }
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(
                                    text = "Characters on this account",
                                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                                    fontWeight = FontWeight.Bold,
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }
                        //Aqui iran los
                        val characterList = listOf(
                            CharacterCardModel(
                                name = "Fenna Lightfoot",
                                world = "Pacera",
                                isOnline = true,
                                isMain = true,
                                isViewing = true
                            ),
                            CharacterCardModel(
                                name = "Edric Hollowmere",
                                world = "Bona",
                                isOnline = false,
                                showChevron = true
                            ),
                            CharacterCardModel(
                                name = "Isolde Emberfall",
                                world = "Menera",
                                isOnline = false,
                                isTraded = true,
                                showChevron = true
                            )
                        )
                        items(characterList.size) { index ->
                            CharacterCard(
                                modifier = Modifier.fillMaxWidth(),
                                character = characterList[index]
                            )
                            if (index < characterList.lastIndex) {
                                Spacer(modifier = Modifier.size(10.dp))
                            }
                        }
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.size(16.dp))
                                Text(
                                    text = "Displayed achievements",
                                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                                    fontWeight = FontWeight.Bold,
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }

                        items(count = 3) {
                            ListItem(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentPadding = PaddingValues(),
                                leadingContent = {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(
                                                color = MaterialTheme.colorScheme.error,
                                                shape = CircleShape
                                            )
                                    ) {
                                        Icon(
                                            modifier = Modifier.align(Alignment.Center),
                                            painter = painterResource(R.drawable.skull_image),
                                            contentDescription = "Dangerous Icon",
                                            tint = MaterialTheme.colorScheme.onError,
                                        )
                                    }
                                },
                                supportingContent = {
                                    Text(
                                        text = "Died at Level 215 by weakened frazzlemaw.",
                                        style = MaterialTheme.typography.labelLargeEmphasized.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp
                                        )
                                    )
                                },
                                content = {
                                    Text(
                                        text = "Nov 12, 2026 · Level 215",
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                    )
                                }
                            )
                        }
                    }
                }
                is ViewModelPlayers.UiState.Error-> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Player not found or error occurred",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PrimaryCard(
    modifier: Modifier = Modifier,
    details: CharacterDetail? = null
){
    Card(
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .clickable { }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 36.dp, y = (-40).dp)
                    .size(170.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                        shape = CircleShape
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-40).dp, y = 50.dp)
                    .size(90.dp)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.20f),
                        shape = CircleShape
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(
                                    topStart = 50.dp,
                                    topEnd = 75.dp,
                                    bottomEnd = 55.dp,
                                    bottomStart = 65.dp
                                )
                            )
                    ){
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = details?.name?.first()?.toString() ?: "T",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = details?.name ?: "Unknown",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 25.sp,
                                lineHeight = 30.sp,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                ),
                                lineHeightStyle = LineHeightStyle(
                                    alignment = LineHeightStyle.Alignment.Center,
                                    trim = LineHeightStyle.Trim.Both
                                )
                            ),
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = details?.vocation ?: "Unknown",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomCard(
                        modifier = Modifier.weight(1f),
                        title = "Level",
                        subtitle = details?.level?.toString() ?: "Unknown"
                    )
                    CustomCard(
                        modifier = Modifier.weight(1f),
                        title = "Achiev.",
                        subtitle = details?.achievementPoints?.toString() ?: "Unknown"
                    )
                    CustomCard(
                        modifier = Modifier.weight(1f),
                        title = "World",
                        subtitle = details?.world ?: "Unknown"
                    )
                }
            }
        }
    }
}


private fun CharactersData.getListDataCards(): List<ListDataCardModel> {
    val details = this
    return buildList {
        details.character?.let {
            add(ListDataCardModel(key = "Residence", value = it.residence))
            it.guild?.let { add(ListDataCardModel(key = "Guild", value = it.name)) }
            add(ListDataCardModel(key = "Sex", value = it.sex))
            add(ListDataCardModel(key = "Account", value = it.accountStatus))
            add(ListDataCardModel(key = "Last login", value = it.lastLogin))
            it.married_to?.let { add(ListDataCardModel(key = "Married to", value = it)) }
            if (it.houses.isNotEmpty()) {
                it.houses.forEach { add(ListDataCardModel(key = "House", value = "${it.name} ${it.paid} ${it.town}")) }
            }
            it.title?.let { add(ListDataCardModel(key = "Title", value = it)) }

        }
        details.accountInformation?.let { if (it.loyaltyTitle.isNotEmpty()){add(ListDataCardModel(key = "Loyalty", value = it.loyaltyTitle))} }
        details.accountInformation?.let { if (it.created.isNotEmpty()){add(ListDataCardModel(key = "Created", value = it.created))} }
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryCardPreview(){
    TibiaToolsTheme(darkTheme = false) {
        SearchPlayerScreen(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
        )
    }
}