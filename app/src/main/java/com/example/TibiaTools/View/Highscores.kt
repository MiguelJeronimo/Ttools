package com.example.TibiaTools.View

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
import com.example.TibiaTools.View.ViewModel.ViewModelHighScore
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewHighScores
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewHighScores
import com.example.TibiaTools.utilidades.DataHighScores
import com.example.TibiaTools.utilidades.Spinners
import com.example.ttools.R
import com.example.ttools.databinding.ActivityHighscoresBinding
import java.text.DecimalFormat
import java.util.ArrayList
import java.util.concurrent.Executors

class Highscores : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityHighscoresBinding
    private val dataHighScores = DataHighScores()
    private lateinit var spinnerWorlds: AutoCompleteTextView
    private lateinit var spinnerVocations: AutoCompleteTextView
    private lateinit var spinnerCategorys: AutoCompleteTextView
    private var adapterWorlds: ArrayAdapter<String>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptador: AdapterRecyclerViewHighScores
    private lateinit var listaHighscore: MutableList<ItemsRecyclerViewHighScores>
    private var mundo: String? = null
    private var categoria: String? = null
    private var vocacion: String? = null
    private lateinit var viewModelHighScore: ViewModelHighScore
    private lateinit var viewModelProvider: ViewModelProvider

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighscoresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModelProvider = ViewModelProvider(this)
        viewModelHighScore = viewModelProvider[ViewModelHighScore::class.java]
        binding.root.findViewById<View>(R.id.carga_highscores)?.visibility = View.VISIBLE

        spinnerWorlds = findViewById(R.id.spinner_worldss)
        spinnerVocations = findViewById(R.id.spinner_vocationss)
        spinnerCategorys = findViewById(R.id.spinner_category)

        hilos()
        spinnerWorlds.onItemClickListener = this
        spinnerVocations.onItemClickListener = this
        spinnerCategorys.onItemClickListener = this

        recyclerView = findViewById(R.id.recycler_highscores)
        listaHighscore = ArrayList()
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        adaptador = AdapterRecyclerViewHighScores(listaHighscore)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adaptador

        viewModelHighScore.Worlds().observe(this) { worlds ->
            if (worlds != null) {
                adapterWorlds = ArrayAdapter(applicationContext, R.layout.auto_complete, worlds)
                spinnerWorlds.setAdapter(adapterWorlds)
                binding.root.findViewById<View>(R.id.carga_highscores)?.visibility = View.GONE
            } else {
                binding.root.findViewById<View>(R.id.carga_highscores)?.visibility = View.GONE
            }
        }

        viewModelHighScore.highScoreList().observe(this) { highScores ->
            listaHighscore.clear()
            val decimalFormat = DecimalFormat("#,###.###").apply {
                groupingSize = 3
            }
            if (highScores != null) {
                highScores.highscore_list.forEach { highscoreList ->
                    val categorias = highScores.category
                    val value = when (categorias) {
                        "achievements" -> "Archivements: ${decimalFormat.format(highscoreList.value)}"
                        "axefighting" -> "Axe Fighting : ${decimalFormat.format(highscoreList.value)}"
                        "charmpoints" -> "Charm Points: ${decimalFormat.format(highscoreList.value)}"
                        "clubfighting" -> "Club Fighting: ${decimalFormat.format(highscoreList.value)}"
                        "distancefighting" -> "Distance Fighting: ${decimalFormat.format(highscoreList.value)}"
                        "experience" -> "Experience: ${decimalFormat.format(highscoreList.value)}"
                        "fishing" -> "Fishing: ${decimalFormat.format(highscoreList.value)}"
                        "fistfighting" -> "Fist Fighting: ${decimalFormat.format(highscoreList.value)}"
                        "goshnarstaint" -> "Goshnar's Taint: ${decimalFormat.format(highscoreList.value)}"
                        "loyaltypoints" -> "Loyalty Points: ${decimalFormat.format(highscoreList.value)}"
                        "magiclevel" -> "Magic Level: ${decimalFormat.format(highscoreList.value)}"
                        "shielding" -> "Shielding: ${decimalFormat.format(highscoreList.value)}"
                        "swordfighting" -> "Sword Fighting: ${decimalFormat.format(highscoreList.value)}"
                        "dromescore" -> "Drome Score: ${decimalFormat.format(highscoreList.value)}"
                        "bosspoints" -> "Boss Points: ${decimalFormat.format(highscoreList.value)}"
                        else -> "${decimalFormat.format(highscoreList.value)}"
                    }

                    listaHighscore.add(
                        ItemsRecyclerViewHighScores(
                            highscoreList.rank.toString(),
                            highscoreList.name,
                            highscoreList.vocation,
                            highscoreList.world,
                            highscoreList.level.toString(),
                            value,
                            highscoreList.title
                        )
                    )
                }
            } else {
                Toast.makeText(applicationContext, "No hay respuesta del servidor", Toast.LENGTH_SHORT).show()
            }
            binding.root.findViewById<View>(R.id.carga_highscores)?.visibility = View.GONE
            adaptador.notifyDataSetChanged()
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

    private fun spinners() {
        val spinners = Spinners()
        val dataVocation = spinners.spinnerVocations(resources.openRawResource(R.raw.vocations))
        val dataCategorys = spinners.spinnerCategory(resources.openRawResource(R.raw.categorias))
        val adapterVocations = ArrayAdapter(applicationContext, R.layout.auto_complete, dataVocation)
        val adapterCategorys = ArrayAdapter(applicationContext, R.layout.auto_complete, dataCategorys)
        Handler(Looper.getMainLooper()).post {
            spinnerVocations.setAdapter(adapterVocations)
            spinnerCategorys.setAdapter(adapterCategorys)
        }
    }

    private fun hilos() {
        val executor = Executors.newFixedThreadPool(2)
        executor.execute { spinners() }
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val worldText = spinnerWorlds.text.toString()
        val categoryText = spinnerCategorys.text.toString()
        val vocationText = spinnerVocations.text.toString()

        if (worldText.isNotEmpty() && !worldText.equals("Seleccione un mundo", ignoreCase = true) &&
            categoryText.isNotEmpty() && !categoryText.equals("Seleccione una categoria", ignoreCase = true) &&
            vocationText.isNotEmpty() && !vocationText.equals("Seleccione una vocación", ignoreCase = true)
        ) {
            binding.root.findViewById<View>(R.id.carga_highscores)?.visibility = View.VISIBLE
            mundo = worldText
            dataHighScores.mundo = mundo
            vocacion = vocationText
            dataHighScores.vocacion = vocacion
            categoria = categoryText
            dataHighScores.categoria = categoria
            viewModelHighScore.setHighScores(
                dataHighScores.mundo,
                dataHighScores.categoria.replace(" ", "").lowercase(),
                dataHighScores.vocacion
            )
        } else {
            listaHighscore.clear()
            adaptador.notifyDataSetChanged()
        }
    }
}
