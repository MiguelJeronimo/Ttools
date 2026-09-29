package com.example.TibiaTools.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.TibiaTools.View.ViewModel.ViewModelHome
import com.example.TibiaTools.data.model.News
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import java.util.Calendar

data class QuickToolItem(
    val title: String,
    val icon: ImageVector,
    val containerColor: Color,
    val contentColor: Color,
    val onClick: () -> Unit
)

@Composable
fun HomeScreen(
    viewModel: ViewModelHome,
    onMenuClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onWorldsClick: () -> Unit = {},
    onToolClick: (String) -> Unit = {},
    onNewsClick: (News) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val worldsState by viewModel.worlds.observeAsState()
    val playersOnlineState by viewModel.playersOnline.observeAsState()
    val rashidState by viewModel.rashidLocation.observeAsState()
    val creatureBossState by viewModel.creatureBoss.observeAsState()
    val bostedBossState by viewModel.bostedBoss.observeAsState()
    val newsState by viewModel.news.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.setWorlds()
        viewModel.setPlayersOnline()
        viewModel.setRashirLocation()
        viewModel.setCreatureBoss()
        viewModel.setBostedBoss()
        viewModel.setNews()
    }

    val totalOnline = playersOnlineState?.players_online ?: worldsState?.players_online ?: 0
    val totalWorlds = worldsState?.regular_worlds?.size ?: 0
    val rashidCity = rashidState ?: "Carlin"
    val boostedCreatureName = creatureBossState?.boosted?.name ?: "Dragon"
    val boostedCreatureImage = creatureBossState?.boosted?.image_url ?: "https://static.tibia.com/images/library/dragon.gif"
    val boostedBossName = bostedBossState?.boosted?.name ?: "Ferumbras"
    val boostedBossImage = bostedBossState?.boosted?.image_url ?: "https://static.tibia.com/images/library/ferumbras.gif"
    val newsList = newsState?.news ?: emptyList()

    HomeContent(
        totalOnline = totalOnline,
        totalWorlds = totalWorlds,
        rashidCity = rashidCity,
        boostedCreatureName = boostedCreatureName,
        boostedCreatureImage = boostedCreatureImage,
        boostedBossName = boostedBossName,
        boostedBossImage = boostedBossImage,
        newsList = newsList,
        onMenuClick = onMenuClick,
        onSearchClick = onSearchClick,
        onWorldsClick = onWorldsClick,
        onToolClick = onToolClick,
        onNewsClick = onNewsClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    totalOnline: Int,
    totalWorlds: Int,
    rashidCity: String,
    boostedCreatureName: String,
    boostedCreatureImage: String,
    boostedBossName: String,
    boostedBossImage: String,
    newsList: List<News>,
    onMenuClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onWorldsClick: () -> Unit = {},
    onToolClick: (String) -> Unit = {},
    onNewsClick: (News) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
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
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSearchClick) {
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
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Hero Card - Players Online
            item {
                PlayersOnlineHeroCard(
                    totalOnline = totalOnline,
                    totalWorlds = totalWorlds,
                    onWorldsClick = onWorldsClick
                )
            }

            // 2. Rashid Today Card
            item {
                RashidTodayCard(city = rashidCity)
            }

            // 3. Boosted Creature & Boss Section (2 Columns)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BoostedCard(
                        category = "BOOSTED CREATURE",
                        name = boostedCreatureName,
                        imageUrl = boostedCreatureImage,
                        categoryColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )
                    BoostedCard(
                        category = "BOOSTED BOSS",
                        name = boostedBossName,
                        imageUrl = boostedBossImage,
                        categoryColor = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // 4. Quick Tools Section
            item {
                QuickToolsSection(onToolClick = onToolClick)
            }

            // 5. Latest News Section Header
            item {
                Text(
                    text = "Latest news",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                )
            }

            // News Items
            items(newsList.ifEmpty { sampleNewsList }) { newsItem ->
                NewsCard(news = newsItem, onClick = { onNewsClick(newsItem) })
            }
        }
    }
}

@Composable
fun PlayersOnlineHeroCard(
    totalOnline: Int,
    totalWorlds: Int,
    onWorldsClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF004D64), // Expressive Hero background
            contentColor = Color(0xFFBDE9FF)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .clickable { onWorldsClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Background Decorative Blob 1 (Top-Right)
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

            // Background Decorative Blob 2 (Bottom-Right)
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

            // Card Foreground Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Indicator Dot + Label
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF5BD68A), CircleShape)
                    )
                    Text(
                        text = "PLAYERS ONLINE",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp,
                        color = Color(0xFFBDE9FF).copy(alpha = 0.85f)
                    )
                }

                // Online Number
                Text(
                    text = String.format("%,d", if (totalOnline > 0) totalOnline else 17445),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFBDE9FF),
                    letterSpacing = (-1).sp
                )

                // Chips Row
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Worlds Chip
                    Box(
                        modifier = Modifier
                            .height(28.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF191C1E))
                            .clickable { onWorldsClick() }
                            .padding(horizontal = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Public,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color(0xFFE1E2E4)
                            )
                            Text(
                                text = "${if (totalWorlds > 0) totalWorlds else 96} worlds",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFE1E2E4)
                            )
                        }
                    }

                    // Server Save Chip
                    Box(
                        modifier = Modifier
                            .height(28.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF191C1E))
                            .padding(horizontal = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color(0xFFE1E2E4)
                            )
                            Text(
                                text = "Server save in ${getServerSaveTime()}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFE1E2E4)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RashidTodayCard(city: String) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Storefront,
                    contentDescription = "Rashid",
                    tint = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier.size(26.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Rashid today",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.8f)
                )
                Text(
                    text = city,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            Text(
                text = getTodayDayName(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun BoostedCard(
    category: String,
    name: String,
    imageUrl: String,
    categoryColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = categoryColor,
                letterSpacing = 0.5.sp
            )

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(56.dp)
                )
            }

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun QuickToolsSection(onToolClick: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Quick tools",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val tools = listOf(
                QuickToolItem("Exp share", Icons.Default.Group, MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.onPrimaryContainer) { onToolClick("exp") },
                QuickToolItem("Stamina", Icons.Default.Schedule, MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.colorScheme.onSecondaryContainer) { onToolClick("stamina") },
                QuickToolItem("Blessings", Icons.Default.Star, MaterialTheme.colorScheme.tertiaryContainer, MaterialTheme.colorScheme.onTertiaryContainer) { onToolClick("bless") },
                QuickToolItem("Map", Icons.Default.Map, MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant) { onToolClick("map") }
            )

            tools.forEach { tool ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { tool.onClick() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(tool.containerColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = tool.icon,
                            contentDescription = tool.title,
                            tint = tool.contentColor,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Text(
                        text = tool.title,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun NewsCard(
    news: News,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Campaign,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${news.category ?: "News"} · ${news.date ?: ""}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
                Text(
                    text = news.news ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private fun getServerSaveTime(): String {
    val cal = Calendar.getInstance()
    val hour = cal.get(Calendar.HOUR_OF_DAY)
    val minute = cal.get(Calendar.MINUTE)
    val targetHour = 10
    var hoursLeft = targetHour - hour
    var minutesLeft = 0 - minute
    if (minutesLeft < 0) {
        minutesLeft += 60
        hoursLeft -= 1
    }
    if (hoursLeft < 0) {
        hoursLeft += 24
    }
    return "${hoursLeft}h ${minutesLeft}m"
}

private fun getTodayDayName(): String {
    val cal = Calendar.getInstance()
    return when (cal.get(Calendar.DAY_OF_WEEK)) {
        Calendar.SUNDAY -> "Sunday"
        Calendar.MONDAY -> "Monday"
        Calendar.TUESDAY -> "Tuesday"
        Calendar.WEDNESDAY -> "Wednesday"
        Calendar.THURSDAY -> "Thursday"
        Calendar.FRIDAY -> "Friday"
        Calendar.SATURDAY -> "Saturday"
        else -> ""
    }
}

private val sampleNewsList = listOf(
    News(id = "1", date = "2024-03-01", news = "Winterlight Solstice is coming to Tibia!", category = "community", type = "news"),
    News(id = "2", date = "2024-03-02", news = "Extended Double XP and Skill Event announced.", category = "community", type = "news"),
    News(id = "3", date = "2024-03-03", news = "New Server Save times and maintenance window update.", category = "technical", type = "news")
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    TibiaToolsTheme(darkTheme = true) {
        HomeContent(
            totalOnline = 17445,
            totalWorlds = 96,
            rashidCity = "Carlin",
            boostedCreatureName = "Dragon",
            boostedCreatureImage = "https://static.tibia.com/images/library/dragon.gif",
            boostedBossName = "Ferumbras",
            boostedBossImage = "https://static.tibia.com/images/library/ferumbras.gif",
            newsList = sampleNewsList
        )
    }
}
