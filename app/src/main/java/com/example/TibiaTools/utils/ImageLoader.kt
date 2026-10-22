package com.example.TibiaTools.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

/**
 * Abstracción para cargar imágenes remotas en un [ImageView].
 *
 * Los adapters la reciben por constructor en lugar de llamar a Glide/Picasso
 * directamente. Así se pueden probar con un fake y cambiar de librería (p. ej. a Coil,
 * que ya usa Compose) sin tocar los adapters.
 *
 * Ejemplo:
 * ```kotlin
 * val adapter = AdapterRecyclerViewGuildsList(items, imageLoader = GlideImageLoader)
 *
 * // En tests:
 * val loaded = mutableListOf<String>()
 * val fake = ImageLoader { url, _ -> loaded += url }
 * ```
 */
fun interface ImageLoader {
    /**
     * @param url dirección de la imagen. Si está vacía, la implementación debe limpiar [target].
     * @param target vista donde se pinta la imagen.
     */
    fun load(url: String, target: ImageView)
}

/** [ImageLoader] basado en Glide, que se encarga del ciclo de vida de la vista. */
object GlideImageLoader : ImageLoader {
    override fun load(url: String, target: ImageView) {
        if (url.isBlank()) {
            Glide.with(target).clear(target)
            target.setImageDrawable(null)
            return
        }
        Glide.with(target).load(url).into(target)
    }
}

/**
 * [ImageLoader] basado en Picasso. Se mantiene para la lista de criaturas, que ya lo usaba.
 * Picasso lanza `IllegalArgumentException` si recibe una ruta vacía, así que aquí se filtra.
 */
object PicassoImageLoader : ImageLoader {
    override fun load(url: String, target: ImageView) {
        if (url.isBlank()) {
            Picasso.get().cancelRequest(target)
            target.setImageDrawable(null)
            return
        }
        Picasso.get().load(url).into(target)
    }
}
