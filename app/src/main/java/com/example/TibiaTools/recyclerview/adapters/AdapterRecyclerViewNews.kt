package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewNews
import com.example.ttools.R

/**
 * Lista de noticias (news ticker) de la pantalla principal.
 *
 * Ejemplo:
 * ```kotlin
 * recyclerView.adapter = AdapterRecyclerViewNews(
 *     listOf(ItemsRecyclerViewNews("8000", "2026-10-14", "Server save delayed", "technical", "ticker", "https://..."))
 * )
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterRecyclerViewNews(
    private val items: List<ItemsRecyclerViewNews>
) : RecyclerView.Adapter<AdapterRecyclerViewNews.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_news_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date: TextView = itemView.findViewById(R.id.textViewFecha)
        private val category: TextView = itemView.findViewById(R.id.textViewCategoria)
        private val news: TextView = itemView.findViewById(R.id.textViewNoticia)
        private val type: TextView = itemView.findViewById(R.id.textViewTipo)

        fun bind(item: ItemsRecyclerViewNews) {
            date.text = item.date
            news.text = item.news
            category.text = item.category
            type.text = item.type
        }
    }
}
