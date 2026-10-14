package com.example.TibiaTools.recyclerview.items

/**
 * Mundo de juego, tal como lo pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewWorlds].
 * Sustituye a `ItemsRecyclerViewMundos`; quita el prefijo `lb` y corrige `GameWorlType`.
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewWorlds(
 *     name = "Antica", status = "online", playersOnline = "512", location = "Europe",
 *     pvpType = "Open PvP", premiumOnly = "false", transferType = "regular",
 *     battleyeProtected = "true", battleyeDate = "release", gameWorldType = "regular",
 *     tournamentWorldType = ""
 * )
 * ```
 */
data class ItemsRecyclerViewWorlds(
    val name: String,
    val status: String,
    val playersOnline: String,
    val location: String,
    val pvpType: String,
    val premiumOnly: String,
    val transferType: String,
    val battleyeProtected: String,
    val battleyeDate: String,
    val gameWorldType: String,
    val tournamentWorldType: String
)
