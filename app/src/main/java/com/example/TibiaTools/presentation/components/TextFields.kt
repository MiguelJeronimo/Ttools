package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun LevelTextField(
    label: String = "Character level",
    level: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String = ""
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = level,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = MaterialTheme.shapes.large,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
        singleLine = true,
        supportingText = {
            if (isError) {
                Text(text = errorMessage)
            }
        }
    )
}

@Composable
fun StaminaTextField(
    label: String = "Stamina time",
    level: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String = ""
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = level,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = MaterialTheme.shapes.large,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Time),
        isError = isError,
        singleLine = true,
        supportingText = {
            if (isError) {
                Text(text = errorMessage)
            }
        }
    )
}


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    label: String = "Search player",
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String = "",
    onSearchPlayer: (String) -> Unit = {}
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = label) },
        shape = MaterialTheme.shapes.extraLargeIncreased,
        supportingText = {
            if (isError) {
                Text(text = errorMessage)
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onSearchPlayer(value)
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        isError = isError,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.PersonSearch,
                contentDescription = "Search Icon"
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        singleLine = true,
    )
}

@Preview(name = "Search Text Field", showBackground = true)
@Composable
fun SearchTextFieldPreview(){
    TibiaToolsTheme(darkTheme = true) {
        SearchTextField(
            value = "",
            onValueChange = {},
            isError = false,
            errorMessage = ""
        )
    }
}