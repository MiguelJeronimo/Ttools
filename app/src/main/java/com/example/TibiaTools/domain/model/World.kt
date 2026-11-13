package com.example.TibiaTools.domain.model

data class WorldsResponse(
    val worlds: WorldsData? = null
)

data class WorldsData(
    val playersOnline: Int = 0,
    val recordDate: String = "",
    val regularWorlds: List<RegularWorld> = emptyList()
) {
    val players_online: Int get() = playersOnline
    val regular_worlds: List<RegularWorld> get() = regularWorlds
}

data class RegularWorld(
    val name: String = "",
    val status: String = "",
    val playersOnline: Int = 0,
    val location: String = "",
    val pvpType: String = "",
    val isPremiumOnly: Boolean = false,
    val transferType: String = "",
    val isBattleyeProtected: Boolean = false,
    val battleyeDate: String = "",
    val gameWorldType: String = "",
    val tournamentWorldType: String = ""
) : Comparable<RegularWorld> {
    val players_online: Int get() = playersOnline
    val pvp_type: String get() = pvpType
    val premium_only: Boolean get() = isPremiumOnly
    val transfer_type: String get() = transferType
    val battleye_protected: Boolean get() = isBattleyeProtected
    val battleye_date: String get() = battleyeDate
    val game_world_type: String get() = gameWorldType
    val tournament_world_type: String get() = tournamentWorldType

    override fun compareTo(other: RegularWorld): Int {
        return other.playersOnline.compareTo(this.playersOnline)
    }
}
