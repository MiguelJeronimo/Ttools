package com.example.TibiaTools.data.model

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.highscores.HighScore

@Keep
data class ApiHighScores(
    var highscores: HighScore? = null
)
