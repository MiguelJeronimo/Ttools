package com.example.TibiaTools.utils

import java.util.Locale

/**
 * Filtros que el usuario elige en las pantallas de búsqueda (Highscores y Houses).
 *
 * Sustituye a `DataHighScores`, que era un JavaBean mutable usado solo para pasar
 * valores al ViewModel. Al ser una `data class` inmutable se puede comparar, copiar
 * y enviar al ViewModel sin miedo a que otra parte de la UI la modifique.
 *
 * Ejemplo:
 * ```kotlin
 * val filter = SearchFilter(world = "Antica", category = "Magic Level", vocation = "Sorcerer")
 * filter.categoryQuery // "magiclevel"
 *
 * viewModelHighScore.setHighScores(filter.world, filter.categoryQuery, filter.vocation)
 *
 * val houses = SearchFilter(world = "Antica", city = "Thais")
 * viewModelHouses.setHouses(houses.world, houses.city)
 * ```
 *
 * @property world mundo seleccionado.
 * @property category categoría de highscore tal como se muestra en la UI (p. ej. `"Magic Level"`).
 * @property vocation vocación seleccionada.
 * @property city ciudad seleccionada (solo la usa la búsqueda de casas).
 */
data class SearchFilter(
    val world: String = "",
    val category: String = "",
    val vocation: String = "",
    val city: String = ""
) {
    /**
     * [category] en el formato que espera la API: sin espacios y en minúsculas.
     * `"Magic Level"` → `"magiclevel"`.
     */
    val categoryQuery: String
        get() = category.replace(" ", "").lowercase(Locale.ROOT)
}
