package com.example.TibiaTools.View

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelWorlds
import com.example.TibiaTools.recyclerview.Adapters.adapterRecyclerviewMundos
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewMundos
import com.example.ttools.R
import com.example.ttools.databinding.ActivityMundosBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class Mundos : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: adapterRecyclerviewMundos
    private lateinit var binding: ActivityMundosBinding
    private val itemsRecyclerViewMundos = ArrayList<ItemsRecyclerViewMundos>()
    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var viewModelWorlds: ViewModelWorlds

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMundosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerviewmundos)
        recyclerView.setHasFixedSize(true)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        myAdapter = adapterRecyclerviewMundos(itemsRecyclerViewMundos)
        recyclerView.adapter = myAdapter

        viewModelProvider = ViewModelProvider(this)
        viewModelWorlds = viewModelProvider[ViewModelWorlds::class.java]

        viewModelWorlds.worlds().observe(this) { worlds ->
            if (worlds != null) {
                itemsRecyclerViewMundos.clear()
                worlds.regular_worlds.forEach { world ->
                    itemsRecyclerViewMundos.add(
                        ItemsRecyclerViewMundos(
                            world.name,
                            world.status,
                            world.players_online.toString(),
                            world.location,
                            world.pvp_type,
                            world.premium_only.toString(),
                            world.transfer_type,
                            world.battleye_protected.toString(),
                            world.battleye_date,
                            world.game_world_type,
                            world.tournament_world_type
                        )
                    )
                }
                myAdapter.notifyDataSetChanged()
                recyclerView.visibility = View.VISIBLE
            } else {
                Toast.makeText(applicationContext, "No hay respuesta del servidor", Toast.LENGTH_SHORT).show()
                recyclerView.visibility = View.GONE
            }
            binding.root.findViewById<View>(R.id.carga_mundos)?.visibility = View.GONE
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            viewModelWorlds.setWorlds()
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
