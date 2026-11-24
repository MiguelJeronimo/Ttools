package com.example.TibiaTools.presentation.components

import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.TibiaTools.presentation.routes.OptionsTab
import com.example.TibiaTools.presentation.routes.TabDestination
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme


@Composable
fun <T> CustomTab(
    modifier: Modifier = Modifier,
    destinations: List<T> = emptyList(),
    selectDestination: T,
    onSelected: (T)-> Unit = {}
) where T: Enum<T>, T: TabDestination {
    PrimaryTabRow(
        modifier = modifier,
        selectedTabIndex = selectDestination.ordinal
    ){
        destinations.forEach { destination ->
            Tab(
                selected = selectDestination == destination,
                onClick = { onSelected(destination) },
                text = { Text(destination.nameDestination) }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CustomTabPreview(){
    TibiaToolsTheme(darkTheme = true) {
        CustomTab(
            destinations = OptionsTab.entries,
            selectDestination = OptionsTab.CREATURES
        )
    }
}