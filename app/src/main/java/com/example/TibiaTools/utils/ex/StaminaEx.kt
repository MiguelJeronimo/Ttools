package com.example.TibiaTools.utils.ex

import com.example.TibiaTools.operations.StaminaCalculator
import com.example.TibiaTools.operations.StaminaTime

enum class StaminaLevel { GREEN, ORANGE, RED, EMPTY }

fun StaminaTime.level(): StaminaLevel = when {
    totalMinutes >= 39 * 60 -> StaminaLevel.GREEN
    totalMinutes >= 14 * 60 -> StaminaLevel.ORANGE
    totalMinutes > 0 -> StaminaLevel.RED
    else -> StaminaLevel.EMPTY
}

fun StaminaTime.progress(): Float =
    (totalMinutes.toFloat() / StaminaCalculator.MAX_STAMINA_MINUTES).coerceIn(0f, 1f)