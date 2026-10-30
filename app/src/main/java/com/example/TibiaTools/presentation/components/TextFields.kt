package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

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