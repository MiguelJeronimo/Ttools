package com.example.TibiaTools.domain.model

data class GuildsResponse(
    val guilds: GuildsData? = null
)

data class GuildNameResponse(
    val guild: GuildDetail? = null
)

data class GuildsData(
    val active: List<GuildActive>? = null
)

data class GuildActive(
    val name: String = "",
    val logoUrl: String = "",
    val description: String = ""
)

data class GuildDetail(
    val name: String = "",
    val world: String = "",
    val logoUrl: String = "",
    val description: String = "",
    val active: Boolean = false,
    val inWar: Boolean = false,
    val playersOnline: Int = 0,
    val membersTotal: Int = 0,
    val founded: String = "",
    val members: List<GuildMember> = emptyList(),
    val guildhalls: List<GuildHall> = emptyList()
)

data class GuildHall(
    val name: String = "",
    val world: String = "",
    val paidUntil: String = ""
)

data class GuildMember(
    val name: String = "",
    val title: String = "",
    val rank: String = "",
    val vocation: String = "",
    val level: String = "",
    val joined: String = "",
    val status: String = ""
)
