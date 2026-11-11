package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle:String
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 12.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.Both
                    )
                )
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            )
        }
    }
}

data class ListDataCardModel(
    val key: String,
    val value: String
)

@Composable
fun ListDataCard(
    modifier: Modifier = Modifier,
    data: List<ListDataCardModel> = emptyList()
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            data.forEachIndexed { index, model ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = model.key,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = model.value,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                }
                if (data.lastIndex != index) {
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}


@Preview(name = "Custom Card", showBackground = true)
@Composable
fun CustomCardPreview(){
    TibiaToolsTheme(darkTheme = true) {
        CustomCard(
            title = "Total Online",
            subtitle = "17,445",
            modifier = Modifier
        )
    }
}

@Preview(name = "List Data Card", showBackground = true)
@Composable
fun ListDataCardPreview(){
    TibiaToolsTheme(darkTheme = true) {
        ListDataCard(
            data = listOf(
                ListDataCardModel(key = "Total Online", value = "17,445"),
                ListDataCardModel(key = "Total Online", value = "17,445"),
                ListDataCardModel(key = "Total Online", value = "17,445"),
                ListDataCardModel(key = "Total Online", value = "17,445")
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}