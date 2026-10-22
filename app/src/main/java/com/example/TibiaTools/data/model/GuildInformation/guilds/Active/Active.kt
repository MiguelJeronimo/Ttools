package com.example.TibiaTools.data.model.GuildInformation.guilds.Active

import androidx.annotation.Keep

@Keep
data class Active(
    var name: String? = null,
    var logo_url: String? = null,
    var description: String? = null
)
