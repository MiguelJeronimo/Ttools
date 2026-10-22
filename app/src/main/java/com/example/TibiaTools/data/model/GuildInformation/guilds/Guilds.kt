package com.example.TibiaTools.data.model.GuildInformation.guilds

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.GuildInformation.guilds.Active.Active

@Keep
data class Guilds(
    var active: ArrayList<Active>? = null
)
