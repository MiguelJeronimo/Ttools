package com.example.TibiaTools.domain.model

data class HighScoresResponse(
    val highscores: HighScoreData? = null
)

data class HighScoreData(
    val world: String = "",
    val category: String = "",
    val vocation: String = "",
    val highscoreAge: Int = 0,
    val highscoreList: List<HighScoreItem> = emptyList()
) {
    val highscore_age: Int get() = highscoreAge
    val highscore_list: List<HighScoreItem> get() = highscoreList
}

data class HighScoreItem(
    val rank: Int = 0,
    val level: Int = 0,
    val value: Float = 0f,
    val name: String = "",
    val vocation: String = "",
    val world: String = "",
    val title: String = ""
)
