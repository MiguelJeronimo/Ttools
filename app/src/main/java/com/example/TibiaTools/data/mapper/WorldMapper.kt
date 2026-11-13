package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.RegularWorldDto
import com.example.TibiaTools.data.dto.WorldsDataDto
import com.example.TibiaTools.data.dto.WorldsResponseDto
import com.example.TibiaTools.domain.model.RegularWorld
import com.example.TibiaTools.domain.model.WorldsData
import com.example.TibiaTools.domain.model.WorldsResponse

fun WorldsResponseDto.toDomain(): WorldsResponse = WorldsResponse(
    worlds = worlds?.toDomain()
)

fun WorldsDataDto.toDomain(): WorldsData = WorldsData(
    playersOnline = playersOnline,
    recordDate = recordDate.orEmpty(),
    regularWorlds = regularWorlds.map { it.toDomain() }
)

fun RegularWorldDto.toDomain(): RegularWorld = RegularWorld(
    name = name.orEmpty(),
    status = status.orEmpty(),
    playersOnline = playersOnline,
    location = location.orEmpty(),
    pvpType = pvpType.orEmpty(),
    isPremiumOnly = premiumOnly,
    transferType = transferType.orEmpty(),
    isBattleyeProtected = battleyeProtected,
    battleyeDate = battleyeDate.orEmpty(),
    gameWorldType = gameWorldType.orEmpty(),
    tournamentWorldType = tournamentWorldType.orEmpty()
)
