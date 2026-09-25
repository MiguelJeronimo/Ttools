package com.example.TibiaTools.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelGuilds
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewGuildsList
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewGuilds
import com.example.ttools.R
import com.example.ttools.databinding.ActivityGuildsBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.util.ArrayList
import java.util.Objects

class GuildInformation : AppCompatActivity() {
    private lateinit var binding: ActivityGuildsBinding
    private lateinit var spinner: AutoCompleteTextView
    private var adapter: ArrayAdapter<String>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptador: AdapterRecyclerViewGuildsList
    private val itemsRecyclerViewGuilds = ArrayList<ItemsRecyclerViewGuilds>()
    private lateinit var linearProgressIndicator: LinearProgressIndicator
    private val viewModelGuilds: ViewModelGuilds by inject()
    private lateinit var viewRoot: View

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuildsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        linearProgressIndicator = findViewById(R.id.carga_guilds)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        spinner = findViewById(R.id.spinner_guild)
        viewRoot = findViewById(android.R.id.content)

        recyclerView = findViewById(R.id.recyclerGuilds)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        adaptador = AdapterRecyclerViewGuildsList(itemsRecyclerViewGuilds)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adaptador

        viewModelGuilds.Worlds().observe(this) { worlds ->
            if (worlds != null) {
                adapter = ArrayAdapter(applicationContext, R.layout.auto_complete, worlds)
                spinner.setAdapter(adapter)
                linearProgressIndicator.visibility = View.GONE
            } else {
                Snackbar.make(viewRoot, "No charged worlds", Snackbar.LENGTH_LONG).show()
                linearProgressIndicator.visibility = View.GONE
            }
        }

        viewModelGuilds.Guild().observe(this) { guilds ->
            itemsRecyclerViewGuilds.clear()
            if (guilds != null) {
                val activeList = guilds.active
                if (activeList != null) {
                    activeList.forEach { guild ->
                        itemsRecyclerViewGuilds.add(
                            ItemsRecyclerViewGuilds(
                                guild.name,
                                guild.logo_url,
                                guild.description
                            )
                        )
                    }
                    adaptador.notifyDataSetChanged()
                    linearProgressIndicator.visibility = View.GONE
                } else {
                    Snackbar.make(viewRoot, "No guilds active", Snackbar.LENGTH_LONG).show()
                    linearProgressIndicator.visibility = View.GONE
                }
            } else {
                Snackbar.make(viewRoot, "Error loading data", Snackbar.LENGTH_LONG).show()
                linearProgressIndicator.visibility = View.GONE
            }
        }

        adaptador.setOnClickListener { view ->
            val nameGuild = itemsRecyclerViewGuilds[recyclerView.getChildAdapterPosition(view)].lbName
            val intent = Intent(this, GuildInformationName::class.java).apply {
                putExtra("nameGuild", nameGuild)
            }
            startActivity(intent)
        }

        spinner.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            if (selectedItem != "Seleccione") {
                linearProgressIndicator.visibility = View.VISIBLE
                viewModelGuilds.setGuild(selectedItem)
            } else {
                itemsRecyclerViewGuilds.clear()
                adaptador.notifyDataSetChanged()
                linearProgressIndicator.visibility = View.GONE
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
