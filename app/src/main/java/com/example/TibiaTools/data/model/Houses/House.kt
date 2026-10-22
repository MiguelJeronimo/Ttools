package com.example.TibiaTools.data.model.Houses

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.Houses.Status.Status

@Keep
data class House(
    var world: String? = null,
    var town: String? = null,
    var img: String? = null,
    var name: String? = null,
    var type: String? = null,
    var beds: Int = 0,
    var size: Int = 0,
    var rent: Int = 0,
    var status: Status? = null
)
