package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewGuilds
import com.example.TibiaTools.utils.GlideImageLoader
import com.example.TibiaTools.utils.ImageLoader
import com.example.ttools.R

/**
 * Lista de guilds de un mundo, con su logo.
 *
 * Ejemplo:
 * ```kotlin
 * val adapter = AdapterRecyclerViewGuildsList(items)
 * adapter.setOnClickListener { view ->
 *     val position = recyclerView.getChildAdapterPosition(view)
 *     if (position != RecyclerView.NO_POSITION) openGuild(items[position].name)
 * }
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 * @param imageLoader carga el logo; por defecto Glide.
 */
class AdapterRecyclerViewGuildsList(
    private val items: List<ItemsRecyclerViewGuilds>,
    private val imageLoader: ImageLoader = GlideImageLoader
) : RecyclerView.Adapter<AdapterRecyclerViewGuildsList.ViewHolder>() {

    private var listener: View.OnClickListener? = null

    /** Listener que se llama al pulsar una fila. Recibe la vista de la fila. */
    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_items_guilds, parent, false)
        view.setOnClickListener { listener?.onClick(it) }
        return ViewHolder(view, imageLoader)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(itemView) {
        private val logo: ImageView = itemView.findViewById(R.id.imageViewLogo)
        private val name: TextView = itemView.findViewById(R.id.textViewName)
        private val description: TextView = itemView.findViewById(R.id.textViewDescription)

        fun bind(item: ItemsRecyclerViewGuilds) {
            name.text = item.name
            description.text = item.description
            imageLoader.load(item.logoUrl, logo)
        }
    }
}
