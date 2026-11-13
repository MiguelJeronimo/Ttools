package com.example.TibiaTools.domain.model

typealias News = NewsItem

data class NewsResponse(
    val news: List<NewsItem> = emptyList()
)

data class NewsTickerResponse(
    val news: List<NewsItem> = emptyList()
)

data class NewsItem(
    val id: String = "",
    val date: String = "",
    val news: String = "",
    val category: String = "",
    val type: String = "",
    val url: String = ""
)

data class RashidInfo(
    val location: String = ""
)
