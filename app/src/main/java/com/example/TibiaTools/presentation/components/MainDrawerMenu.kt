package com.example.TibiaTools.presentation.components

import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ttools.R

@Composable
fun MainDrawerMenu(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onBlessClick: () -> Unit = {},
    onPlayerClick: () -> Unit = {},
    onGuildsClick: () -> Unit = {},
    onHighScoresClick: () -> Unit = {},
    onHousesClick: () -> Unit = {},
    onSharedExperienceClick: () -> Unit = {},
    onStaminaClick: () -> Unit = {},
    onWorldsClick: () -> Unit = {},
    onCreaturesClick: () -> Unit = {},
    onSpellsClick: () -> Unit = {},
    onMapsClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    content: @Composable () -> Unit
){
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(320.dp)
                    .fillMaxHeight(),
                drawerShape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp),
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                Column(
                    modifier = Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .verticalScroll(rememberScrollState())
                ) {
                    // Header Section matching TibiaTools.dc.html design
                    Column(
                        modifier = Modifier.padding(start = 28.dp, end = 28.dp, top = 24.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        AsyncImage(
                            model = R.mipmap.ic_launcher,
                            contentDescription = "App Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = "TibiaTools",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "Support tool for Tibia Online",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_bless),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.icon_bless), contentDescription = null) },
                        selected = false,
                        onClick = onBlessClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_character),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.character_icon), contentDescription = null) },
                        selected = false,
                        onClick = onPlayerClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_guild),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.guild_icon), contentDescription = null) },
                        selected = false,
                        onClick = onGuildsClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_highscores),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.icon_highscores), contentDescription = null) },
                        selected = false,
                        onClick = onHighScoresClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_houses),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.house_icon), contentDescription = null) },
                        selected = false,
                        onClick = onHousesClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_experiencia_compartida),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.exp_compartida_icon), contentDescription = null) },
                        selected = false,
                        onClick = onSharedExperienceClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_stamina),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.stamina_icon), contentDescription = null) },
                        selected = false,
                        onClick = onStaminaClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_mundos),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.words_icon), contentDescription = null) },
                        selected = false,
                        onClick = { /* Handle click */ },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_criaturas),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.criatures_icon), contentDescription = null) },
                        selected = false,
                        onClick = onCreaturesClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_spells),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.icon_spells), contentDescription = null) },
                        selected = false,
                        onClick = onSpellsClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_maps),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.icon_map), contentDescription = null) },
                        selected = false,
                        onClick = onMapsClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = stringResource(R.string.nd_about),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        icon = { Icon(painter = painterResource(R.drawable.icon_about), contentDescription = null) },
                        selected = false,
                        onClick = onAboutClick,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        content = content
    )
}
