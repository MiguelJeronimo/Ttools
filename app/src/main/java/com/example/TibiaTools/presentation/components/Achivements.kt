package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme


data class AchievementsData(
    val name: String,
    val grade: Int
)
//★
@Composable
fun AchievementsList(
    modifier: Modifier = Modifier,
    achievements: List<AchievementsData> = emptyList()
) {
    FlowRow(
        modifier = modifier,
    ) {
        achievements.forEach {
            AchievementsChip(
                modifier = Modifier.padding(5.dp),
                text = it.name,
                grade = it.grade
            )
        }

    }
}


@Composable
fun AchievementsChip(
    modifier: Modifier = Modifier,
    text: String,
    grade:Int
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..grade) {
                Star()
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}



@Composable
fun Star(){
    Text(
        text = "★",
        color = MaterialTheme.colorScheme.error
    )
}

@Preview(showBackground = true)
@Composable
fun AchievementsListPreview() {
    val achievements = listOf(
        AchievementsData("Achievement 1", 3),
        AchievementsData("Achievement 2", 5),
        AchievementsData("Achievement 3", 2)
    )
    TibiaToolsTheme(darkTheme = true) {
        AchievementsList(
            modifier = Modifier.width(300.dp),
            achievements = achievements
        )
    }
}