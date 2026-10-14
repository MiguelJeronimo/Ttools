package com.example.TibiaTools.recyclerview.items

/**
 * Hechizo de la lista, tal como lo pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewSpells].
 * Las propiedades se renombraron al inglés (`nombre` → [name], `precio` → [price],
 * `tipo` → [type], `grupo` → [group]).
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewSpells(
 *     name = "Light Healing", formula = "exura", mana = "20", price = "170",
 *     type = "Instant", group = "Healing", spellId = "lighthealing",
 *     premium = "Free", level = "8"
 * )
 * ```
 *
 * @property price precio en gold coins, tal como viene de la API (texto).
 */
data class ItemsRecyclerViewSpells(
    val name: String,
    val formula: String,
    val mana: String,
    val price: String,
    val type: String,
    val group: String,
    val spellId: String,
    val premium: String,
    val level: String
)
