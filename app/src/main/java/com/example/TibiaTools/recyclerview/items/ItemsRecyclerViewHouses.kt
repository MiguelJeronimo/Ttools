package com.example.TibiaTools.recyclerview.items

/**
 * Casa o guildhall de una ciudad, tal como la pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewHouses].
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewHouses(
 *     houseName = "Sweet Home", houseSize = "40", houseRent = "50000",
 *     houseRented = ItemsRecyclerViewHouses.RENTED, houseId = "10101"
 * )
 * ```
 *
 * @property houseRented texto de estado: [RENTED] o [AVAILABLE].
 */
data class ItemsRecyclerViewHouses(
    val houseName: String,
    val houseSize: String,
    val houseRent: String,
    val houseRented: String,
    val houseId: String
) {
    /** `true` si la casa está alquilada. */
    val isRented: Boolean get() = houseRented == RENTED

    companion object {
        const val RENTED = "Ocupada"
        const val AVAILABLE = "Desocupada"

        /** @return [RENTED] o [AVAILABLE] según [rented]. */
        fun rentedLabel(rented: Boolean): String = if (rented) RENTED else AVAILABLE
    }
}
