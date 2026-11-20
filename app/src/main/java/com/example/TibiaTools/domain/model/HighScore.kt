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
) {
    val highscore_age: Int get() = highscoreAge
    val highscore_list: List<HighScoreItem> get() = highscoreList
    val highscore_page: HighScorePage? get() = highscorePage
}

data class HighScorePage(
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val totalRecords: Int = 0
) {
    val current_page: Int get() = currentPage
    val total_pages: Int get() = totalPages
    val total_records: Int get() = totalRecords
}

data class HighScoreItem(
    val rank: Int = 0,
    val level: Int = 0,
    val value: Long = 0L,
    val name: String = "",
    val vocation: String = "",
    val world: String = "",
    val title: String = ""
)
