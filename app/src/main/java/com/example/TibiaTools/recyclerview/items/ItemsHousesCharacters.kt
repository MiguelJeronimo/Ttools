package com.example.TibiaTools.recyclerview.items

/**
 * Casa alquilada por un personaje, tal como la pinta [com.example.TibiaTools.recyclerview.adapters.AdapterHouseCharacters].
 *
 * Ejemplo:
 * ```kotlin
 * ItemsHousesCharacters(name = "Sweet Home", town = "Thais", paid = "2026-10-30", houseid = 10101)
 * ```
 *
 * @property name nombre de la casa.
 * @property town ciudad.
 * @property paid fecha hasta la que está pagada la renta.
 * @property houseid id de la casa en TibiaData.
 */
data class ItemsHousesCharacters(
    val name: String,
    val town: String,
    val paid: String,
    val houseid: Int
)
