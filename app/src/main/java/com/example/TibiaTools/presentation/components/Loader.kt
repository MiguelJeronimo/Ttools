package com.example.TibiaTools.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoaderPReview(){
    TibiaToolsTheme(darkTheme = true) {
        Loader()
    }
}