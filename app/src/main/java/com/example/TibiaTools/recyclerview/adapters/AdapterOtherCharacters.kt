package com.example.TibiaTools.recyclerview.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsCharacters
import com.example.ttools.R

/**
 * Lista horizontal con los demás personajes de la cuenta.
 *
 * Corrige un bug de reciclado: los textos "Main"/"Delete" y el color verde de "online" se
 * aplicaban pero nunca se quitaban, así que al hacer scroll aparecían en otros personajes.
 *
 * Ejemplo:
 * ```kotlin
 * recyclerView.adapter = AdapterOtherCharacters(
 *     listOf(ItemsCharacters("Bubble", "Antica", "online", deleted = false, main = true, traded = false))
 * )
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterOtherCharacters(
    private val items: List<ItemsCharacters>
) : RecyclerView.Adapter<AdapterOtherCharacters.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_characters_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.nameCharater)
        private val status: TextView = itemView.findViewById(R.id.characterStatus)
        private val deleted: TextView = itemView.findViewById(R.id.characterDelete)
        private val main: TextView = itemView.findViewById(R.id.characterMain)
        private val world: TextView = itemView.findViewById(R.id.characterWorld)

        /** Color original del layout, para restaurarlo al reciclar la vista. */
        private val defaultStatusColors: ColorStateList? = status.textColors

        fun bind(item: ItemsCharacters) {
            name.text = item.name
            world.text = item.world

            status.text = item.status
            if (item.isOnline) {
                status.setTextColor(Color.GREEN)
            } else {
                defaultStatusColors?.let(status::setTextColor)
            }

            main.text = if (item.main) MAIN_LABEL else ""
            if (item.main) main.setTextColor(MAIN_COLOR)

            deleted.text = if (item.deleted) DELETED_LABEL else ""
        }
    }

    companion object {
        const val MAIN_LABEL = "Main"
        const val DELETED_LABEL = "Delete"
        /** #FFB4AB */
        const val MAIN_COLOR: Int = 0xFFFFB4AB.toInt()
    }
}
