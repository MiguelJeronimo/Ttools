package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.HighScoreDataDto
import com.example.TibiaTools.data.dto.HighScoreItemDto
import com.example.TibiaTools.data.dto.HighScoresResponseDto
import com.example.TibiaTools.domain.model.HighScoreData
import com.example.TibiaTools.domain.model.HighScoreItem
import com.example.TibiaTools.domain.model.HighScoresResponse

fun HighScoresResponseDto.toDomain(): HighScoresResponse = HighScoresResponse(
    highscores = highscores?.toDomain()
)

fun HighScoreDataDto.toDomain(): HighScoreData = HighScoreData(
    world = world.orEmpty(),
    category = category.orEmpty(),
    vocation = vocation.orEmpty(),
    highscoreAge = highscoreAge,
    highscoreList = highscoreList.map { it.toDomain() }
)

fun HighScoreItemDto.toDomain(): HighScoreItem = HighScoreItem(
    rank = rank,
    level = level,
    value = value,
    name = name.orEmpty(),
    vocation = vocation.orEmpty(),
    world = world.orEmpty(),
    title = title.orEmpty()
)
