package com.example.TibiaTools.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * Formatea cantidades de oro (gold coins) con separador de miles.
 *
 * Antes se usaba `DecimalFormat("###,###.00")`, que tenía dos problemas: añadía decimales
 * (el oro siempre es entero) y formateaba `0` como `".00"`. Además el adapter de spells
 * hacía `Integer.parseInt` del precio y se cerraba si la API mandaba un valor no numérico.
 *
 * Ejemplo:
 * ```kotlin
 * GoldFormatter.format(1500L, Locale.US)     // "1,500"
 * GoldFormatter.formatCoins("2500", Locale.US) // "2,500 gold coins"
 * GoldFormatter.formatCoins("N/A")             // "N/A"
 * ```
 */
object GoldFormatter {

    const val COINS_SUFFIX = " gold coins"

    /** @return [amount] con separador de miles según [locale]. */
    fun format(amount: Long, locale: Locale = Locale.getDefault()): String =
        NumberFormat.getIntegerInstance(locale).format(amount)

    /**
     * @param raw cantidad tal como viene de la API.
     * @return la cantidad formateada más [COINS_SUFFIX], o [raw] sin cambios si no es un número.
     */
    fun formatCoins(raw: String, locale: Locale = Locale.getDefault()): String =
        raw.trim().toLongOrNull()?.let { format(it, locale) + COINS_SUFFIX } ?: raw
}
