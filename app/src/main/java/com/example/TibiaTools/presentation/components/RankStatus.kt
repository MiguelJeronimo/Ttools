package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

data class PodiumPlayer(
    val rank: Int, // 1, 2, 3
    val name: String,
    val level: Long,
    val vocation: String,
    val value: Long = 0L
)

@Composable
fun TopPodiumSection(
    modifier: Modifier = Modifier,
    players: List<PodiumPlayer>
) {
    val centerPlayer = players.getOrNull(0) // 1er puesto (Centro)
    val leftPlayer = players.getOrNull(1)   // 2do puesto (Izquierda)
    val rightPlayer = players.getOrNull(2)  // 3er puesto (Derecha)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        // 2nd Place (Left)
        if (leftPlayer != null) {
            PodiumCardItem(
                modifier = Modifier.weight(1f),
                player = leftPlayer,
                cardHeight = 145.dp,
                badgeColor = MaterialTheme.colorScheme.tertiary,
                badgeTextColor = MaterialTheme.colorScheme.background,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.tertiary
            )
        }

        // 1st Place (Center - Higher)
        if (centerPlayer != null) {
            PodiumCardItem(
                modifier = Modifier.weight(1.1f),
                player = centerPlayer,
                cardHeight = 175.dp,
                badgeColor = MaterialTheme.colorScheme.primary,
                badgeTextColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        // 3rd Place (Right)
        if (rightPlayer != null) {
            PodiumCardItem(
                modifier = Modifier.weight(1f),
                player = rightPlayer,
                cardHeight = 130.dp,
                badgeColor = MaterialTheme.colorScheme.secondary,
                badgeTextColor = MaterialTheme.colorScheme.onSecondary,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
fun PodiumCardItem(
    modifier: Modifier = Modifier,
    player: PodiumPlayer,
    cardHeight: androidx.compose.ui.unit.Dp,
    badgeColor: Color,
    badgeTextColor: Color,
    containerColor: Color,
    contentColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Badge numérico flotante arriba
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    color = badgeColor,
                    shape = RoundedCornerShape(topStart = 14.dp, topEnd = 16.dp, bottomEnd = 12.dp, bottomStart = 18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = player.rank.toString(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = badgeTextColor
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del personaje
        Text(
            text = player.name,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Tarjeta del podio
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Lv ${player.level}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = if (player.rank == 1) 16.sp else 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = contentColor,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = player.vocation,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = contentColor.copy(alpha = 0.8f),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                if (player.value > 0L) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${player.value}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = contentColor.copy(alpha = 0.7f),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(name = "Top Podium Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun TopPodiumSectionPreview() {
    TibiaToolsTheme(darkTheme = true) {
        TopPodiumSection(
            players = listOf(
                PodiumPlayer(rank = 1, name = "Elder Reno", level = 2792, vocation = "ED", value = 220584452482L),
                PodiumPlayer(rank = 2, name = "King Danter", level = 2701, vocation = "EK", value = 185177220100L),
                PodiumPlayer(rank = 3, name = "Venoox", level = 2610, vocation = "RP", value = 175524313572L)
            )
        )
    }
}
