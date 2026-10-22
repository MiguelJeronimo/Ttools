package com.example.TibiaTools.data.model.GuildInformation

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.GuildInformation.guilds.Guilds

@Keep
data class ApiGuilds(
    var guilds: Guilds? = null
)
