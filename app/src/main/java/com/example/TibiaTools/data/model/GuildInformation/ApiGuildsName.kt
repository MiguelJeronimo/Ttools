package com.example.TibiaTools.data.model.GuildInformation

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.GuildInformation.GuildName.Guild

@Keep
data class ApiGuildsName(
    var guild: Guild? = null
)
