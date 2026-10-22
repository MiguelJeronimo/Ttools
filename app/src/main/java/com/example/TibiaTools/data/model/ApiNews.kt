package com.example.TibiaTools.data.model

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.news.News

@Keep
data class ApiNews(
    var news: ArrayList<News> = ArrayList()
)
