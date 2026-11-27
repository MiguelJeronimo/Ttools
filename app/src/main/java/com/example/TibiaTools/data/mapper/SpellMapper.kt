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
import com.example.TibiaTools.presentation.routes.GroupSpell

fun SpellsResponseDto.toDomain(): SpellsResponse = SpellsResponse(
    spells = spells?.toDomain(),
    information = information?.toDomain()
)

fun SpellInformationResponseDto.toDomain(): SpellInformationResponse = SpellInformationResponse(
    spell = spell?.toDomain(),
    information = information?.toDomain()
)

fun SpellsDataDto.toDomain(): SpellsData = SpellsData(
    spellsList = spellsList?.map { it.toDomain() } ?: emptyList(),
    spellFilter = spellFilter
)

fun SpellListItemDto.toDomain(): SpellListItem {
    val type = when {
        groupAttack -> GroupSpell.ATTACK
        groupHealing -> GroupSpell.HEALING
        groupSupport -> GroupSpell.SUPPORT
        else -> GroupSpell.UNKNOW
    }

    return SpellListItem(
        name = name.orEmpty(),
        spellId = spellId.orEmpty(),
        formula = formula.orEmpty(),
        level = level.toString(),
        mana = mana.toString(),
        price = price.toString(),
        isGroupAttack = groupAttack,
        isGroupHealing = groupHealing,
        isGroupSupport = groupSupport,
        isTypeInstant = typeInstant,
        isTypeRune = typeRune,
        isPremiumOnly = premiumOnly,
        type = type
    )
}

fun SpellDetailDto.toDomain(): SpellDetail = SpellDetail(
    name = name.orEmpty(),
    spellId = spellId.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    description = description.orEmpty(),
    hasSpellInformation = hasSpellInformation,
    spellInformation = spellInformation?.toDomain(),
    hasRuneInformation = hasRuneInformation,
    runeInformation = runeInformation?.toDomain()
)

fun SpellInformationDataDto.toDomain(): SpellInfoData = SpellInfoData(
    vocation = vocation ?: emptyList(),
    city = city ?: emptyList(),
    damageType = damageType.orEmpty(),
    soulPoints = soulPoints,
    amount = amount,
    cooldownAlone = cooldownAlone,
    cooldownGroup = cooldownGroup,
    formula = formula.orEmpty(),
    level = level.toString(),
    mana = mana.toString(),
    price = price.toString(),
    isGroupAttack = groupAttack,
    isGroupHealing = groupHealing,
    isGroupSupport = groupSupport,
    isTypeInstant = typeInstant,
    isTypeRune = typeRune,
    isPremiumOnly = premiumOnly
)

fun RuneInformationDataDto.toDomain(): RuneInfoData = RuneInfoData(
    vocation = vocation ?: emptyList(),
    isGroupAttack = groupAttack,
    isGroupHealing = groupHealing,
    isGroupSupport = groupSupport,
    damageType = damageType.orEmpty(),
    level = level,
    magicLevel = magicLevel
)
