package com.example.TibiaTools.presentation.screens.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.TibiaTools.presentation.components.LevelTextField
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme
import com.example.ttools.R

@Composable
fun SharedExperiencesScreen(
    modifier: Modifier = Modifier
){
    var levelText by rememberSaveable { mutableStateOf("") }
    val level = levelText.toIntOrNull()?.takeIf { it >= 1 }
    val isError = levelText.isNotEmpty() && level == null

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LevelTextField(
            level = levelText,
            onValueChange = {
                levelText = it
            },
            isError = isError,
            errorMessage = "Invalid level (must be >= 1) and integer number"
        )

    }
}



@Composable
fun SharedExperiencesDataContainer(modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLargeIncreased.copy(all = CornerSize(35.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Text(
               text = "A level 250 can share experience with",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp),
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "MIN",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 12.sp,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    ),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
                Text(
                    text = "MAX",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 12.sp,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    ),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "2000",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 48.sp),
                    fontWeight = FontWeight.ExtraBold,
                )
                Icon(
                    contentDescription = "outline_arrow_range_24",
                    painter = painterResource(R.drawable.outline_arrow_range_24),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    text = "3000",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 48.sp),
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SharedExperiencesScreenPreview(){
    TibiaToolsTheme {
        SharedExperiencesScreen(
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SharedExperiencesDataContainerPreview(){
    TibiaToolsTheme {
        SharedExperiencesDataContainer(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
    }
}