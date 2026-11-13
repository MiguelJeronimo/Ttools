package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CreaturesResponseDto(
    @SerializedName("creatures")
    @SerialName("creatures")
    val creatures: CreaturesDataDto? = null,

    @SerializedName("creatures_List")
    @SerialName("creatures_List")
    val creaturesList: List<CreatureListItemDto>? = null
)

@Keep
@Serializable
data class CreatureInformationResponseDto(
    @SerializedName("creature")
    @SerialName("creature")
    val creature: CreatureDetailDto? = null
)

@Keep
@Serializable
data class BoostableBossesResponseDto(
    @SerializedName("boostable_bosses")
    @SerialName("boostable_bosses")
    val boostableBosses: BoostableBossesDataDto? = null
)

@Keep
@Serializable
data class CreaturesDataDto(
    @SerializedName("boosted")
    @SerialName("boosted")
    val boosted: BoostedDataDto? = null,

    @SerializedName("creature_list")
    @SerialName("creature_list")
    val creatureList: List<CreatureListItemDto> = emptyList()
)

@Keep
@Serializable
data class CreatureListItemDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("race")
    @SerialName("race")
    val race: String? = null,

    @SerializedName("image_url")
    @SerialName("image_url")
    val imageUrl: String? = null
)

@Keep
@Serializable
data class CreatureDetailDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("race")
    @SerialName("race")
    val race: String? = null,

    @SerializedName("image_url")
    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerializedName("description")
    @SerialName("description")
    val description: String? = null,

    @SerializedName("behaviour")
    @SerialName("behaviour")
    val behaviour: String? = null,

    @SerializedName("hitpoints")
    @SerialName("hitpoints")
    val hitpoints: String? = null,

    @SerializedName("experience_points")
    @SerialName("experience_points")
    val experiencePoints: String? = null,

    @SerializedName("loot_list")
    @SerialName("loot_list")
    val lootList: List<String> = emptyList(),

    @SerializedName("immune")
    @SerialName("immune")
    val immune: List<String> = emptyList(),

    @SerializedName("strong")
    @SerialName("strong")
    val strong: List<String> = emptyList(),

    @SerializedName("weakness")
    @SerialName("weakness")
    val weakness: List<String> = emptyList()
)

@Keep
@Serializable
data class BoostableBossesDataDto(
    @SerializedName("boosted")
    @SerialName("boosted")
    val boosted: BoostedDataDto? = null
)

@Keep
@Serializable
data class BoostedDataDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("race")
    @SerialName("race")
    val race: String? = null,

    @SerializedName("image_url")
    @SerialName("image_url")
    val imageUrl: String? = null
)
