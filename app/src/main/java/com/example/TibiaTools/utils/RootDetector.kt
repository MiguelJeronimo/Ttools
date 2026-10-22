package com.example.TibiaTools.utils

import java.io.File

/**
 * Heurística sencilla para detectar si el dispositivo está rooteado. Comprueba que
 * exista `Superuser.apk` y que el binario `su` se pueda ejecutar.
 *
 * Sustituye a `utilidades.isPhoneRooted()` / `utilidades.suRun()`. Las comprobaciones
 * se inyectan por constructor (inversión de dependencias), así se puede probar sin un
 * dispositivo real y registrar en Koin como `single { RootDetector() }`.
 *
 * No es una medida de seguridad fiable: es fácil saltársela. Si de verdad hace falta,
 * usa Play Integrity API.
 *
 * Ejemplo:
 * ```kotlin
 * val detector = RootDetector()
 * if (detector.isRooted()) showRootWarning()
 *
 * // En tests:
 * val fake = RootDetector(fileExists = { true }, canRunSu = { true })
 * fake.isRooted() // true
 * ```
 *
 * @param fileExists indica si existe un archivo en la ruta dada.
 * @param canRunSu indica si se puede ejecutar `su -c exit` sin errores. Bloquea el hilo
 * mientras se ejecuta el proceso, así que no lo llames desde el hilo principal.
 */
class RootDetector(
    private val fileExists: (path: String) -> Boolean = { path -> File(path).exists() },
    private val canRunSu: () -> Boolean = ::runSu
) {

    /** @return `true` solo si existen [SUPERUSER_APK_PATH] y un `su` que se pueda ejecutar. */
    fun isRooted(): Boolean =
        runCatching { fileExists(SUPERUSER_APK_PATH) && canRunSu() }.getOrDefault(false)

    companion object {
        /** APK de SuperSU/Superuser que suelen instalar los dispositivos rooteados. */
        const val SUPERUSER_APK_PATH = "/system/app/Superuser.apk"

        private fun runSu(): Boolean = runCatching {
            Runtime.getRuntime().exec(arrayOf("su", "-c", "exit")).waitFor() == 0
        }.getOrDefault(false)
    }
}
