package com.example.TibiaTools.operations

/**
 * Calcula el precio de las blessings de Tibia según el nivel del personaje.
 *
 * Sustituye a `calcularBlessings`, que guardaba cada resultado en un campo mutable y
 * obligaba a llamar a los métodos en un orden concreto (`blessingIndividual` →
 * `sumaBlessingsPrincipales` → getters). Aquí todas las funciones son puras: mismo
 * nivel, mismo resultado. Los importes son `Long` para que no haya desbordamiento.
 *
 * Fórmulas (en gold coins):
 *
 * | Blessing                                | Nivel ≤ 30 | Nivel 31–119       | Nivel ≥ 120                   |
 * |-----------------------------------------|------------|--------------------|-------------------------------|
 * | Regular (cada una de las 5)             | 2 000      | 200 × (nivel − 20) | 20 000 + 75 × (nivel − 120)   |
 * | Twist of Fate (PvP)                     | 2 000      | 200 × (nivel − 20) | 20 000                        |
 * | Heart / Blood of the Mountain (cada una)| 2 600      | 260 × (nivel − 20) | 26 000 + 100 × (nivel − 120)  |
 *
 * Ejemplo:
 * ```kotlin
 * BlessingCalculator.regularBlessingCost(100) // 16_000
 *
 * val costs = BlessingCalculator.calculate(
 *     level = 150,
 *     includeHeartOfTheMountain = true,
 *     includeBloodOfTheMountain = false
 * )
 * costs.regularBlessing     // 22_250
 * costs.regularBlessingsTotal // 111_250 (5 × 22_250)
 * costs.twistOfFate         // 20_000
 * costs.heartOfTheMountain  // 29_000
 * costs.bloodOfTheMountain  // 0
 * costs.total               // 160_250
 * ```
 */
object BlessingCalculator {

    /** Número de blessings regulares (Spiritual Shielding, Embrace of Tibia, Fire of the Suns, Wisdom of Solitude, Spark of the Phoenix). */
    const val REGULAR_BLESSINGS_COUNT = 5

    private const val LOW_LEVEL_CAP = 30
    private const val HIGH_LEVEL_THRESHOLD = 120
    private const val LEVEL_OFFSET = 20

    /**
     * @param level nivel del personaje (≥ 1).
     * @return precio de **una** blessing regular.
     */
    fun regularBlessingCost(level: Int): Long = tieredCost(
        level = level,
        lowLevelCost = 2_000,
        perLevelCost = 200,
        highLevelBase = 20_000,
        highLevelIncrement = 75
    )

    /**
     * @param level nivel del personaje (≥ 1).
     * @return precio de Twist of Fate. A partir del nivel 120 deja de subir.
     */
    fun twistOfFateCost(level: Int): Long = tieredCost(
        level = level,
        lowLevelCost = 2_000,
        perLevelCost = 200,
        highLevelBase = 20_000,
        highLevelIncrement = 0
    )

    /**
     * @param level nivel del personaje (≥ 1).
     * @return precio de **una** blessing mejorada (Heart of the Mountain o Blood of the Mountain).
     */
    fun enhancedBlessingCost(level: Int): Long = tieredCost(
        level = level,
        lowLevelCost = 2_600,
        perLevelCost = 260,
        highLevelBase = 26_000,
        highLevelIncrement = 100
    )

    /**
     * Calcula todo el desglose de una sola vez.
     *
     * @param level nivel del personaje (≥ 1).
     * @param includeHeartOfTheMountain si se compra Heart of the Mountain.
     * @param includeBloodOfTheMountain si se compra Blood of the Mountain.
     * @throws IllegalArgumentException si [level] < 1.
     */
    fun calculate(
        level: Int,
        includeHeartOfTheMountain: Boolean = false,
        includeBloodOfTheMountain: Boolean = false
    ): BlessingCosts {
        val enhanced = enhancedBlessingCost(level)
        return BlessingCosts(
            regularBlessing = regularBlessingCost(level),
            twistOfFate = twistOfFateCost(level),
            heartOfTheMountain = if (includeHeartOfTheMountain) enhanced else 0L,
            bloodOfTheMountain = if (includeBloodOfTheMountain) enhanced else 0L
        )
    }

    private fun tieredCost(
        level: Int,
        lowLevelCost: Long,
        perLevelCost: Long,
        highLevelBase: Long,
        highLevelIncrement: Long
    ): Long {
        require(level >= 1) { "El nivel debe ser >= 1 (recibido: $level)" }
        return when {
            level <= LOW_LEVEL_CAP -> lowLevelCost
            level < HIGH_LEVEL_THRESHOLD -> perLevelCost * (level - LEVEL_OFFSET)
            else -> highLevelBase + highLevelIncrement * (level - HIGH_LEVEL_THRESHOLD)
        }
    }
}

/**
 * Desglose de precios que devuelve [BlessingCalculator.calculate]. Una blessing que no se
 * compra vale `0`.
 *
 * @property regularBlessing precio de una blessing regular.
 * @property twistOfFate precio de Twist of Fate.
 * @property heartOfTheMountain precio de Heart of the Mountain, o `0` si no se incluye.
 * @property bloodOfTheMountain precio de Blood of the Mountain, o `0` si no se incluye.
 */
data class BlessingCosts(
    val regularBlessing: Long,
    val twistOfFate: Long,
    val heartOfTheMountain: Long,
    val bloodOfTheMountain: Long
) {
    /** Suma de las [BlessingCalculator.REGULAR_BLESSINGS_COUNT] blessings regulares, sin Twist of Fate. */
    val regularBlessingsTotal: Long
        get() = regularBlessing * BlessingCalculator.REGULAR_BLESSINGS_COUNT

    /** Precio total: regulares + Twist of Fate + las blessings mejoradas que se incluyan. */
    val total: Long
        get() = regularBlessingsTotal + twistOfFate + heartOfTheMountain + bloodOfTheMountain
}
