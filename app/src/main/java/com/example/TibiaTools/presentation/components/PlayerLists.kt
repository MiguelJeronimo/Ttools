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

@Composable
fun PlayerLists(
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "4",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Column(modifier = Modifier.fillMaxWidth().weight(0.5f)) {
            Text(
                text = "Lunatic Princess",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "EK * Celesta",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().weight(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Lv 2418",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "235267.7M xp",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)
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