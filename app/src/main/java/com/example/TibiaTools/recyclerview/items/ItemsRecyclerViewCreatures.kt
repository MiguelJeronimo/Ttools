package com.example.TibiaTools.recyclerview.items

/**
 * Criatura de la lista, tal como la pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewCreatures].
 * Sustituye a `ItemsRecyclerViewCriatures` (`lbName`/`lbrace`/`imagerace`).
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewCreatures(name = "Dragon", race = "dragon", imageUrl = "https://static.tibia.com/images/library/dragon.gif")
 * ```
 *
 * @property name nombre visible.
 * @property race identificador de raza con el que se consulta la API.
 * @property imageUrl URL de la imagen; puede estar vacía.
 */
data class ItemsRecyclerViewCreatures(
    val name: String,
    val race: String,
    val imageUrl: String
)
