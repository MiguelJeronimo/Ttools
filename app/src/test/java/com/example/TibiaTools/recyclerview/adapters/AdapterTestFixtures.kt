package com.example.TibiaTools.recyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot

/**
 * Vista de fila simulada: `findViewById` devuelve un [TextView] o un [ImageView] relajado
 * por cada id registrado. Así se pueden probar los ViewHolder en la JVM, sin Robolectric
 * ni emulador.
 */
internal class FakeItemView(
    textIds: List<Int>,
    imageIds: List<Int> = emptyList()
) {
    val textViews: Map<Int, TextView> = textIds.associateWith { mockk<TextView>(relaxed = true) }
    val imageViews: Map<Int, ImageView> = imageIds.associateWith { mockk<ImageView>(relaxed = true) }

    /** Listener que el adapter registra sobre la fila en `onCreateViewHolder`. */
    val clickListener: CapturingSlot<View.OnClickListener> = slot()

    val view: View = mockk(relaxed = true) {
        every { findViewById<View>(any()) } answers {
            val id = firstArg<Int>()
            textViews[id] ?: imageViews[id]
        }
        every { setOnClickListener(capture(clickListener)) } answers { }
    }

    fun text(id: Int): TextView = requireNotNull(textViews[id]) { "id $id no registrado" }
    fun image(id: Int): ImageView = requireNotNull(imageViews[id]) { "id $id no registrado" }
}

/**
 * Simula `LayoutInflater.from(parent.context).inflate(layoutId, parent, false)` para que
 * devuelva [itemView]. Llama a `unmockkStatic(LayoutInflater::class)` en `@After`.
 *
 * @return el `parent` que hay que pasar a `onCreateViewHolder`.
 */
internal fun stubInflation(layoutId: Int, itemView: View): ViewGroup {
    val context = mockk<Context>(relaxed = true)
    val parent = mockk<ViewGroup>(relaxed = true) { every { this@mockk.context } returns context }
    val inflater = mockk<LayoutInflater> {
        every { inflate(layoutId, parent, false) } returns itemView
    }
    mockkStatic(LayoutInflater::class)
    every { LayoutInflater.from(context) } returns inflater
    return parent
}
