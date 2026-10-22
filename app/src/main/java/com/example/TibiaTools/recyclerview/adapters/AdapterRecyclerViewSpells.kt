package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewSpells
import com.example.TibiaTools.utils.GoldFormatter
import com.example.ttools.R

/**
 * Lista de hechizos.
 *
 * Antes hacía `Integer.parseInt(precio)` y la app se cerraba si la API mandaba un precio
 * no numérico. Ahora el formato lo hace [GoldFormatter].
 *
 * Ejemplo:
 * ```kotlin
 * val adapter = AdapterRecyclerViewSpells(spells)
 * adapter.setOnClickListener { view ->
 *     val position = recyclerView.getChildAdapterPosition(view)
 *     if (position != RecyclerView.NO_POSITION) openSpell(spells[position].spellId)
 * }
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterRecyclerViewSpells(
    private val items: List<ItemsRecyclerViewSpells>
) : RecyclerView.Adapter<AdapterRecyclerViewSpells.ViewHolder>() {

    private var listener: View.OnClickListener? = null

    /** Listener que se llama al pulsar una fila. Recibe la vista de la fila. */
    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_spells_tibia, parent, false)
        view.setOnClickListener { listener?.onClick(it) }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.textViewName)
        private val formula: TextView = itemView.findViewById(R.id.textViewFormula)
        private val level: TextView = itemView.findViewById(R.id.textViewNivvel)
        private val mana: TextView = itemView.findViewById(R.id.textViewMana)
        private val price: TextView = itemView.findViewById(R.id.textViewPrecio)
        private val type: TextView = itemView.findViewById(R.id.textViewType)
        private val group: TextView = itemView.findViewById(R.id.textViewGroup)
        private val spellId: TextView = itemView.findViewById<TextView>(R.id.textViewSpell_Id)
            .apply { visibility = View.INVISIBLE }
        private val premium: TextView = itemView.findViewById(R.id.textViewPremium)

        fun bind(item: ItemsRecyclerViewSpells) {
            name.text = item.name
            formula.text = item.formula
            level.text = "Level: ${item.level}"
            mana.text = "Mana: ${item.mana}"
            price.text = GoldFormatter.formatCoins(item.price)
            type.text = item.type
            group.text = item.group
            spellId.text = item.spellId
            premium.text = item.premium
        }
    }
}
