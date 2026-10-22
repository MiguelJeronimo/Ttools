package com.example.TibiaTools.recyclerview.items

/**
 * Logro de un personaje, tal como lo pinta [com.example.TibiaTools.recyclerview.adapters.AdapterAchievementsCharacter].
 *
 * Ejemplo:
 * ```kotlin
 * ItemsAchievementsCharacter(achievementsName = "Allow Cookies?", achievementsGrade = "1", secret = false)
 * ```
 *
 * @property achievementsName nombre del logro.
 * @property achievementsGrade grado del logro (1–3).
 * @property secret `true` si es un logro secreto.
 */
data class ItemsAchievementsCharacter(
    val achievementsName: String,
    val achievementsGrade: String,
    val secret: Boolean
)
