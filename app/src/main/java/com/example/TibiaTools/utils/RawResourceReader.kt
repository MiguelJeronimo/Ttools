package com.example.TibiaTools.utils

import java.io.IOException
import java.io.InputStream

/**
 * Lee archivos de texto plano (normalmente de `res/raw`) que alimentan los spinners
 * de vocaciones, categorías y ciudades.
 *
 * Sustituye a `Spinners`, que tenía tres métodos idénticos (`spinnerVocations`,
 * `spinnerCategory` y `LeerDataCitys`), y a `utilidades.TibiaMapps`. Ahora el stream se
 * cierra siempre (con `use`), incluso si la lectura falla.
 *
 * Lee de forma bloqueante: llámalo fuera del hilo principal, por ejemplo con
 * `withContext(Dispatchers.IO)`.
 *
 * Ejemplo:
 * ```kotlin
 * val vocations = RawResourceReader.readLines(resources.openRawResource(R.raw.vocations))
 * // ["Seleccione una vocación", "Knight", "Paladin", ...]
 *
 * val body = RawResourceReader.readText(resources.openRawResource(R.raw.mi_archivo), skipLines = 1)
 * // Todas las líneas menos la primera, concatenadas sin separador
 * ```
 */
object RawResourceReader {

    /**
     * @param input stream de texto UTF-8. Esta función lo cierra.
     * @return una entrada por línea, en orden. Si hay un error de lectura, una lista vacía.
     */
    fun readLines(input: InputStream): List<String> =
        try {
            input.bufferedReader(Charsets.UTF_8).useLines { it.toList() }
        } catch (e: IOException) {
            emptyList()
        }

    /**
     * @param input stream de texto UTF-8. Esta función lo cierra.
     * @param skipLines número de líneas iniciales que se descartan (p. ej. una cabecera).
     * @return las líneas restantes concatenadas sin separador.
     */
    fun readText(input: InputStream, skipLines: Int = 0): String {
        require(skipLines >= 0) { "skipLines debe ser >= 0 (recibido: $skipLines)" }
        return readLines(input).drop(skipLines).joinToString(separator = "")
    }
}
