package com.example.TibiaTools.data.model.CharactersInformation.Characters.CharacterData

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.CharactersInformation.Characters.CharacterData.Guild.Guild
import com.example.TibiaTools.data.model.CharactersInformation.Characters.CharacterData.Houses.House

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
    var guild: Guild? = null,
    var last_login: String? = null,
    var account_status: String? = null,
    var comment: String? = null,
    var houses: ArrayList<House> = ArrayList(),
    var married_to: String? = null
)
