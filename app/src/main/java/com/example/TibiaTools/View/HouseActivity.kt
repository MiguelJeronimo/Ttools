package com.example.TibiaTools.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelHouses
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewHouses
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewHouses
import com.example.TibiaTools.utilidades.DataHighScores
import com.example.TibiaTools.utilidades.Spinners
import com.example.ttools.R
import com.example.ttools.databinding.ActivityHouseBinding
import java.util.ArrayList
import java.util.Objects
import java.util.concurrent.Executors

class HouseActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityHouseBinding
    private var mundo: String? = null
    private val dataHighScores = DataHighScores()
    private lateinit var spinnerWorlds: AutoCompleteTextView
    private lateinit var spinnerCitys: AutoCompleteTextView
    private var adapterWorlds: ArrayAdapter<String>? = null
    private var adapterCitys: ArrayAdapter<String>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterRecyclerViewHouses: AdapterRecyclerViewHouses
    private val listHouses = ArrayList<ItemsRecyclerViewHouses>()
    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var viewModelHouses: ViewModelHouses

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        recyclerView = findViewById(R.id.recycler_houses)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
        adapterRecyclerViewHouses = AdapterRecyclerViewHouses(listHouses)
        recyclerView.adapter = adapterRecyclerViewHouses

        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        binding.root.findViewById<View>(R.id.carga_houses)?.visibility = View.VISIBLE

        viewModelProvider = ViewModelProvider(this)
        viewModelHouses = viewModelProvider[ViewModelHouses::class.java]

        spinnerWorlds = findViewById(R.id.spinner_mundos)
        spinnerCitys = findViewById(R.id.spinner_citys)
        spinnerWorlds.onItemClickListener = this
        spinnerCitys.onItemClickListener = this

        threads()

        viewModelHouses.Worlds().observe(this) { worlds ->
            if (worlds != null) {
                adapterWorlds = ArrayAdapter(applicationContext, R.layout.auto_complete, worlds)
                spinnerWorlds.setAdapter(adapterWorlds)
                binding.root.findViewById<View>(R.id.carga_houses)?.visibility = View.GONE
            } else {
                Toast.makeText(applicationContext, "Fallo en la opcion mundos.. Intente mas tarde", Toast.LENGTH_SHORT).show()
                binding.root.findViewById<View>(R.id.carga_houses)?.visibility = View.GONE
            }
        }

        viewModelHouses.houses().observe(this) { houses ->
            listHouses.clear()
            if (houses != null) {
                mundo = houses.world
                houses.house_list.forEach { houseList ->
                    val rented = if (houseList.rented) "Ocupada" else "Desocupada"
                    listHouses.add(
                        ItemsRecyclerViewHouses(
                            houseList.name,
                            houseList.size.toString(),
                            houseList.rent.toString(),
                            rented,
                            houseList.house_id.toString()
                        )
                    )
                }
                houses.guildhall_list?.forEach { guildhallList ->
                    val rented = if (guildhallList.rented) "Ocupada" else "Desocupada"
                    listHouses.add(
                        ItemsRecyclerViewHouses(
                            guildhallList.name,
                            guildhallList.size.toString(),
                            guildhallList.rent.toString(),
                            rented,
                            guildhallList.house_id.toString()
                        )
                    )
                }
            }
            binding.root.findViewById<View>(R.id.carga_houses)?.visibility = View.GONE
            adapterRecyclerViewHouses.notifyDataSetChanged()
        }

        adapterRecyclerViewHouses.setOnClickListener { view ->
            val position = recyclerView.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION) {
                val idHouse = listHouses[position].houseId
                val name = listHouses[position].houseName
                val intent = Intent(this, HousesInformation::class.java).apply {
                    putExtra("ID", idHouse)
                    putExtra("mundo", mundo)
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

    private fun spinnersCity() {
        val spinners = Spinners()
        adapterCitys = ArrayAdapter(applicationContext, R.layout.auto_complete, spinners.LeerDataCitys(resources.openRawResource(R.raw.data)))
        Handler(Looper.getMainLooper()).post {
            spinnerCitys.setAdapter(adapterCitys)
        }
    }

    private fun threads() {
        val executor = Executors.newFixedThreadPool(1)
        executor.execute { spinnersCity() }
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val cityText = spinnerCitys.text.toString()
        val worldText = spinnerWorlds.text.toString()
        if (cityText.isNotEmpty() && worldText.isNotEmpty()) {
            if (!cityText.equals("Seleccione una ciudad", ignoreCase = true) && !worldText.equals("Seleccione", ignoreCase = true)) {
                mundo = worldText
                val ciudad = cityText
                dataHighScores.mundo = mundo
                dataHighScores.ciudad = ciudad
                viewModelHouses.setHouses(dataHighScores.mundo, dataHighScores.ciudad)
            } else {
                listHouses.clear()
                adapterRecyclerViewHouses.notifyDataSetChanged()
            }
        }
    }
}
