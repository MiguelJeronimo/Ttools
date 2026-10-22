package com.example.TibiaTools.operations

/**
 * Calcula cuánto tiempo tiene que estar un personaje desconectado para recuperar toda su
 * stamina (42:00).
 *
 * Regla que se usa (la misma que tenía `staminaTibia`):
 * `tiempo offline = (minutos que faltan) × 6 + 10`. Los 10 minutos son la espera antes de
 * que empiece la regeneración.
 *
 * Sustituye a `staminaTibia`, que tenía 9 campos mutables y un "constructor" que en
 * realidad era un método `void`. Cambios de comportamiento:
 * - [parse] rechaza valores mayores que 42:00. Antes la regex aceptaba hasta 42:59 y el
 *   resultado salía negativo.
 * - [parse] acepta 0:xx. Antes se descartaba sin avisar al usuario.
 * - Si la stamina ya está completa, el resultado es 0:00 (antes salía 0:10).
 *
 * Ejemplo:
 * ```kotlin
 * val current = StaminaCalculator.parse("39:30") ?: return showError()
 * val offline = StaminaCalculator.timeToFullStamina(current)
 * offline.hours   // 15
 * offline.minutes // 10
 * offline.format() // "15:10"
 * ```
 */
object StaminaCalculator {

    /** Stamina máxima en minutos (42 h). */
    const val MAX_STAMINA_MINUTES = 42 * 60

    /** Minutos offline necesarios para regenerar 1 minuto de stamina. */
    const val OFFLINE_MINUTES_PER_STAMINA_MINUTE = 6

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
     * @param current stamina actual (0:00 – 42:00).
     * @return tiempo que hay que estar offline para llegar a 42:00.
     * @throws IllegalArgumentException si [current] supera la stamina máxima.
     */
    fun timeToFullStamina(current: StaminaTime): StaminaTime {
        require(current.totalMinutes <= MAX_STAMINA_MINUTES) {
            "La stamina no puede superar 42:00 (recibido: ${current.format()})"
        }
        val missing = MAX_STAMINA_MINUTES - current.totalMinutes
        if (missing == 0) return StaminaTime(0, 0)
        return StaminaTime.fromMinutes(missing * OFFLINE_MINUTES_PER_STAMINA_MINUTE + REGENERATION_DELAY_MINUTES)
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
