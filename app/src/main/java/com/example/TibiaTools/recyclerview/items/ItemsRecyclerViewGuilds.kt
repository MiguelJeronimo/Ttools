package com.example.TibiaTools.recyclerview.items

/**
 * Guild de un mundo, tal como la pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewGuildsList].
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewGuilds(name = "Red Rose", logoUrl = "https://.../logo.gif", description = "PvP guild")
 * ```
 *
 * @property name nombre de la guild.
 * @property logoUrl URL del logo; puede estar vacía.
 * @property description descripción pública.
 */
data class ItemsRecyclerViewGuilds(
    val name: String,
    val logoUrl: String,
    val description: String
)
