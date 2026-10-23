package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewCreatures
import com.example.TibiaTools.utils.GlideImageLoader
import com.example.TibiaTools.utils.ImageLoader
import com.example.ttools.R

/**
 * Lista de criaturas con su imagen. Sustituye a `adapterRecyclerViewCriatures`.
 *
 * Ejemplo:
 * ```kotlin
 * val adapter = AdapterRecyclerViewCreatures(creatures)
 * adapter.setOnClickListener { view ->
 *     val position = recyclerView.getChildAdapterPosition(view)
 *     if (position != RecyclerView.NO_POSITION) openCreature(creatures[position].race)
 * }
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 * @param imageLoader carga la imagen de la criatura; por defecto Glide.
 */
class AdapterRecyclerViewCreatures(
    private val items: List<ItemsRecyclerViewCreatures>,
    private val imageLoader: ImageLoader = GlideImageLoader
) : RecyclerView.Adapter<AdapterRecyclerViewCreatures.ViewHolder>() {

    private var listener: View.OnClickListener? = null

    /** Listener que se llama al pulsar una fila. Recibe la vista de la fila. */
    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_items_criatures, parent, false)
        view.setOnClickListener { listener?.onClick(it) }
        return ViewHolder(view, imageLoader)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.textViewName)
        private val race: TextView = itemView.findViewById(R.id.textViewRace)
        private val image: ImageView = itemView.findViewById(R.id.imageViewCriature)

        fun bind(item: ItemsRecyclerViewCreatures) {
            name.text = item.name
            race.text = "Race: ${item.race}"
            imageLoader.load(item.imageUrl, image)
        }
    }
}
