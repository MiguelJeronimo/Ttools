package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GuildsResponseDto(
    @SerializedName("guilds")
    @SerialName("guilds")
    val guilds: GuildsDataDto? = null
)

@Keep
@Serializable
data class GuildNameResponseDto(
    @SerializedName("guild")
    @SerialName("guild")
    val guild: GuildDetailDto? = null
)

@Keep
@Serializable
data class GuildsDataDto(
    @SerializedName("active")
    @SerialName("active")
    val active: List<GuildActiveDto>? = null
)

@Keep
@Serializable
data class GuildActiveDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("logo_url")
    @SerialName("logo_url")
    val logoUrl: String? = null,

    @SerializedName("description")
    @SerialName("description")
    val description: String? = null
)

@Keep
@Serializable
data class GuildDetailDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("logo_url")
    @SerialName("logo_url")
    val logoUrl: String? = null,

    @SerializedName("description")
    @SerialName("description")
    val description: String? = null,

    @SerializedName("active")
    @SerialName("active")
    val active: Boolean? = null,

    @SerializedName("in_war")
    @SerialName("in_war")
    val inWar: Boolean? = null,

    @SerializedName("players_online")
    @SerialName("players_online")
    val playersOnline: Int = 0,

    @SerializedName("members_total")
    @SerialName("members_total")
    val membersTotal: Int = 0,

    @SerializedName("founded")
    @SerialName("founded")
    val founded: String? = null,

    @SerializedName("members")
    @SerialName("members")
    val members: List<GuildMemberDto>? = null,

    @SerializedName("guildhalls")
    @SerialName("guildhalls")
    val guildhalls: List<GuildHallDto>? = null
)

@Keep
@Serializable
data class GuildHallDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("paid_until")
    @SerialName("paid_until")
    val paidUntil: String? = null
)

@Keep
@Serializable
data class GuildMemberDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("title")
    @SerialName("title")
    val title: String? = null,

    @SerializedName("rank")
    @SerialName("rank")
    val rank: String? = null,

    @SerializedName("vocation")
    @SerialName("vocation")
    val vocation: String? = null,

    @SerializedName("level")
    @SerialName("level")
    val level: String? = null,

    @SerializedName("joined")
    @SerialName("joined")
    val joined: String? = null,

    @SerializedName("status")
    @SerialName("status")
    val status: String? = null
)
