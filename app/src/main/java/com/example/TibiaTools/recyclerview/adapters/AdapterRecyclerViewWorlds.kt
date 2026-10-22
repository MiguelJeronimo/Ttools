package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsRecyclerViewWorlds
import com.example.ttools.R

/**
 * Lista de mundos con sus características. Sustituye a `adapterRecyclerviewMundos`.
 * Corrige el texto "Tournament world type", al que le faltaba el separador `": "`.
 *
 * Ejemplo:
 * ```kotlin
 * recyclerView.adapter = AdapterRecyclerViewWorlds(worlds)
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterRecyclerViewWorlds(
    private val items: List<ItemsRecyclerViewWorlds>
) : RecyclerView.Adapter<AdapterRecyclerViewWorlds.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_items_mundo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.world)
        private val status: TextView = itemView.findViewById(R.id.status)
        private val location: TextView = itemView.findViewById(R.id.location)
        private val pvpType: TextView = itemView.findViewById(R.id.pvptype)
        private val premiumOnly: TextView = itemView.findViewById(R.id.premium_only)
        private val transferType: TextView = itemView.findViewById(R.id.transfer_type)
        private val playersOnline: TextView = itemView.findViewById(R.id.player_online)
        private val gameWorldType: TextView = itemView.findViewById(R.id.game_world_type)
        private val battleyeProtected: TextView = itemView.findViewById(R.id.battleeye_protected)
        private val battleyeDate: TextView = itemView.findViewById(R.id.battleeye_date)
        private val tournamentWorldType: TextView = itemView.findViewById(R.id.tournament_world_type)

        fun bind(item: ItemsRecyclerViewWorlds) {
            name.text = item.name
            status.text = item.status
            location.text = "Location: ${item.location}"
            pvpType.text = "Pvp type: ${item.pvpType}"
            premiumOnly.text = "Premium only: ${item.premiumOnly}"
            transferType.text = "Transfer type: ${item.transferType}"
            playersOnline.text = "Players Online: ${item.playersOnline}"
            gameWorldType.text = "Game world type: ${item.gameWorldType}"
            battleyeProtected.text = "BattleEye protected: ${item.battleyeProtected}"
            battleyeDate.text = "BattleEye date: ${item.battleyeDate}"
            tournamentWorldType.text = "Tournament world type: ${item.tournamentWorldType}"
        }
    }
}
