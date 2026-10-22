package com.example.TibiaTools.recyclerview.items

/**
 * Otro personaje de la misma cuenta, tal como lo pinta [com.example.TibiaTools.recyclerview.adapters.AdapterOtherCharacters].
 *
 * Ejemplo:
 * ```kotlin
 * ItemsCharacters(name = "Bubble", world = "Antica", status = "online", deleted = false, main = true, traded = false)
 * ```
 *
 * @property name nombre del personaje.
 * @property world mundo donde juega.
 * @property status `"online"` u `"offline"`.
 * @property deleted `true` si está marcado para borrarse.
 * @property main `true` si es el personaje principal de la cuenta.
 * @property traded `true` si se compró en el Char Bazaar.
 */
data class ItemsCharacters(
    val name: String,
    val world: String,
    val status: String,
    val deleted: Boolean,
    val main: Boolean,
    val traded: Boolean
) {
    /** `true` si [status] es `"online"` (sin distinguir mayúsculas). */
    val isOnline: Boolean get() = status.equals(ONLINE_STATUS, ignoreCase = true)

    companion object {
        const val ONLINE_STATUS = "online"
    }
}
