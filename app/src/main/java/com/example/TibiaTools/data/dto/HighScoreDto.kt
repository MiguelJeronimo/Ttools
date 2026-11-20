package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class HighScoresResponseDto(
    @SerializedName("highscores")
    @SerialName("highscores")
    val highscores: HighScoreDataDto? = null,

    @SerializedName("information")
    @SerialName("information")
    val information: InformationDto? = null
)

@Keep
@Serializable
data class HighScoreDataDto(
    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("category")
    @SerialName("category")
    val category: String? = null,

    @SerializedName("vocation")
    @SerialName("vocation")
    val vocation: String? = null,

    @SerializedName("highscore_age")
    @SerialName("highscore_age")
    val highscoreAge: Int = 0,

    @SerializedName("highscore_list")
    @SerialName("highscore_list")
    val highscoreList: List<HighScoreItemDto> = emptyList(),

    @SerializedName("highscore_page")
    @SerialName("highscore_page")
    val highscorePage: HighScorePageDto? = null
)

@Keep
@Serializable
data class HighScorePageDto(
    @SerializedName("current_page")
    @SerialName("current_page")
    val currentPage: Int = 0,

    @SerializedName("total_pages")
    @SerialName("total_pages")
    val totalPages: Int = 0,

    @SerializedName("total_records")
    @SerialName("total_records")
    val totalRecords: Int = 0
)

@Keep
@Serializable
data class HighScoreItemDto(
    @SerializedName("rank")
    @SerialName("rank")
    val rank: Int = 0,

    @SerializedName("level")
    @SerialName("level")
    val level: Int = 0,

    @SerializedName("value")
    @SerialName("value")
    val value: Long = 0L,

    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("vocation")
    @SerialName("vocation")
    val vocation: String? = null,

    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("title")
    @SerialName("title")
    val title: String? = null
)
