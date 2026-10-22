package com.example.TibiaTools.data.model.HighScores.HighscoreList

import androidx.annotation.Keep

@Keep
data class HighscoreList(
    var rank: Int = 0,
    var level: Int = 0,
    var value: Float = 0f,
    var name: String? = null,
    var vocation: String? = null,
    var world: String? = null,
    var title: String? = null
)
