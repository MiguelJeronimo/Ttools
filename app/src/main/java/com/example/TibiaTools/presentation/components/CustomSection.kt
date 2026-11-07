package com.example.TibiaTools.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


data class SectionOption(
    val id: Int,
    val title: String
)

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    options: List<SectionOption> = emptyList(),
    section: @Composable (option: SectionOption) -> Unit
){
    var selectedIndex by remember { mutableIntStateOf(0) }
    Log.d("CustomSectionTAG", "Options: $options")
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (options.isNotEmpty()) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEachIndexed { index, option ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = {
                            selectedIndex = index
                        },
                        selected = index == selectedIndex,
                        label = {
                            Text(
                                text = option.title,
                                maxLines = 1
                            )
                        }
                    )
                }
            }
            if (selectedIndex in options.indices) {
                section(options[selectedIndex])
            }
        }
    }
}