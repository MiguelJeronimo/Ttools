package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.BoostableBossesDataDto
import com.example.TibiaTools.data.dto.BoostableBossesResponseDto
import com.example.TibiaTools.data.dto.BoostedDataDto
import com.example.TibiaTools.data.dto.CreatureDetailDto
import com.example.TibiaTools.data.dto.CreatureInformationResponseDto
import com.example.TibiaTools.data.dto.CreatureListItemDto
import com.example.TibiaTools.data.dto.CreaturesDataDto
import com.example.TibiaTools.data.dto.CreaturesResponseDto
import com.example.TibiaTools.domain.model.BoostableBossesData
import com.example.TibiaTools.domain.model.BoostableBossesResponse
import com.example.TibiaTools.domain.model.BoostedData
import com.example.TibiaTools.domain.model.CreatureDetail
import com.example.TibiaTools.domain.model.CreatureInformationResponse
import com.example.TibiaTools.domain.model.CreatureListItem
import com.example.TibiaTools.domain.model.CreaturesData
import com.example.TibiaTools.domain.model.CreaturesResponse

fun CreaturesResponseDto.toDomain(): CreaturesResponse = CreaturesResponse(
    creatures = creatures?.toDomain(),
    creaturesList = creaturesList?.map { it.toDomain() }
)

fun CreatureInformationResponseDto.toDomain(): CreatureInformationResponse = CreatureInformationResponse(
    creature = creature?.toDomain()
)

fun BoostableBossesResponseDto.toDomain(): BoostableBossesResponse = BoostableBossesResponse(
    boostableBosses = boostableBosses?.toDomain()
)

fun CreaturesDataDto.toDomain(): CreaturesData = CreaturesData(
    boosted = boosted?.toDomain(),
    creatureList = creatureList.map { it.toDomain() }
)

fun CreatureListItemDto.toDomain(): CreatureListItem = CreatureListItem(
    name = name.orEmpty(),
    race = race.orEmpty(),
    imageUrl = imageUrl.orEmpty()
)

fun CreatureDetailDto.toDomain(): CreatureDetail = CreatureDetail(
    name = name.orEmpty(),
    race = race.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    description = description.orEmpty(),
    behaviour = behaviour.orEmpty(),
    hitpoints = hitpoints.orEmpty(),
    experiencePoints = experiencePoints.orEmpty(),
    lootList = lootList,
    immune = immune,
    strong = strong,
    weakness = weakness
)

fun BoostableBossesDataDto.toDomain(): BoostableBossesData = BoostableBossesData(
    boosted = boosted?.toDomain()
)

fun BoostedDataDto.toDomain(): BoostedData = BoostedData(
    name = name.orEmpty(),
    race = race.orEmpty(),
    imageUrl = imageUrl.orEmpty()
)
