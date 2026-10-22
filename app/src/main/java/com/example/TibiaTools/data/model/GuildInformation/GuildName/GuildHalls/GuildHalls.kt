package com.example.TibiaTools.data.model.GuildInformation.GuildName.GuildHalls

import androidx.annotation.Keep

@Keep
data class GuildHalls(
    var name: String? = null,
    var world: String? = null,
    var paid_until: String? = null
)
