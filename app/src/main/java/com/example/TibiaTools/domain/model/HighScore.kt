package com.example.TibiaTools.domain.model

data class HighScoresResponse(
    val highscores: HighScoreData? = null,
    val information: InformationInfo? = null
)

data class HighScoreData(
    val world: String = "",
    val category: String = "",
    val vocation: String = "",
    val highscoreAge: Int = 0,
    val highscoreList: List<HighScoreItem> = emptyList(),
    val highscorePage: HighScorePage? = null
)
data class HighScorePage(
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val totalRecords: Int = 0
)

data class HighScoreItem(
    val rank: Int = 0,
    val level: Int = 0,
    val value: Long = 0L,
    val name: String = "",
    val vocation: String = "",
    val world: String = "",
    val title: String = ""
)
