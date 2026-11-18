package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

data class FilterData(
 val id: Int,
 val text: Int
)

@Composable
fun FilterList(
    modifier: Modifier = Modifier,
    label: String = "Filter",
    options: List<FilterData> = emptyList(),
    onSelectOption: (option: FilterData) -> Unit = {}
){
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = { Text(text = label) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.text.toString()
                        )
                    },
                    onClick = {
                        selectedText = option.text.toString()
                        onSelectOption(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(name = "FilterList Light Preview", showBackground = true)
@Composable
fun FilterListPreview() {
    TibiaToolsTheme(darkTheme = false) {
        FilterList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = "Category",
            options = listOf(
                FilterData(id = 1, text = 2024),
                FilterData(id = 2, text = 2025),
                FilterData(id = 3, text = 2026)
            )
        )
    }
}

@Preview(name = "FilterList Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun FilterListDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        FilterList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = "Category",
            options = listOf(
                FilterData(id = 1, text = 2024),
                FilterData(id = 2, text = 2025),
                FilterData(id = 3, text = 2026)
            )
        )
    }
}
