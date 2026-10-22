package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class APICriatures(
    var creatures: Criatures? = null,
    var creatures_List: ArrayList<CriaturesList>? = null
)

@Keep
data class APICriaturesInformation(
    var creature: Creature? = null
)

@Keep
data class APIBoostableBosses(
    var boostable_bosses: BoostableBosses? = null
)

@Keep
data class Criatures(
    var boosted: Boosted? = null,
    var creature_list: ArrayList<CriaturesList> = ArrayList()
) {
    fun getCriatures_list(): ArrayList<CriaturesList> = creature_list
    fun setCriatures_list(list: ArrayList<CriaturesList>) {
        this.creature_list = list
    }
}

@Keep
data class CriaturesList(
    var name: String? = null,
    var race: String? = null,
    var image_url: String? = null
)

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

@Keep
data class BoostableBosses(
    var boosted: Boosted? = null
)

@Keep
data class Boosted(
    var name: String? = null,
    var race: String? = null,
    var image_url: String? = null
)

@Keep
data class BoostedBoss(
    var name: String? = null,
    var race: String? = null,
    var image_url: String? = null
)
