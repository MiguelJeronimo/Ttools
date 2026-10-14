package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewHighScores
import com.example.ttools.R

/**
 * Lista del ranking (highscores).
 *
 * Ejemplo:
 * ```kotlin
 * recyclerView.adapter = AdapterRecyclerViewHighScores(
 *     listOf(ItemsRecyclerViewHighScores("1", "Bubble", "Elite Knight", "Antica", "1000", "Magic Level: 12", "Legend"))
 * )
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterRecyclerViewHighScores(
    private val items: List<ItemsRecyclerViewHighScores>
) : RecyclerView.Adapter<AdapterRecyclerViewHighScores.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_items_highscores, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rank: TextView = itemView.findViewById(R.id.textViewRank)
        private val name: TextView = itemView.findViewById(R.id.textViewName)
        private val vocation: TextView = itemView.findViewById(R.id.textViewVocation)
        private val level: TextView = itemView.findViewById(R.id.textViewLevel)
        private val value: TextView = itemView.findViewById(R.id.textViewValue)
        private val title: TextView = itemView.findViewById(R.id.textViewTittlee)

        fun bind(item: ItemsRecyclerViewHighScores) {
            rank.text = item.rank
            name.text = item.name
            vocation.text = item.vocation
            level.text = "Level: ${item.level}"
            value.text = item.value
            title.text = item.title
        }
    }
}
