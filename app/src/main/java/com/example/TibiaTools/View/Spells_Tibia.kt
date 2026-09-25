package com.example.TibiaTools.View

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelSpells
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewSpells
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewSpells
import com.example.ttools.R
import com.example.ttools.databinding.ActivitySpellsTibiaBinding
import java.util.ArrayList
import java.util.Objects

class Spells_Tibia : AppCompatActivity() {
    private lateinit var binding: ActivitySpellsTibiaBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterRecyclerViewSpells
    private val itemsRecyclerViewSpellsList = ArrayList<ItemsRecyclerViewSpells>()
    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var viewModelSpells: ViewModelSpells

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpellsTibiaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerSpells)
        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        recyclerView.layoutManager = linearLayoutManager
        adapter = AdapterRecyclerViewSpells(itemsRecyclerViewSpellsList)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModelProvider = ViewModelProvider(this)
        viewModelSpells = viewModelProvider[ViewModelSpells::class.java]

        viewModelSpells.spells().observe(this) { spells ->
            if (spells != null) {
                var stateGroup: String? = null
                var stateType: String? = null
                var statePremium: String? = null
                val spellList = spells.spells_list
                if (spellList != null) {
                    spellList.forEach { item ->
                        if (item.isGroup_support()) stateGroup = "Support"
                        if (item.isGroup_healing()) stateGroup = "Healing"
                        if (item.isGroup_attack()) stateGroup = "Attack"
                        if (item.isType_instant()) stateType = "Instant"
                        if (item.isType_rune()) stateType = "Rune"
                        statePremium = if (item.isPremium_only()) "Premium Only" else "Free"

                        itemsRecyclerViewSpellsList.add(
                            ItemsRecyclerViewSpells(
                                item.name,
                                item.formula,
                                item.mana.toString(),
                                item.price.toString(),
                                stateType,
                                stateGroup,
                                item.spell_id,
                                statePremium,
                                item.level.toString()
                            )
                        )
                        recyclerView.visibility = View.VISIBLE
                    }
                }
            } else {
                Toast.makeText(applicationContext, "No hay respuesta del servidor", Toast.LENGTH_SHORT).show()
            }
            binding.root.findViewById<View>(R.id.carga_spells)?.visibility = View.GONE
            adapter.notifyDataSetChanged()
        }

        adapter.setOnClickListener { view ->
            val position = recyclerView.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION) {
                var id = itemsRecyclerViewSpellsList[position].spellId
                val name = itemsRecyclerViewSpellsList[position].nombre
                var id1 = id.replace("'s", "s")
                var id3 = id1.replace(" ", "")
                var idMinusculas = id3.lowercase()
                if (idMinusculas == "apprenticesstrike") {
                    id = id.replace("'s ", "")
                    idMinusculas = id.lowercase()
                }
                val intent = Intent(this, SpellInformationActivity::class.java).apply {
                    putExtra("ID", idMinusculas)
                    putExtra("name", name)
                }
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
