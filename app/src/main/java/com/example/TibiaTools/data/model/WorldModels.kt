package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class DataWords(
    var worlds: Worlds? = null
)

@Keep
data class Worlds(
    var players_online: Int = 0,
    var record_date: String? = null,
    var regular_worlds: ArrayList<RegularWorlds> = ArrayList()
)

@Keep
data class RegularWorlds(
    var name: String? = null,
    var status: String? = null,
    var players_online: Int = 0,
    var location: String? = null,
    var pvp_type: String? = null,
    var premium_only: Boolean = false,
    var transfer_type: String? = null,
    var battleye_protected: Boolean = false,
    var battleye_date: String? = null,
    var game_world_type: String? = null,
    var tournament_world_type: String? = null
) : Comparable<RegularWorlds> {
    override fun compareTo(other: RegularWorlds): Int {
        return other.players_online.compareTo(this.players_online)
    }
}
