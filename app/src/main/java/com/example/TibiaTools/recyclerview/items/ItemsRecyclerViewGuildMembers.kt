package com.example.TibiaTools.recyclerview.items

/**
 * Miembro de una guild, tal como lo pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewGuildMembers].
 *
 * Sustituye a `itemsRecyclerViewGuildsName`, cuyo constructor hacía `Integer.parseInt(level)`
 * y se cerraba si la API mandaba un nivel nulo o no numérico. Ahora la conversión la hace
 * quien llama, por ejemplo con `level?.toIntOrNull() ?: 0`.
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewGuildMembers(
 *     name = "Bubble", title = "", rank = "Leader", vocation = "Elite Knight",
 *     level = 500, joined = "2020-01-01", status = "online"
 * )
 * ```
 *
 * @property level nivel del personaje.
 * @property status `"online"` u `"offline"`.
 */
data class ItemsRecyclerViewGuildMembers(
    val name: String,
    val title: String,
    val rank: String,
    val vocation: String,
    val level: Int,
    val joined: String,
    val status: String
) {
    /** `true` si [status] es `"online"` (sin distinguir mayúsculas). */
    val isOnline: Boolean get() = status.equals(ItemsCharacters.ONLINE_STATUS, ignoreCase = true)
}
