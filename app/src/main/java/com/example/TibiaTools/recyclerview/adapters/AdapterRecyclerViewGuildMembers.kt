package com.example.TibiaTools.recyclerview.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewGuildMembers
import com.example.ttools.R

/**
 * Lista de miembros de una guild. Sustituye a `AdapterRecyclerViewGuildName`.
 *
 * Ejemplo:
 * ```kotlin
 * recyclerView.adapter = AdapterRecyclerViewGuildMembers(
 *     listOf(ItemsRecyclerViewGuildMembers("Bubble", "", "Leader", "Elite Knight", 500, "2020-01-01", "online"))
 * )
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterRecyclerViewGuildMembers(
    private val items: List<ItemsRecyclerViewGuildMembers>
) : RecyclerView.Adapter<AdapterRecyclerViewGuildMembers.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_guilds_members, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.textViewGuildName)
        private val title: TextView = itemView.findViewById(R.id.textViewTitulo)
        private val rank: TextView = itemView.findViewById(R.id.textViewRango)
        private val vocation: TextView = itemView.findViewById(R.id.textViewVocation)
        private val level: TextView = itemView.findViewById(R.id.textViewLevel)
        private val status: TextView = itemView.findViewById(R.id.textViewStatus)
        private val joined: TextView = itemView.findViewById(R.id.textViewUnido)

        fun bind(item: ItemsRecyclerViewGuildMembers) {
            name.text = item.name
            title.text = "Titulo: ${item.title}"
            rank.text = "Rango: ${item.rank}"
            vocation.text = item.vocation
            level.text = "Level: ${item.level}"
            status.setTextColor(if (item.isOnline) Color.GREEN else OFFLINE_COLOR)
            status.text = "Status: ${item.status}"
            joined.text = "Unido: ${item.joined}"
        }
    }

    companion object {
        /** #9B0000 */
        const val OFFLINE_COLOR: Int = 0xFF9B0000.toInt()
    }
}
