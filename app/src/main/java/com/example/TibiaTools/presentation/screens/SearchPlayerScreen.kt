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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.components.AchievementsData
import com.example.TibiaTools.presentation.components.AchievementsList
import com.example.TibiaTools.presentation.components.CharacterCard
import com.example.TibiaTools.presentation.components.CharacterCardModel
import com.example.TibiaTools.presentation.components.CustomCard
import com.example.TibiaTools.presentation.components.ListDataCard
import com.example.TibiaTools.presentation.components.ListDataCardModel
import com.example.TibiaTools.presentation.components.SearchTextField
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.ttools.R

@Composable
fun SearchPlayerScreen(
    modifier: Modifier = Modifier
){
    var searchText by rememberSaveable { mutableStateOf("") }
    val isError = searchText.isNotEmpty() && searchText.length <= 1

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.size(16.dp))
                    SearchTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Search player",
                        value = searchText,
                        onValueChange = { searchText = it },
                        isError = isError,
                        errorMessage = "Player name must be at least 3 characters long"
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    PrimaryCard(
                        modifier = Modifier.fillMaxWidth()
                    )
                    val data = listOf(
                        ListDataCardModel(key = "Residence", value = "Yalahar"),
                        ListDataCardModel(key = "Guild", value = "Recruit of Ember Legion"),
                        ListDataCardModel(key = "Sex", value = "Female"),
                        ListDataCardModel(key = "Account", value = "Premium Account"),
                        ListDataCardModel(key = "Last login", value = "Nov 9, 2026"),
                        ListDataCardModel(key = "Married to", value = "Garrick Stoneheart"),
                        ListDataCardModel(key = "Houses", value = "Harbour Street 4 (Kazordoon)"),
                        ListDataCardModel(key = "Titles", value = "37 unlocked"),
                        ListDataCardModel(key = "Created", value = "Mar 19, 2024"),
                        ListDataCardModel(key = "Loyalty", value = "Scout of Tibia")
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    ListDataCard(
                        modifier = Modifier.fillMaxWidth(),
                        data = data
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
}

@Composable
fun PrimaryCard(
    modifier: Modifier = Modifier
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
                            text = "T",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Kaelith Dawnbreak",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 25.sp),
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Elite Knight · Bestiary Expert",
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
                        subtitle = "216"
                    )
                    CustomCard(
                        modifier = Modifier.weight(1f),
                        title = "Achiev.",
                        subtitle = "966"
                    )
                    CustomCard(
                        modifier = Modifier.weight(1f),
                        title = "World",
                        subtitle = "Wintera"
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryCardPreview(){
    TibiaToolsTheme(darkTheme = false) {
        SearchPlayerScreen(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp))
    }
}