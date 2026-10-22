package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewHouses
import com.example.ttools.R

/**
 * Lista de casas y guildhalls de una ciudad. Las alquiladas se pintan en verde y las
 * libres en rojo.
 *
 * Ejemplo:
 * ```kotlin
 * val adapter = AdapterRecyclerViewHouses(listHouses)
 * adapter.setOnClickListener { view ->
 *     val position = recyclerView.getChildAdapterPosition(view)
 *     if (position != RecyclerView.NO_POSITION) openHouse(listHouses[position].houseId)
 * }
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterRecyclerViewHouses(
    private val items: List<ItemsRecyclerViewHouses>
) : RecyclerView.Adapter<AdapterRecyclerViewHouses.ViewHolder>() {

    private var listener: View.OnClickListener? = null

    /** Listener que se llama al pulsar una fila. Recibe la vista de la fila. */
    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_items_houses, parent, false)
        view.setOnClickListener { listener?.onClick(it) }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.house_name)
        private val size: TextView = itemView.findViewById(R.id.house_size)
        private val rent: TextView = itemView.findViewById(R.id.house_rent)
        private val rented: TextView = itemView.findViewById(R.id.house_rented)
        private val id: TextView = itemView.findViewById(R.id.house_id)

        fun bind(item: ItemsRecyclerViewHouses) {
            name.text = item.houseName
            size.text = item.houseSize
            rent.text = item.houseRent
            rented.setTextColor(if (item.isRented) RENTED_COLOR else AVAILABLE_COLOR)
            rented.text = item.houseRented
            id.text = item.houseId
        }
    }

    companion object {
        /** #00FF00 */
        const val RENTED_COLOR: Int = 0xFF00FF00.toInt()
        /** #F10300 */
        const val AVAILABLE_COLOR: Int = 0xFFF10300.toInt()
    }
}
