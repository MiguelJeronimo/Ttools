package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class ApiNews(
    var news: ArrayList<News> = ArrayList()
)

@Keep
data class ApiNewsTicker(
    var news: ArrayList<News> = ArrayList()
)

@Keep
data class News(
    var id: String? = null,
    var date: String? = null,
    var news: String? = null,
    var category: String? = null,
    var type: String? = null,
    var url: String? = null
)

@Keep
data class Rashid(
    var location: String? = null
)
