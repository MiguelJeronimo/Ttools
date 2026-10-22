package com.example.TibiaTools.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.recyclerview.items.ItemsAchievementsCharacter
import com.example.ttools.R

/**
 * Lista horizontal de logros en la pantalla de personaje.
 *
 * Corrige un bug de reciclado: antes el texto "Secret" se ponía pero nunca se quitaba, así
 * que al hacer scroll aparecía en logros que no eran secretos.
 *
 * Ejemplo:
 * ```kotlin
 * recyclerView.adapter = AdapterAchievementsCharacter(
 *     listOf(ItemsAchievementsCharacter("Allow Cookies?", "1", secret = true))
 * )
 * ```
 *
 * @param items lista que se muestra. El adapter guarda la referencia: si la modificas,
 * llama a `notifyDataSetChanged()`.
 */
class AdapterAchievementsCharacter(
    private val items: List<ItemsAchievementsCharacter>
) : RecyclerView.Adapter<AdapterAchievementsCharacter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_achievements_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    /** Es una clase anidada (no `inner`): no retiene una referencia al adapter. */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.achievementsName)
        private val grade: TextView = itemView.findViewById(R.id.achievementsGrade)
        private val secret: TextView = itemView.findViewById(R.id.achievementsSecret)

        fun bind(item: ItemsAchievementsCharacter) {
            name.text = item.achievementsName
            grade.text = item.achievementsGrade
            secret.text = if (item.secret) SECRET_LABEL else ""
        }
    }

    companion object {
        const val SECRET_LABEL = "Secret"
    }
}
