package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.NewsItemDto
import com.example.TibiaTools.data.dto.NewsResponseDto
import com.example.TibiaTools.data.dto.NewsTickerResponseDto
import com.example.TibiaTools.domain.model.NewsItem
import com.example.TibiaTools.domain.model.NewsResponse
import com.example.TibiaTools.domain.model.NewsTickerResponse

fun NewsResponseDto.toDomain(): NewsResponse = NewsResponse(
    news = news.map { it.toDomain() }
)

fun NewsTickerResponseDto.toDomain(): NewsTickerResponse = NewsTickerResponse(
    news = news.map { it.toDomain() }
)

fun NewsItemDto.toDomain(): NewsItem = NewsItem(
    id = id.orEmpty(),
    date = date.orEmpty(),
    news = news.orEmpty(),
    category = category.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty()
)
