package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class ApiGuilds(
    var guilds: Guilds? = null
)

@Keep
data class ApiGuildsName(
    var guild: Guild? = null
) {
    fun getGuilds(): Guild? = guild
}

@Keep
data class Guilds(
    var active: ArrayList<Active>? = null
)

@Keep
data class Active(
    var name: String? = null,
    var logo_url: String? = null,
    var description: String? = null
)

@Keep
data class Guild(
    var name: String? = null,
    var world: String? = null,
    var logo_url: String? = null,
    var description: String? = null,
    var active: Boolean? = null,
    var in_war: Boolean? = null,
    var players_online: Int = 0,
    var members_total: Int = 0,
    var founded: String? = null,
    var members: ArrayList<MembersGuild>? = null,
    var guildhalls: ArrayList<GuildHalls>? = null
)

@Keep
data class GuildName(
    var name: String? = null,
    var world: String? = null,
    var logo_url: String? = null,
    var description: String? = null,
    var active: Boolean? = null,
    var in_war: Boolean? = null,
    var players_online: Int = 0,
    var members_total: Int = 0,
    var founded: String? = null,
    var members: ArrayList<MembersGuild>? = null,
    var guildhalls: ArrayList<GuildHalls>? = null
)

@Keep
data class GuildHalls(
    var name: String? = null,
    var world: String? = null,
    var paid_until: String? = null
)

@Keep
data class MembersGuild(
    var name: String? = null,
    var title: String? = null,
    var rank: String? = null,
    var vocation: String? = null,
    var level: String? = null,
    var joined: String? = null,
    var status: String? = null
)

@Keep
data class GuildWorld(
    var world: String? = null
)
