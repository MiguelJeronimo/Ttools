package com.example.TibiaTools.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Comprueba si el dispositivo tiene una red activa por Wi-Fi, datos móviles o Ethernet.
 *
 * Sustituye a `RedValidator`. Cambios:
 * - En API 21 y 22 (`minSdk` es 21) el código anterior devolvía siempre `false`, porque
 *   `getActiveNetwork()` no existe en esas versiones. Ahora usa `activeNetworkInfo`.
 * - Quita los `Log.i` de depuración y el código comentado.
 * - Acepta cualquier [Context]. Internamente usa `applicationContext`, así que pasarle
 *   una Activity no la retiene.
 *
 * Es una comprobación puntual. Para reaccionar a cambios de red en tiempo real conviene
 * exponer un `Flow<Boolean>` con `ConnectivityManager.registerDefaultNetworkCallback`
 * desde la capa de datos.
 *
 * Ejemplo:
 * ```kotlin
 * if (NetworkValidator.isConnected(this)) {
 *     viewModel.load()
 * } else {
 *     Toast.makeText(this, "Sin conexión", Toast.LENGTH_SHORT).show()
 * }
 * ```
 */
object NetworkValidator {

    /** Transportes que se consideran conexión válida. */
    internal val SUPPORTED_TRANSPORTS = intArrayOf(
        NetworkCapabilities.TRANSPORT_WIFI,
        NetworkCapabilities.TRANSPORT_CELLULAR,
        NetworkCapabilities.TRANSPORT_ETHERNET
    )

    /**
     * @return `true` si hay una red activa con alguno de los [SUPPORTED_TRANSPORTS].
     */
    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: return false
        return isConnected(connectivityManager, Build.VERSION.SDK_INT)
    }

    /**
     * Versión con el nivel de API inyectable, para poder probar ambas ramas.
     */
    @SuppressLint("NewApi") // La rama se elige con sdkInt, que lint no sabe evaluar.
    @Suppress("DEPRECATION")
    internal fun isConnected(connectivityManager: ConnectivityManager, sdkInt: Int): Boolean =
        if (sdkInt >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            hasSupportedTransport(network?.let(connectivityManager::getNetworkCapabilities))
        } else {
            connectivityManager.activeNetworkInfo?.isConnected == true
        }

    /**
     * @return `true` si [capabilities] no es nulo y tiene alguno de los [SUPPORTED_TRANSPORTS].
     */
    internal fun hasSupportedTransport(capabilities: NetworkCapabilities?): Boolean =
        capabilities != null && SUPPORTED_TRANSPORTS.any(capabilities::hasTransport)
}
