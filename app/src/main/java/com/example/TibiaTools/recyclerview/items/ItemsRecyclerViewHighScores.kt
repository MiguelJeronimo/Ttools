package com.example.TibiaTools.recyclerview.items

/**
 * Entrada del ranking, tal como la pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewHighScores].
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewHighScores(
 *     rank = "1", name = "Bubble", vocation = "Elite Knight", world = "Antica",
 *     level = "1000", value = "Magic Level: 12", title = "Legend"
 * )
 * ```
 *
 * @property value valor ya formateado para mostrar (p. ej. `"Magic Level: 12"`).
 */
data class ItemsRecyclerViewHighScores(
    val rank: String,
    val name: String,
    val vocation: String,
    val world: String,
    val level: String,
    val value: String,
    val title: String
)
