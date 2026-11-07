package com.example.TibiaTools.operations

/**
 * Calcula el rango de niveles con los que un personaje puede compartir experiencia en party.
 *
 * Regla de Tibia: el nivel más bajo de la party debe ser al menos 2/3 del más alto.
 * Para un personaje de nivel `L`:
 * - nivel mínimo = ⌈L × 2 / 3⌉
 * - nivel máximo = ⌊L × 3 / 2⌋
 *
 * Sustituye a `ExperienciaCompartida`, que heredaba de `calcularBlessings` sin relación
 * real entre ambas clases y guardaba los resultados en campos mutables. Además usaba
 * `Double` con `Math.ceil`/`Math.floor`. Aquí se usa aritmética entera, que es exacta y
 * no tiene errores de redondeo.
 *
 * Ejemplo:
 * ```kotlin
 * val range = SharedExperienceCalculator.calculate(100)
 * range.minLevel // 67
 * range.maxLevel // 150
 * 120 in range   // true
 * ```
 */
object SharedExperienceCalculator {

    /**
     * @param level nivel del personaje (≥ 1).
     * @throws IllegalArgumentException si [level] < 1.
     */
    fun calculate(level: Int): SharedExperienceRange {
        require(level >= 1) { "El nivel debe ser >= 1 (recibido: $level)" }
        val lvl = level.toLong()
        val min = (lvl * 2 + 2) / 3 // ⌈2L/3⌉ con enteros
        val max = lvl * 3 / 2       // ⌊3L/2⌋ con enteros
        return SharedExperienceRange(minLevel = min.toInt(), maxLevel = max.coerceAtMost(Int.MAX_VALUE.toLong()).toInt())
    }
}

/**
 * Rango cerrado `[minLevel, maxLevel]` de niveles con los que se comparte experiencia.
 */
data class SharedExperienceRange(val minLevel: Int, val maxLevel: Int) {
    /** @return `true` si [level] está dentro del rango (incluye los extremos). */
    operator fun contains(level: Int): Boolean = level in minLevel..maxLevel
}
