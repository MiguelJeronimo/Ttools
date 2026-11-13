package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class WorldsResponseDto(
    @SerializedName("worlds")
    @SerialName("worlds")
    val worlds: WorldsDataDto? = null
)

@Keep
@Serializable
data class WorldsDataDto(
    @SerializedName("players_online")
    @SerialName("players_online")
    val playersOnline: Int = 0,

    @SerializedName("record_date")
    @SerialName("record_date")
    val recordDate: String? = null,

    @SerializedName("regular_worlds")
    @SerialName("regular_worlds")
    val regularWorlds: List<RegularWorldDto> = emptyList()
)

@Keep
@Serializable
data class RegularWorldDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("status")
    @SerialName("status")
    val status: String? = null,

    @SerializedName("players_online")
    @SerialName("players_online")
    val playersOnline: Int = 0,

    @SerializedName("location")
    @SerialName("location")
    val location: String? = null,

    @SerializedName("pvp_type")
    @SerialName("pvp_type")
    val pvpType: String? = null,

    @SerializedName("premium_only")
    @SerialName("premium_only")
    val premiumOnly: Boolean = false,

    @SerializedName("transfer_type")
    @SerialName("transfer_type")
    val transferType: String? = null,

    @SerializedName("battleye_protected")
    @SerialName("battleye_protected")
    val battleyeProtected: Boolean = false,

    @SerializedName("battleye_date")
    @SerialName("battleye_date")
    val battleyeDate: String? = null,

    @SerializedName("game_world_type")
    @SerialName("game_world_type")
    val gameWorldType: String? = null,

    @SerializedName("tournament_world_type")
    @SerialName("tournament_world_type")
    val tournamentWorldType: String? = null
)
