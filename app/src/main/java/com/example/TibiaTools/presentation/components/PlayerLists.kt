package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme



data class PlayerInfo (
    val rank: Int,
    val name: String,
    val vocation: String,
    val world:String,
    val level: Int,
    val value: Long
)
@Composable
fun PlayerLists(
    modifier: Modifier = Modifier,
    playerInfo: PlayerInfo = PlayerInfo(
        rank = 1,
        name = "Lunatic Princess",
        vocation = "EK",
        world = "Celesta",
        level = 2418,
        value = 235267
    )
){
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${playerInfo.rank}",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Column(modifier = Modifier.fillMaxWidth().weight(0.5f)) {
            Text(
                text = playerInfo.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${playerInfo.vocation} - ${playerInfo.world}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().weight(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Lv ${playerInfo.level}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${playerInfo.value}M xp",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlayerListPreview(){
    TibiaToolsTheme(darkTheme = true) {
        PlayerLists(modifier = Modifier.fillMaxWidth().padding(10.dp))
    }
}