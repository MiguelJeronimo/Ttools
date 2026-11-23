package com.example.TibiaTools.domain.model

data class WorldsResponse(
    val worlds: WorldsData? = null
)

data class WorldsData(
    val playersOnline: Int = 0,
    val recordDate: String = "",
    val regularWorlds: List<RegularWorld> = emptyList()
)

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
    override fun compareTo(other: RegularWorld): Int {
        return other.playersOnline.compareTo(this.playersOnline)
    }
}
