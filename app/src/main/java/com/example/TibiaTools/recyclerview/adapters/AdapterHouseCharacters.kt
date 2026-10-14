package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsHousesCharacters
import com.example.ttools.R

/**
 * Lista horizontal de casas de un personaje.
 *
 * Ejemplo:
 * ```kotlin
 * recyclerView.adapter = AdapterHouseCharacters(
 *     listOf(ItemsHousesCharacters("Sweet Home", "Thais", "2026-10-30", 10101))
 * )
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterHouseCharacters(
    private val items: List<ItemsHousesCharacters>
) : RecyclerView.Adapter<AdapterHouseCharacters.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_houses_recyclerview_characters, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val houseName: TextView = itemView.findViewById(R.id.houseName)
        private val houseCity: TextView = itemView.findViewById(R.id.houseCity)
        private val houseDateRent: TextView = itemView.findViewById(R.id.houseDateRent)
        private val idHouseRent: TextView = itemView.findViewById(R.id.idHouseRent)

        fun bind(item: ItemsHousesCharacters) {
            houseName.text = item.name
            houseCity.text = item.town
            houseDateRent.text = item.paid
            idHouseRent.text = item.houseid.toString()
        }
    }
}
