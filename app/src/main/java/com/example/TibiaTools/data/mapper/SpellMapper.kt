package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.RuneInformationDataDto
import com.example.TibiaTools.data.dto.SpellDetailDto
import com.example.TibiaTools.data.dto.SpellInformationDataDto
import com.example.TibiaTools.data.dto.SpellInformationResponseDto
import com.example.TibiaTools.data.dto.SpellListItemDto
import com.example.TibiaTools.data.dto.SpellsDataDto
import com.example.TibiaTools.data.dto.SpellsResponseDto
import com.example.TibiaTools.domain.model.RuneInfoData
import com.example.TibiaTools.domain.model.SpellDetail
import com.example.TibiaTools.domain.model.SpellInfoData
import com.example.TibiaTools.domain.model.SpellInformationResponse
import com.example.TibiaTools.domain.model.SpellListItem
import com.example.TibiaTools.domain.model.SpellsData
import com.example.TibiaTools.domain.model.SpellsResponse

fun SpellsResponseDto.toDomain(): SpellsResponse = SpellsResponse(
    spells = spells?.toDomain()
)

fun SpellInformationResponseDto.toDomain(): SpellInformationResponse = SpellInformationResponse(
    spell = spell?.toDomain()
)

fun SpellsDataDto.toDomain(): SpellsData = SpellsData(
    spellsList = spellsList?.map { it.toDomain() } ?: emptyList(),
    spell = spell?.toDomain()
)

fun SpellListItemDto.toDomain(): SpellListItem = SpellListItem(
    name = name.orEmpty(),
    spellId = spellId.orEmpty(),
    formula = formula.orEmpty(),
    level = level.orEmpty(),
    mana = mana.orEmpty(),
    price = price.orEmpty(),
    isGroupAttack = groupAttack,
    isGroupHealing = groupHealing,
    isGroupSupport = groupSupport,
    isTypeInstant = typeInstant,
    isTypeRune = typeRune,
    isPremiumOnly = premiumOnly
)

fun SpellDetailDto.toDomain(): SpellDetail = SpellDetail(
    name = name.orEmpty(),
    spellId = spellId.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    description = description.orEmpty(),
    spellInformation = spellInformation?.toDomain(),
    runeInformation = runeInformation?.toDomain()
)

fun SpellInformationDataDto.toDomain(): SpellInfoData = SpellInfoData(
    vocation = vocation ?: emptyList(),
    city = city ?: emptyList(),
    damageType = damageType.orEmpty(),
    soulPoints = soulPoints,
    amount = amount,
    cooldownAlone = cooldownAlone,
    cooldownGroup = cooldownGroup
)

fun RuneInformationDataDto.toDomain(): RuneInfoData = RuneInfoData(
    vocation = vocation ?: emptyList()
)
