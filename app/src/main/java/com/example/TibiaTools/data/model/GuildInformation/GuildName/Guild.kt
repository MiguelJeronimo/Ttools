package com.example.TibiaTools.data.model.GuildInformation.GuildName

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.GuildInformation.GuildName.GuildHalls.GuildHalls
import com.example.TibiaTools.data.model.GuildInformation.GuildName.members.MembersGuild

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
