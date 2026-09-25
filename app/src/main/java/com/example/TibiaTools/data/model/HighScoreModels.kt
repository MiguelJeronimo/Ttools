package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class ApiHighScores(
    var highscores: HighScore? = null
)

@Keep
data class HighScore(
    var world: String? = null,
    var category: String? = null,
    var vocation: String? = null,
    var highscore_age: Int = 0,
    var highscore_list: ArrayList<HighscoreList> = ArrayList()
)

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
