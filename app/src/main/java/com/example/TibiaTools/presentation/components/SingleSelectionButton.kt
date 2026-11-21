package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun SingleSelectButton(
    modifier: Modifier = Modifier,
    options: List<String> = emptyList(),
    option: (option: String) -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier
    ) {
        options.forEachIndexed { index, label ->
            ToggleButton(
                checked = selectedIndex == index,
                onCheckedChange = {
                    selectedIndex = index
                    option(label)
                },
                shapes = when (index) {
                    0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                    options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                    else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                },
                modifier = Modifier
                    .weight(1f)
                    .semantics { role = Role.RadioButton },
            ) {
                Text(
                    text = label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun SingleChoiceSegmentedButton(
    modifier: Modifier = Modifier,
    onClick: (options: String) -> Unit = {},
    options: List<String> = emptyList()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    selectedIndex = index
                    onClick(options[index])
                },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}

@Composable
fun SingleChipSelector(
    modifier: Modifier = Modifier,
    options: List<String> = emptyList(),
    onClick: (option: String) -> Unit = {}
){
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(options.size) { index ->
            FilterChip(
                label = { Text(options[index]) },
                onClick = {
                    onClick(options[index])
                    selectedIndex = index
                },
                selected = index == selectedIndex,
                leadingIcon = if (index == selectedIndex) {
                    {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Check),
                            contentDescription = "Selected",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
            )
        }
    }
}

@Preview(name = "SingleSelectButton Light Preview", showBackground = true)
@Composable
fun SingleSelectButtonPreview() {
    TibiaToolsTheme(darkTheme = false) {
        SingleSelectButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            options = listOf("All", "EK", "RP", "MS", "ED")
        )
    }
}

@Preview(name = "SingleSelectButton Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun SingleSelectButtonDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        SingleSelectButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            options = listOf("All", "EK", "RP", "MS", "ED")
        )
    }
}

@Preview(name = "SingleChoiceSegmentedButton Light Preview", showBackground = true)
@Composable
fun SingleChoiceSegmentedButtonPreview() {
    TibiaToolsTheme(darkTheme = false) {
        SingleChoiceSegmentedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            options = listOf("All", "EK", "RP", "MS", "ED")
        )
    }
}

@Preview(name = "SingleChoiceSegmentedButton Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun SingleChoiceSegmentedButtonDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        SingleChoiceSegmentedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            options = listOf("All", "EK", "RP", "MS", "ED")
        )
    }
}

@Preview(name = "SingleChipSelector Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun SingleChipSelectorPreview() {
    TibiaToolsTheme(darkTheme = true) {
        SingleChipSelector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            options = listOf("Experience", "Magic level", "Sword", "Axe", "Distances")
        )
    }
}

