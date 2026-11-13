package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) para mapear la respuesta JSON de TibiaData API v4 (/v4/character/{name}).
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
