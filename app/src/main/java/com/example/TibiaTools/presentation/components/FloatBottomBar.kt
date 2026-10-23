package com.example.TibiaTools.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ttools.R

data class MainItemModel(
    val route: String,
    val title: String? = null,
    @StringRes val titleRes: Int? = null,
    val icon: ImageVector? = null,
    @DrawableRes val drawableRes: Int? = null
)

val list = listOf(
    MainItemModel(
        title = "Home",
        icon = Icons.Default.Home,
        route = "home"
    ),
    MainItemModel(
        titleRes = R.string.nd_character,
        drawableRes = R.drawable.character_icon,
        route = "players"
    ),
    MainItemModel(
        title = "Ranks",
        drawableRes = R.drawable.icon_highscores,
        route = "ranks"
    ),
    MainItemModel(
        title = "Library",
        icon = Icons.Outlined.MenuBook,
        route = "library"
    ),

    MainItemModel(
        title = "Tools",
        icon = Icons.Outlined.Calculate,
        route = "tools"
    )
)

@Composable
fun MainBottonBar(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
){
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = modifier
    ) {
        list.forEachIndexed { index, model ->
            val labelText = when {
                model.titleRes != null -> stringResource(id = model.titleRes)
                model.title != null -> model.title
                else -> ""
            }

            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    onNavigate(model.route)
                },
                label = {
                    Text(
                        text = labelText
                    )
                },
                icon = {
                    when {
                        model.icon != null -> {
                            Icon(
                                imageVector = model.icon,
                                contentDescription = labelText
                            )
                        }
                        model.drawableRes != null -> {
                            Icon(
                                painter = painterResource(id = model.drawableRes),
                                contentDescription = labelText
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MainBottomBarPreview(){
    MainBottonBar(modifier = Modifier.fillMaxWidth())
}
