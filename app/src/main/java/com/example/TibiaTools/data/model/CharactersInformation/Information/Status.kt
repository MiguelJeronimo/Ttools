package com.example.TibiaTools.data.model.CharactersInformation.Information

import androidx.annotation.Keep

@Keep
data class Status(
    var http_code: Int = 0,
    var message: String? = null
) {
    val httpCode: Int get() = http_code
}
