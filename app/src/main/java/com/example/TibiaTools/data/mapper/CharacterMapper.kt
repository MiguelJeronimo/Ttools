package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.AccountInformationDto
import com.example.TibiaTools.data.dto.AchievementDto
import com.example.TibiaTools.data.dto.CharacterDetailDto
import com.example.TibiaTools.data.dto.CharacterGuildDto
import com.example.TibiaTools.data.dto.CharacterHouseDto
import com.example.TibiaTools.data.dto.CharacterInformationDto
import com.example.TibiaTools.data.dto.CharactersDto
import com.example.TibiaTools.data.dto.DeathDto
import com.example.TibiaTools.data.dto.InformationDto
import com.example.TibiaTools.data.dto.OtherCharacterDto
import com.example.TibiaTools.data.dto.StatusDto
import com.example.TibiaTools.domain.model.AccountInformation
import com.example.TibiaTools.domain.model.Achievement
import com.example.TibiaTools.domain.model.CharacterDetail
import com.example.TibiaTools.domain.model.CharacterGuild
import com.example.TibiaTools.domain.model.CharacterHouse
import com.example.TibiaTools.domain.model.CharacterInformation
import com.example.TibiaTools.domain.model.CharactersData
import com.example.TibiaTools.domain.model.Death
import com.example.TibiaTools.domain.model.InformationInfo
import com.example.TibiaTools.domain.model.OtherCharacter
import com.example.TibiaTools.domain.model.StatusInfo

/**
 * Mappers para convertir DTOs de capa data a modelos limpios de dominio.
 */
fun CharacterInformationDto.toDomain(): CharacterInformation = CharacterInformation(
    characterData = characterData?.toDomain(),
    information = information?.toDomain()
)

fun CharactersDto.toDomain(): CharactersData = CharactersData(
    character = character?.toDomain(),
    otherCharacters = otherCharacters.map { it.toDomain() },
    deaths = deaths.map { it.toDomain() },
    achievements = achievements.map { it.toDomain() },
    accountInformation = accountInformation?.toDomain()
)

fun CharacterDetailDto.toDomain(): CharacterDetail = CharacterDetail(
    name = name.orEmpty(),
    sex = sex.orEmpty(),
    title = title.orEmpty(),
    unlockedTitles = unlockedTitles,
    vocation = vocation.orEmpty(),
    level = level,
    achievementPoints = achievementPoints,
    world = world.orEmpty(),
    residence = residence.orEmpty(),
    guild = guild?.toDomain(),
    lastLogin = lastLogin.orEmpty(),
    accountStatus = accountStatus.orEmpty(),
    comment = comment.orEmpty(),
    houses = houses.map { it.toDomain() },
    marriedTo = marriedTo.orEmpty()
)

fun CharacterGuildDto.toDomain(): CharacterGuild = CharacterGuild(
    name = name.orEmpty(),
    rank = rank.orEmpty()
)

fun CharacterHouseDto.toDomain(): CharacterHouse = CharacterHouse(
    name = name.orEmpty(),
    town = town.orEmpty(),
    paid = paid.orEmpty(),
    houseId = houseId
)

fun AccountInformationDto.toDomain(): AccountInformation = AccountInformation(
    loyaltyTitle = loyaltyTitle.orEmpty(),
    created = created.orEmpty()
)

fun AchievementDto.toDomain(): Achievement = Achievement(
    name = name.orEmpty(),
    grade = grade.orEmpty(),
    secret = secret
)

fun DeathDto.toDomain(): Death = Death(
    time = time.orEmpty(),
    reason = reason.orEmpty(),
    level = level
)

fun OtherCharacterDto.toDomain(): OtherCharacter = OtherCharacter(
    name = name.orEmpty(),
    world = world.orEmpty(),
    status = status.orEmpty(),
    isDeleted = deleted,
    isMain = main,
    isTraded = traded
)

fun InformationDto.toDomain(): InformationInfo = InformationInfo(
    status = status?.toDomain()
)

fun StatusDto.toDomain(): StatusInfo = StatusInfo(
    httpCode = httpCode,
    message = message.orEmpty()
)
