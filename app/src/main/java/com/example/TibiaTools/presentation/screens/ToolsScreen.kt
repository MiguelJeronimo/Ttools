package com.example.TibiaTools.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.presentation.components.ButtonSection
import com.example.TibiaTools.presentation.components.SectionOption
import com.example.TibiaTools.presentation.screens.tools.BlessingScreen

@Composable
fun ToolsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
){
    val sectionOptions = listOf(
        SectionOption(id = 1, title = "Exp Shared"),
        SectionOption(id = 2, title = "Stamina"),
        SectionOption(id = 3, title = "Blessings")
    )
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Tools",
                style = MaterialTheme.typography.displayLargeEmphasized,
                fontWeight = FontWeight.ExtraBold,
            )
            ButtonSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                options = sectionOptions
            ) { option ->
                when (option.id) {
                    3 -> BlessingScreen(modifier = Modifier.fillMaxWidth())
                    else -> {
                        BlessingScreen(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToolsScreenPreview() {
    ToolsScreen()
}