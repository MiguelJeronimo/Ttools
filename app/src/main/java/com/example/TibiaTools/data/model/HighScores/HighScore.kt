package com.example.TibiaTools.data.model.HighScores

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.HighScores.HighscoreList.HighscoreList

@Keep
data class HighScore(
    var world: String? = null,
    var category: String? = null,
    var vocation: String? = null,
    var highscore_age: Int = 0,
    var highscore_list: ArrayList<HighscoreList> = ArrayList()
)
