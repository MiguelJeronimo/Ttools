package com.example.TibiaTools.operations

/**
 * Calcula cuánto tiempo tiene que estar un personaje desconectado para llegar a la stamina
 * verde (39:00) y a la stamina completa (42:00).
 *
 * Reglas:
 * - Hasta 39:00: 3 minutos offline por cada minuto de stamina.
 * - De 39:00 a 42:00: 6 minutos offline por cada minuto de stamina.
 * - La regeneración empieza tras 10 minutos offline.
 *
 * Ejemplo:
 * ```kotlin
 * val current = StaminaCalculator.parse("37:20") ?: return showError()
 * StaminaCalculator.timeToGreenStamina(current).format() // "5:10"
 * StaminaCalculator.timeToFullStamina(current).format()  // "23:10"
 * ```
 */
object StaminaCalculator {

    /** Stamina máxima en minutos (42 h). */
    const val MAX_STAMINA_MINUTES = 42 * 60

    /**Stamina maxima en color verde 39:00**/
    private const val GREEN_THRESHOLD_MINUTES = 39 * 60

    /** Minutos offline para regenerar 1 minuto de stamina por debajo de la zona verde. */
    const val OFFLINE_MINUTES_PER_NORMAL_MINUTE = 3

    /** Minutos offline para regenerar 1 minuto de stamina dentro de la zona verde. */
    const val OFFLINE_MINUTES_PER_GREEN_MINUTE = 6

    /** Minutos offline antes de que empiece la regeneración. */
    const val REGENERATION_DELAY_MINUTES = 10

    private val INPUT_REGEX = Regex("""^(\d{1,2}):([0-5]\d)$""")

    /**
     * Interpreta el texto que escribe el usuario, con formato `H:mm` o `HH:mm`.
     *
     * @return la stamina indicada, o `null` si el formato no es válido o supera 42:00.
     */
    fun parse(input: String): StaminaTime? {
        val match = INPUT_REGEX.matchEntire(input.trim()) ?: return null
        val (hours, minutes) = match.destructured
        val time = StaminaTime(hours.toInt(), minutes.toInt())
        return time.takeIf { it.totalMinutes <= MAX_STAMINA_MINUTES }
    }

    /**
     * Calcula el tiempo offline necesario para llegar a la stamina verde (39:00).
     *
     * Fórmula, con `actual` en minutos:
     * ```
     * tramoNormal = max(2340 - actual, 0) × 3
     * tiempoOffline = 0                  si tramoNormal = 0
     * tiempoOffline = tramoNormal + 10   en otro caso
     * ```
     *
     * Ejemplo con 37:20 (2240 min): `(2340 - 2240) × 3 + 10 = 310 min`, es decir, `5:10`.
     *
     * @param current stamina actual (0:00 – 42:00).
     * @return tiempo que hay que estar offline para llegar a 39:00, o 0:00 si ya se alcanzó.
     * @throws IllegalArgumentException si [current] supera la stamina máxima.
     */
    fun timeToGreenStamina(current: StaminaTime): StaminaTime {
        requireValid(current)
        return offlineTime(normalRegenerationMinutes(current))
    }

    /**
     * Calcula el tiempo offline necesario para llegar a la stamina completa (42:00).
     *
     * Fórmula, con `actual` en minutos:
     * ```
     * tramoNormal = max(2340 - actual, 0) × 3
     * tramoVerde = (2520 - max(actual, 2340)) × 6
     * tiempoOffline = 0                               si tramoNormal + tramoVerde = 0
     * tiempoOffline = tramoNormal + tramoVerde + 10   en otro caso
     * ```
     *
     * Ejemplo con 37:20 (2240 min): `(100 × 3) + (180 × 6) + 10 = 1390 min`, es decir, `23:10`.
     *
     * @param current stamina actual (0:00 – 42:00).
     * @return tiempo que hay que estar offline para llegar a 42:00, o 0:00 si ya se alcanzó.
     * @throws IllegalArgumentException si [current] supera la stamina máxima.
     */
    fun timeToFullStamina(current: StaminaTime): StaminaTime {
        requireValid(current)            
        return offlineTime(normalRegenerationMinutes(current) + greenRegenerationMinutes(current))
    }


    private fun requireValid(current: StaminaTime) {
        require(current.totalMinutes <= MAX_STAMINA_MINUTES) {
            "La stamina no puede superar 42:00 (recibido: ${current.format()})"
        }
    }

    private fun normalRegenerationMinutes(current: StaminaTime): Int =
        (GREEN_THRESHOLD_MINUTES - current.totalMinutes).coerceAtLeast(0) *
                OFFLINE_MINUTES_PER_NORMAL_MINUTE

    private fun greenRegenerationMinutes(current: StaminaTime): Int =
        (MAX_STAMINA_MINUTES - maxOf(current.totalMinutes, GREEN_THRESHOLD_MINUTES)) *
                OFFLINE_MINUTES_PER_GREEN_MINUTE

    private fun offlineTime(regenerationMinutes: Int): StaminaTime =
        if (regenerationMinutes == 0) {
            StaminaTime(0, 0)
        } else {
            StaminaTime.fromMinutes(regenerationMinutes + REGENERATION_DELAY_MINUTES)
        }
}

/**
 * Duración expresada en horas y minutos.
 *
 * @property hours horas (≥ 0).
 * @property minutes minutos (0–59).
 */
data class StaminaTime(val hours: Int, val minutes: Int) {
    init {
        require(hours >= 0) { "hours debe ser >= 0 (recibido: $hours)" }
        require(minutes in 0..59) { "minutes debe estar entre 0 y 59 (recibido: $minutes)" }
    }

    /** Duración total en minutos. */
    val totalMinutes: Int get() = hours * 60 + minutes

    /** @return la duración como `H:mm`, p. ej. `"15:05"`. */
    fun format(): String = "$hours:${minutes.toString().padStart(2, '0')}"

    companion object {
        /** Crea un [StaminaTime] a partir de un total de minutos (≥ 0). */
        fun fromMinutes(totalMinutes: Int): StaminaTime {
            require(totalMinutes >= 0) { "totalMinutes debe ser >= 0 (recibido: $totalMinutes)" }
            return StaminaTime(totalMinutes / 60, totalMinutes % 60)
        }
    }
}
