package com.example.TibiaTools.domain.models

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.APIServicesTibia
import com.example.TibiaTools.data.model.AccountInformation
import com.example.TibiaTools.data.model.Achievements
import com.example.TibiaTools.data.model.Character
import com.example.TibiaTools.data.model.CharacterGuild
import com.example.TibiaTools.data.model.CharacterHouse
import com.example.TibiaTools.data.model.Characters
import com.example.TibiaTools.data.model.Deaths
import com.example.TibiaTools.data.model.Information
import com.example.TibiaTools.data.model.OtherCharacters
import com.example.TibiaTools.data.model.Status
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) para la respuesta de información de personajes
 * de la API de TibiaData v4 ([APIServicesTibia]).
 */
@Keep
@Serializable
data class CharacterInformationDto(
    @SerializedName("character")
    @SerialName("character")
    val characterData: CharactersDto? = null,

    @SerializedName("information")
    @SerialName("information")
    val information: InformationDto? = null
)

@Keep
@Serializable
data class CharactersDto(
    @SerializedName("character")
    @SerialName("character")
    val character: CharacterDetailDto? = null,

    @SerializedName("other_characters")
    @SerialName("other_characters")
    val otherCharacters: List<OtherCharacterDto> = emptyList(),

    @SerializedName("deaths")
    @SerialName("deaths")
    val deaths: List<DeathDto> = emptyList(),

    @SerializedName("achievements")
    @SerialName("achievements")
    val achievements: List<AchievementDto> = emptyList(),

    @SerializedName("account_information")
    @SerialName("account_information")
    val accountInformation: AccountInformationDto? = null
)

@Keep
@Serializable
data class CharacterDetailDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("sex")
    @SerialName("sex")
    val sex: String? = null,

    @SerializedName("title")
    @SerialName("title")
    val title: String? = null,

    @SerializedName("unlocked_titles")
    @SerialName("unlocked_titles")
    val unlockedTitles: Int = 0,

    @SerializedName("vocation")
    @SerialName("vocation")
    val vocation: String? = null,

    @SerializedName("level")
    @SerialName("level")
    val level: Int = 0,

    @SerializedName("achievement_points")
    @SerialName("achievement_points")
    val achievementPoints: Int = 0,

    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("residence")
    @SerialName("residence")
    val residence: String? = null,

    @SerializedName("guild")
    @SerialName("guild")
    val guild: CharacterGuildDto? = null,

    @SerializedName("last_login")
    @SerialName("last_login")
    val lastLogin: String? = null,

    @SerializedName("account_status")
    @SerialName("account_status")
    val accountStatus: String? = null,

    @SerializedName("comment")
    @SerialName("comment")
    val comment: String? = null,

    @SerializedName("houses")
    @SerialName("houses")
    val houses: List<CharacterHouseDto> = emptyList(),

    @SerializedName("married_to")
    @SerialName("married_to")
    val marriedTo: String? = null
)

@Keep
@Serializable
data class CharacterGuildDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("rank")
    @SerialName("rank")
    val rank: String? = null
)

@Keep
@Serializable
data class CharacterHouseDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("town")
    @SerialName("town")
    val town: String? = null,

    @SerializedName("paid")
    @SerialName("paid")
    val paid: String? = null,

    @SerializedName("houseid")
    @SerialName("houseid")
    val houseId: Int = 0
)

@Keep
@Serializable
data class AccountInformationDto(
    @SerializedName("loyalty_title")
    @SerialName("loyalty_title")
    val loyaltyTitle: String? = null,

    @SerializedName("created")
    @SerialName("created")
    val created: String? = null
)

@Keep
@Serializable
data class AchievementDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("grade")
    @SerialName("grade")
    val grade: String? = null,

    @SerializedName("secret")
    @SerialName("secret")
    val secret: Boolean = false
)

@Keep
@Serializable
data class DeathDto(
    @SerializedName("time")
    @SerialName("time")
    val time: String? = null,

    @SerializedName("reason")
    @SerialName("reason")
    val reason: String? = null,

    @SerializedName("level")
    @SerialName("level")
    val level: Int = 0
)

@Keep
@Serializable
data class OtherCharacterDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("status")
    @SerialName("status")
    val status: String? = null,

    @SerializedName("deleted")
    @SerialName("deleted")
    val deleted: Boolean = false,

    @SerializedName("main")
    @SerialName("main")
    val main: Boolean = false,

    @SerializedName("traded")
    @SerialName("traded")
    val traded: Boolean = false
)

@Keep
@Serializable
data class InformationDto(
    @SerializedName("status")
    @SerialName("status")
    val status: StatusDto? = null
)

@Keep
@Serializable
data class StatusDto(
    @SerializedName("http_code")
    @SerialName("http_code")
    val httpCode: Int = 0,

    @SerializedName("message")
    @SerialName("message")
    val message: String? = null
)

// region Mappers
fun APIServicesTibia.toDto(): CharacterInformationDto = CharacterInformationDto(
    characterData = character?.toDto(),
    information = information?.toDto()
)

fun Characters.toDto(): CharactersDto = CharactersDto(
    character = character?.toDto(),
    otherCharacters = other_characters.map { it.toDto() },
    deaths = deaths.map { it.toDto() },
    achievements = achievements.map { it.toDto() },
    accountInformation = account_information?.toDto()
)

fun Character.toDto(): CharacterDetailDto = CharacterDetailDto(
    name = name,
    sex = sex,
    title = title,
    unlockedTitles = unlocked_titles,
    vocation = vocation,
    level = level,
    achievementPoints = achievement_points,
    world = world,
    residence = residence,
    guild = guild?.toDto(),
    lastLogin = last_login,
    accountStatus = account_status,
    comment = comment,
    houses = houses.map { it.toDto() },
    marriedTo = married_to
)

fun CharacterGuild.toDto(): CharacterGuildDto = CharacterGuildDto(
    name = name,
    rank = rank
)

fun CharacterHouse.toDto(): CharacterHouseDto = CharacterHouseDto(
    name = name,
    town = town,
    paid = paid,
    houseId = houseid
)

fun AccountInformation.toDto(): AccountInformationDto = AccountInformationDto(
    loyaltyTitle = loyalty_title,
    created = created
)

fun Achievements.toDto(): AchievementDto = AchievementDto(
    name = name,
    grade = grade,
    secret = secret
)

fun Deaths.toDto(): DeathDto = DeathDto(
    time = time,
    reason = reason,
    level = level
)

fun OtherCharacters.toDto(): OtherCharacterDto = OtherCharacterDto(
    name = name,
    world = world,
    status = status,
    deleted = deleted,
    main = main,
    traded = traded
)

fun Information.toDto(): InformationDto = InformationDto(
    status = status?.toDto()
)

fun Status.toDto(): StatusDto = StatusDto(
    httpCode = http_code,
    message = message
)
// endregion
