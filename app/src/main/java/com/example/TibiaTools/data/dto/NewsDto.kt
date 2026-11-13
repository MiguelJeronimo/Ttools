package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NewsResponseDto(
    @SerializedName("news")
    @SerialName("news")
    val news: List<NewsItemDto> = emptyList()
)

@Keep
@Serializable
data class NewsTickerResponseDto(
    @SerializedName("news")
    @SerialName("news")
    val news: List<NewsItemDto> = emptyList()
)

@Keep
@Serializable
data class NewsItemDto(
    @SerializedName("id")
    @SerialName("id")
    val id: String? = null,

    @SerializedName("date")
    @SerialName("date")
    val date: String? = null,

    @SerializedName("news")
    @SerialName("news")
    val news: String? = null,

    @SerializedName("category")
    @SerialName("category")
    val category: String? = null,

    @SerializedName("type")
    @SerialName("type")
    val type: String? = null,

    @SerializedName("url")
    @SerialName("url")
    val url: String? = null
)

@Keep
@Serializable
data class RashidDto(
    @SerializedName("location")
    @SerialName("location")
    val location: String? = null
)
