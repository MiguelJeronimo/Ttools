package com.example.TibiaTools.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelCreatures
import com.example.TibiaTools.recyclerview.Adapters.adapterRecyclerViewCriatures
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewCriatures
import com.example.ttools.R
import com.example.ttools.databinding.ActivityCriaturasBinding
import java.util.ArrayList
import java.util.Objects

class Criaturas : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: adapterRecyclerViewCriatures
    private val itemsRecyclerViewCriatures = ArrayList<ItemsRecyclerViewCriatures>()
    private lateinit var binding: ActivityCriaturasBinding
    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var viewModelCreatures: ViewModelCreatures

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriaturasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerCriaturas)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        myAdapter = adapterRecyclerViewCriatures(itemsRecyclerViewCriatures)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = myAdapter

        viewModelProvider = ViewModelProvider(this)
        viewModelCreatures = viewModelProvider[ViewModelCreatures::class.java]

        viewModelCreatures.creature().observe(this) { creatures ->
            if (creatures != null) {
                itemsRecyclerViewCriatures.add(
                    ItemsRecyclerViewCriatures(
                        "Today's Boss: " + (creatures.boosted?.name ?: ""),
                        creatures.boosted?.race ?: "",
                        creatures.boosted?.image_url ?: ""
                    )
                )
                creatures.creature_list.forEach { creature ->
                    itemsRecyclerViewCriatures.add(
                        ItemsRecyclerViewCriatures(
                            creature.name,
                            creature.race,
                            creature.image_url
                        )
                    )
                }
                myAdapter.notifyDataSetChanged()
                recyclerView.visibility = View.VISIBLE
            } else {
                Toast.makeText(applicationContext, "Error al obtener las criaturas, intente mas tarde", Toast.LENGTH_SHORT).show()
            }
            binding.root.findViewById<View>(R.id.carga_criatures)?.visibility = View.GONE
        }

        myAdapter.setOnClickListener { view ->
            val position = recyclerView.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION) {
                val raceCreatures = itemsRecyclerViewCriatures[position].lbrace
                val nameCreatures = itemsRecyclerViewCriatures[position].lbName
                val intent = Intent(this@Criaturas, CriaturesInformation::class.java).apply {
                    putExtra("raceCreatures", raceCreatures)
                    putExtra("nameCreatures", nameCreatures)
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
