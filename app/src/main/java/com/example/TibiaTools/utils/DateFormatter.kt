package com.example.TibiaTools.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/**
 * Convierte las fechas que devuelve la API de TibiaData (ISO-8601 en UTC, p. ej.
 * `2026-09-25T18:30:00Z`) a un texto legible en la zona horaria del dispositivo.
 *
 * Sustituye a `ConvertidorFecha`, que tenía tres problemas:
 * - Interpretaba la `Z` (UTC) como hora de `America/Tijuana`, desplazando la hora real.
 * - Guardaba estado mutable (`expiryDateString`/`date`), así que una fecha inválida
 *   devolvía el valor de la llamada anterior.
 * - Devolvía `Date.toString()`, un formato pensado para depurar, no para mostrar.
 *
 * Es un `object` sin estado: cada llamada crea su propio [SimpleDateFormat], porque esa
 * clase no es thread-safe.
 *
 * Ejemplo:
 * ```kotlin
 * DateFormatter.format("2026-09-25T18:30:00Z", TimeZone.getTimeZone("America/Mexico_City"))
 * // "25/09/2026 12:30"
 *
 * DateFormatter.format("fecha-invalida") // null
 *
 * lastLogin.text = DateFormatter.format(character.last_login) ?: "-"
 * ```
 */
object DateFormatter {

    /** Formato en el que la API de TibiaData devuelve las fechas (siempre en UTC). */
    const val API_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    /** Formato por defecto con el que se muestran las fechas en la UI. */
    const val DISPLAY_PATTERN = "dd/MM/yyyy HH:mm"

    private val UTC: TimeZone = TimeZone.getTimeZone("UTC")

    /**
     * Formatea una fecha de la API.
     *
     * @param raw fecha en formato [API_PATTERN]; puede ser `null` o estar vacía.
     * @param targetTimeZone zona horaria en la que se mostrará la fecha.
     * @param displayPattern patrón de salida, compatible con [SimpleDateFormat].
     * @param locale idioma usado para nombres de meses/días si el patrón los incluye.
     * @return la fecha formateada, o `null` si [raw] es nulo, vacío o no cumple [API_PATTERN].
     */
    fun format(
        raw: String?,
        targetTimeZone: TimeZone = TimeZone.getDefault(),
        displayPattern: String = DISPLAY_PATTERN,
        locale: Locale = Locale.getDefault()
    ): String? {
        if (raw.isNullOrBlank()) return null

        val parser = SimpleDateFormat(API_PATTERN, Locale.US).apply {
            timeZone = UTC
            isLenient = false
        }
        val date = try {
            parser.parse(raw)
        } catch (e: ParseException) {
            null
        } ?: return null

        return SimpleDateFormat(displayPattern, locale)
            .apply { timeZone = targetTimeZone }
            .format(date)
    }
}
