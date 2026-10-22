package com.example.TibiaTools.recyclerview.items

/**
 * Noticia o news ticker, tal como la pinta [com.example.TibiaTools.recyclerview.adapters.AdapterRecyclerViewNews].
 *
 * Ejemplo:
 * ```kotlin
 * ItemsRecyclerViewNews(
 *     id = "8000", date = "2026-10-14", news = "Server save delayed",
 *     category = "technical", type = "ticker", url = "https://..."
 * )
 * ```
 */
data class ItemsRecyclerViewNews(
    val id: String,
    val date: String,
    val news: String,
    val category: String,
    val type: String,
    val url: String
)
