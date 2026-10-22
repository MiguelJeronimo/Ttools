package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class APIServicesTibia(
    var character: Characters? = null,
    var information: Information? = null
)

@Keep
data class Characters(
    var character: Character? = null,
    var other_characters: ArrayList<OtherCharacters> = ArrayList(),
    var deaths: ArrayList<Deaths> = ArrayList(),
    var achievements: ArrayList<Achievements> = ArrayList(),
    var account_information: AccountInformation? = null
)

@Keep
data class Character(
    var name: String? = null,
    var sex: String? = null,
    var title: String? = null,
    var unlocked_titles: Int = 0,
    var vocation: String? = null,
    var level: Int = 0,
    var achievement_points: Int = 0,
    var world: String? = null,
    var residence: String? = null,
    var guild: CharacterGuild? = null,
    var last_login: String? = null,
    var account_status: String? = null,
    var comment: String? = null,
    var houses: ArrayList<CharacterHouse> = ArrayList(),
    var married_to: String? = null
)

@Keep
data class CharacterGuild(
    var name: String? = null,
    var rank: String? = null
)

@Keep
data class CharacterHouse(
    var name: String? = null,
    var town: String? = null,
    var paid: String? = null,
    var houseid: Int = 0
)

@Keep
data class AccountInformation(
    var loyalty_title: String? = null,
    var created: String? = null
)

@Keep
data class Achievements(
    var name: String? = null,
    var grade: String? = null,
    var secret: Boolean = false
) {
    fun isSecret(): Boolean = secret
}

@Keep
data class Deaths(
    var time: String? = null,
    var reason: String? = null,
    var level: Int = 0
)

@Keep
data class OtherCharacters(
    var name: String? = null,
    var world: String? = null,
    var status: String? = null,
    var deleted: Boolean = false,
    var main: Boolean = false,
    var traded: Boolean = false
)

@Keep
data class Information(
    var status: Status? = null
)

@Keep
data class Status(
    var http_code: Int = 0,
    var message: String? = null
) {
    val httpCode: Int get() = http_code
}
