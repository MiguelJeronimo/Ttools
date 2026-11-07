package com.example.TibiaTools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.TibiaTools.presentation.MainScreen
import com.example.TibiaTools.presentation.theme.TibiaToolsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TibiaToolsTheme {
                MainScreen()
            }
        }
    }
}
