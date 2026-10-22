package com.example.TibiaTools.utils

import android.view.View
import com.example.ttools.R

/**
 * Muestra u oculta, de una sola vez, las vistas con la información general de un personaje
 * en la pantalla `characters`.
 *
 * Sustituye a `IsVisibillityCharacters`:
 * - Ya no guarda un flag mutable; la visibilidad se pasa como parámetro.
 * - Recibe la vista raíz en lugar del `ActivityCharactersBinding`, así no depende de una
 *   pantalla concreta y se puede probar con una vista simulada.
 * - Elimina el `System.out.println` y el id `separador` que estaba duplicado.
 * - Si un id no existe en el layout lo ignora en lugar de lanzar `NullPointerException`.
 *
 * Ejemplo:
 * ```kotlin
 * // Antes de lanzar la búsqueda
 * CharacterInfoVisibility.apply(binding.root, visible = false)
 *
 * // Cuando llega la respuesta
 * CharacterInfoVisibility.apply(binding.root, visible = true)
 * ```
 */
object CharacterInfoVisibility {

    /** Ids de las vistas que forman el bloque de información general del personaje. */
    internal val viewIds: List<Int> = listOf(
        R.id.textView2,
        R.id.nombre,
        R.id.textView5,
        R.id.sexo,
        R.id.textView6,
        R.id.titulo,
        R.id.vocacion,
        R.id.textView4,
        R.id.textView7,
        R.id.nivel,
        R.id.textView11,
        R.id.achivement,
        R.id.mundo,
        R.id.textView10,
        R.id.textView15,
        R.id.residencia,
        R.id.textView18,
        R.id.separador,
        R.id.guild,
        R.id.textView22,
        R.id.last_loguin
    )

    /**
     * @param root vista que contiene el bloque (normalmente `binding.root`).
     * @param visible `true` → [View.VISIBLE]; `false` → [View.GONE].
     */
    fun apply(root: View, visible: Boolean) {
        val visibility = if (visible) View.VISIBLE else View.GONE
        viewIds.forEach { id -> root.findViewById<View>(id)?.visibility = visibility }
    }
}
