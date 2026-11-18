package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.FlexBox
import androidx.compose.foundation.layout.FlexDirection
import androidx.compose.foundation.layout.FlexWrap
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@OptIn(ExperimentalFlexBoxApi::class)
@Composable
fun SingleSelectButton(
    options: List<String> = emptyList(),
    option: (option: String) -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    FlexBox(
        modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(),
        config = {
            direction(FlexDirection.Row)
            wrap(FlexWrap.Wrap)
            columnGap(ButtonGroupDefaults.ConnectedSpaceBetween)
            rowGap(2.dp)
        },
    ) {
        options.forEachIndexed { index, label ->
            ToggleButton(
                checked = selectedIndex == index,
                onCheckedChange = { selectedIndex = index },
                shapes =
                    when (index) {
                        0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                        options.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                        else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                    },
                modifier = Modifier.semantics { role = Role.RadioButton },
            ) {
                Text(label)
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


@Preview(name = "SingleSelectButton Light Preview", showBackground = true)
@Composable
fun SingleSelectButtonPreview() {
    TibiaToolsTheme(darkTheme = false) {
        SingleSelectButton(options = listOf("All", "EK", "RP", "MS", "ED"))
    }
}

@Preview(name = "SingleSelectButton Dark Preview", showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun SingleSelectButtonDarkPreview() {
    TibiaToolsTheme(darkTheme = true) {
        SingleSelectButton(options = listOf("All", "EK", "RP", "MS", "ED"))
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
