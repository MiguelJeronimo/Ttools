package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

@Composable
fun Loader(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularWavyProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}


@Composable
fun CustomLinearWavyProgressIndicator(){
    LinearWavyProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.tertiary
    )
}

@Preview(showBackground = true)
@Composable
fun LoaderPreview(){
    TibiaToolsTheme(darkTheme = true) {
        Loader()
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLinearWavyProgressIndicatorPreview(){
    TibiaToolsTheme(darkTheme = true) {
        CustomLinearWavyProgressIndicator()
    }
}