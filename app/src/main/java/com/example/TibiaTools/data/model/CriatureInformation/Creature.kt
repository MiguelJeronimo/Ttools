package com.example.TibiaTools.data.model.CriatureInformation

import androidx.annotation.Keep

@Keep
data class Creature(
    var name: String? = null,
    var race: String? = null,
    var image_url: String? = null,
    var description: String? = null,
    var behaviour: String? = null,
    var hitpoints: String? = null,
    var experience_points: String? = null,
    var loot_list: ArrayList<String> = ArrayList(),
    var immune: ArrayList<String> = ArrayList(),
    var strong: ArrayList<String> = ArrayList(),
    var weakness: ArrayList<String> = ArrayList()
)
