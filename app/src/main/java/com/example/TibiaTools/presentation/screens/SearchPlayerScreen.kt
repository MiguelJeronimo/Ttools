package com.example.TibiaTools.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.components.CustomCard
import com.example.TibiaTools.presentation.components.ListDataCard
import com.example.TibiaTools.presentation.components.ListDataCardModel
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun SearchPlayerScreen(
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding->
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
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
                    Spacer(modifier = Modifier.size(16.dp))
                    ListDataCard(
                        modifier = Modifier.fillMaxWidth(),
                        data = data
                    )
                }
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
            containerColor = Color(0xFF004D64),
            contentColor = Color(0xFFBDE9FF)
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
                        color = Color(0xFF67D3FF).copy(alpha = 0.16f),
                        shape = CircleShape
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-40).dp, y = 50.dp)
                    .size(90.dp)
                    .background(
                        color = Color(0xFFC6C2EA).copy(alpha = 0.20f),
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
    TibiaToolsTheme(darkTheme = true) {
        SearchPlayerScreen(modifier = Modifier.fillMaxSize().padding(10.dp))
    }
}