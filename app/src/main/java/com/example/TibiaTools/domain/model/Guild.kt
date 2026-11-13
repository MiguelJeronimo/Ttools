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
) {
    val logo_url: String get() = logoUrl
}

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
) {
    val logo_url: String get() = logoUrl
    val in_war: Boolean get() = inWar
    val players_online: Int get() = playersOnline
    val members_total: Int get() = membersTotal
}

data class GuildHall(
    val name: String = "",
    val world: String = "",
    val paidUntil: String = ""
) {
    val paid_until: String get() = paidUntil
}

data class GuildMember(
    val name: String = "",
    val title: String = "",
    val rank: String = "",
    val vocation: String = "",
    val level: String = "",
    val joined: String = "",
    val status: String = ""
)
