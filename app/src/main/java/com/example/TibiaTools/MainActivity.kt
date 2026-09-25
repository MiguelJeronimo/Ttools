package com.example.TibiaTools

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.About
import com.example.TibiaTools.View.Blessings
import com.example.TibiaTools.View.Criaturas
import com.example.TibiaTools.View.GuildInformation
import com.example.TibiaTools.View.Highscores
import com.example.TibiaTools.View.HouseActivity
import com.example.TibiaTools.View.Mundos
import com.example.TibiaTools.View.Spells_Tibia
import com.example.TibiaTools.View.Stamina
import com.example.TibiaTools.View.TibiaMaps
import com.example.TibiaTools.View.ViewModel.ViewModelHome
import com.example.TibiaTools.View.characters
import com.example.TibiaTools.View.experiencia_compartida
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewNews
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewNews
import com.example.TibiaTools.utilidades.RedValidator
import com.example.ttools.R
import com.github.AAChartModel.AAChartCore.AAChartCreator.AAChartModel
import com.github.AAChartModel.AAChartCore.AAChartCreator.AAChartView
import com.github.AAChartModel.AAChartCore.AAChartCreator.AASeriesElement
import com.github.AAChartModel.AAChartCore.AAChartEnum.AAChartType
import com.github.AAChartModel.AAChartCore.AAOptionsModel.AAStyle
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.progressindicator.LinearProgressIndicator
import org.koin.android.ext.android.inject
import java.util.ArrayList
import java.util.Collections
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

class MainActivity : AppCompatActivity(), View.OnClickListener, MenuItem.OnMenuItemClickListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    private lateinit var imgRashid: ImageView
    private lateinit var imgBossCreature: ImageView
    private lateinit var imgBossBosstable: ImageView
    private lateinit var lbRashid: TextView
    private lateinit var textBossCreature: TextView
    private lateinit var lbBossBosstable: TextView

    private lateinit var textViewFecha: TextView
    private lateinit var textViewCategoria: TextView
    private lateinit var textViewNoticia: TextView
    private lateinit var textViewTipo: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewCategory: TextView
    private lateinit var textViewNew: TextView
    private lateinit var textViewtype: TextView
    private lateinit var textViewPlayesOnline: TextView

    private lateinit var recyclerViewNoticas: RecyclerView
    private lateinit var adapterRecyclerViewNews: AdapterRecyclerViewNews
    private lateinit var linearProgressIndicator: LinearProgressIndicator
    private lateinit var itemsRecyclerViewNewsList: MutableList<ItemsRecyclerViewNews>

    private lateinit var cardCreatureBooss: MaterialCardView
    private lateinit var cardBoostedBoos: MaterialCardView
    private lateinit var cardNews: MaterialCardView
    private lateinit var cardNews2: MaterialCardView

    private val viewModelHome: ViewModelHome by inject()
    private lateinit var aaChartView: AAChartView
    private lateinit var aaChartView2: AAChartView
    private val data = ArrayList<Double>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        cardNews = findViewById(R.id.cardView4)
        cardNews2 = findViewById(R.id.cardView5)
        drawerLayout = findViewById(R.id.navegacion)
        cardCreatureBooss = findViewById(R.id.cardCreatureBoos)
        cardBoostedBoos = findViewById(R.id.cardBoostedBoos)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        linearProgressIndicator = findViewById(R.id.carga_main)
        navigationView = findViewById(R.id.barraNavegacion)

        val menu = navigationView.menu
        menu.findItem(R.id.nd_character)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_criaturas)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_stamina)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_experiencia_compartida)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_mundos)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_guilds)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_spells)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_bless)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_highscores)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_houses)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.id_about)?.setOnMenuItemClickListener(this)
        menu.findItem(R.id.nd_maps)?.setOnMenuItemClickListener(this)

        imgRashid = findViewById(R.id.imageViewRashid)
        lbRashid = findViewById(R.id.textViewRashid)
        imgBossCreature = findViewById(R.id.BossCreature)
        textBossCreature = findViewById(R.id.textBossCreature)
        imgBossBosstable = findViewById(R.id.BossBosstable)
        lbBossBosstable = findViewById(R.id.lbBossBosstable)

        textViewFecha = findViewById(R.id.textViewFecha)
        textViewCategoria = findViewById(R.id.textViewCategoria)
        textViewNoticia = findViewById(R.id.textViewNoticia)
        textViewTipo = findViewById(R.id.textViewTipo)
        textViewDate = findViewById(R.id.textViewDate)
        textViewCategory = findViewById(R.id.textViewCategory)
        textViewNew = findViewById(R.id.textViewNew)
        textViewtype = findViewById(R.id.textViewtype)
        textViewPlayesOnline = findViewById(R.id.players_online_main)

        val timer = Timer()
        recyclerViewNoticas = findViewById(R.id.recyclerNews)
        itemsRecyclerViewNewsList = ArrayList()
        val layoutManager = LinearLayoutManager(this)
        recyclerViewNoticas.layoutManager = layoutManager
        adapterRecyclerViewNews = AdapterRecyclerViewNews(itemsRecyclerViewNewsList)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerViewNoticas.setHasFixedSize(true)
        recyclerViewNoticas.adapter = adapterRecyclerViewNews

        aaChartView = findViewById(R.id.AAChartView)
        aaChartView2 = findViewById(R.id.AAChartView2)
        val aaChartModel = AAChartModel()
        val aaChartModel2 = AAChartModel()

        viewModelHome.rashidLocation.observe(this) { location ->
            if (location != null) {
                val urlRashidImage = "https://raw.githubusercontent.com/MiguelJeronimo/TtoolsDesktop/main/src/img/rashid.gif"
                lbRashid.text = location
                Glide.with(applicationContext).load(urlRashidImage).into(imgRashid)
                imgRashid.visibility = View.VISIBLE
                lbRashid.visibility = View.VISIBLE
            } else {
                imgRashid.visibility = View.GONE
                lbRashid.visibility = View.GONE
            }
        }

        val dataElement = ArrayList<AASeriesElement>()
        val categories = ArrayList<String>()
        val data2 = ArrayList<Double>()
        val isDrawChart2 = AtomicBoolean(false)

        viewModelHome.worlds().observe(this) { worlds ->
            if (worlds != null) {
                categories.clear()
                dataElement.clear()
                data2.clear()
                val worldsList = ArrayList<RegularWorlds>(worlds.regular_worlds)
                worldsList.sortWith(Collections.reverseOrder())
                for (world in worldsList) {
                    categories.add(world.name ?: "")
                    data2.add(world.players_online.toDouble())
                }
                dataElement.add(
                    AASeriesElement()
                        .name("Onlines")
                        .data(data2.toTypedArray())
                )
                val serieelement = dataElement.toTypedArray()
                val categoriesArray = categories.toTypedArray()
                if (isDrawChart2.get()) {
                    aaChartView2.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(serieelement)
                } else {
                    aaChartModel2.chartType(AAChartType.Bar)
                        .animationType("Bounce")
                        .title("Worlds").titleStyle(AAStyle().color("#DCE4E9"))
                        .subtitle("Players online to worlds in Tibia").subtitleStyle(AAStyle().color("#70787D"))
                        .backgroundColor("#191C1E")
                        .dataLabelsEnabled(false)
                        .categories(categoriesArray)
                        .yAxisGridLineWidth(0f)
                        .colorsTheme(arrayOf("#67D3FF"))
                        .series(dataElement.toTypedArray())
                    aaChartView2.aa_drawChartWithChartModel(aaChartModel2)
                    aaChartView2.visibility = View.VISIBLE
                    isDrawChart2.set(true)
                }
            }
        }

        val isDrawChart = AtomicBoolean(false)
        viewModelHome.playersOnline.observe(this) { playersOnline ->
            if (playersOnline != null) {
                val onlines = "Players Online: ${playersOnline.players_online}"
                textViewPlayesOnline.visibility = View.VISIBLE
                textViewPlayesOnline.text = onlines
                val players = playersOnline.players_online.toDouble()
                if (data.isNotEmpty()) {
                    val position = data.size - 1
                    if (data[position] != players) {
                        data.add(players)
                    }
                } else {
                    data.add(players)
                }
                aaChartModel.chartType(AAChartType.Spline)
                    .animationType("Bounce")
                    .title("PLAYERS ONLINE").titleStyle(AAStyle().color("#DCE4E9"))
                    .subtitle("Players online in Tibia").subtitleStyle(AAStyle().color("#70787D"))
                    .backgroundColor("#191C1E")
                    .dataLabelsEnabled(false)
                    .yAxisGridLineWidth(0f)

                if (isDrawChart.get()) {
                    val dataElementChart = arrayOf(
                        AASeriesElement()
                            .name("Onlines")
                            .data(data.toTypedArray())
                    )
                    aaChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(dataElementChart)
                } else {
                    aaChartModel.series(
                        arrayOf(
                            AASeriesElement()
                                .name("Onlines")
                                .data(data.toTypedArray())
                        )
                    )
                    aaChartView.aa_drawChartWithChartModel(aaChartModel)
                    aaChartView.visibility = View.VISIBLE
                    isDrawChart.set(true)
                }
            } else {
                textViewPlayesOnline.visibility = View.GONE
                textViewPlayesOnline.text = ""
            }
        }

        viewModelHome.creatureBoss.observe(this) { creatureBoss ->
            if (creatureBoss != null) {
                Glide.with(applicationContext).load(creatureBoss.boosted?.image_url).into(imgBossCreature)
                textBossCreature.text = creatureBoss.boosted?.name
                cardCreatureBooss.visibility = View.VISIBLE
            } else {
                linearProgressIndicator.visibility = View.GONE
                cardCreatureBooss.visibility = View.GONE
            }
        }

        viewModelHome.boostedBoss.observe(this) { bostedBoss ->
            if (bostedBoss != null) {
                val boosted = bostedBoss.boosted
                Glide.with(applicationContext).load(boosted?.image_url).into(imgBossBosstable)
                lbBossBosstable.text = boosted?.name
                cardBoostedBoos.visibility = View.VISIBLE
            } else {
                cardBoostedBoos.visibility = View.GONE
            }
        }

        viewModelHome.news.observe(this) { news ->
            if (news != null) {
                val sizeNews = news.news.size
                val rangeNews = Math.min(sizeNews, 2)
                if (sizeNews > 0) {
                    for (i in 0 until rangeNews) {
                        textViewFecha.text = news.news[i].date
                        textViewCategoria.text = news.news[i].category
                        textViewNoticia.text = news.news[i].news
                        textViewTipo.text = news.news[i].type
                        // Segundo Card
                        textViewDate.text = news.news[i + 1].date
                        textViewCategory.text = news.news[i + 1].category
                        textViewNew.text = news.news[i + 1].news
                        textViewtype.text = news.news[i + 1].type
                        cardNews.visibility = View.VISIBLE
                        cardNews2.visibility = View.VISIBLE
                    }
                }
            } else {
                cardNews.visibility = View.GONE
                cardNews2.visibility = View.GONE
            }
        }

        viewModelHome.newTicker.observe(this) { newTicker ->
            if (newTicker != null) {
                newTicker.news.forEach { news ->
                    itemsRecyclerViewNewsList.add(
                        ItemsRecyclerViewNews(
                            news.id,
                            news.date,
                            news.news,
                            news.category,
                            news.type,
                            news.url
                        )
                    )
                }
                adapterRecyclerViewNews.notifyDataSetChanged()
                linearProgressIndicator.visibility = View.GONE
            } else {
                linearProgressIndicator.visibility = View.GONE
            }
        }

        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread { viewModelHome.setPlayersOnline() }
            }
        }, 0, 3000)

        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread { viewModelHome.setWorlds() }
            }
        }, 0, 3000)

        if (RedValidator.ValidarInternet(application)) {
            threads()
        } else {
            Toast.makeText(this, "Revisa tu conexion a internet :)", Toast.LENGTH_SHORT).show()
            linearProgressIndicator.visibility = View.GONE
        }
    }

    private fun threads() {
        val executor = Executors.newFixedThreadPool(5)
        executor.execute {
            try {
                Thread.sleep(3000)
                viewModelHome.setCreatureBoss()
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
        }
        executor.execute {
            try {
                Thread.sleep(3000)
                viewModelHome.setBostedBoss()
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
        }
        executor.execute {
            try {
                Thread.sleep(3000)
                viewModelHome.setNews()
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
        }
        executor.execute {
            try {
                Thread.sleep(3000)
                viewModelHome.setNewTicker()
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
        }
        executor.execute {
            try {
                Thread.sleep(3000)
                viewModelHome.setRashirLocation()
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nd_about) {
            val about = Intent(this, About::class.java)
            startActivity(about)
        } else if (id == R.id.nd_salir) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {}

    override fun onMenuItemClick(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nd_experiencia_compartida -> {
                startActivity(Intent(this, experiencia_compartida::class.java))
                drawerLayout.close()
            }
            R.id.nd_character -> {
                startActivity(Intent(this, characters::class.java))
                drawerLayout.close()
            }
            R.id.nd_stamina -> {
                startActivity(Intent(this, Stamina::class.java))
                drawerLayout.close()
            }
            R.id.nd_mundos -> {
                startActivity(Intent(this, Mundos::class.java))
                drawerLayout.close()
            }
            R.id.nd_criaturas -> {
                startActivity(Intent(this, Criaturas::class.java))
                drawerLayout.close()
            }
            R.id.nd_guilds -> {
                startActivity(Intent(this, GuildInformation::class.java))
                drawerLayout.close()
            }
            R.id.nd_spells -> {
                startActivity(Intent(this, Spells_Tibia::class.java))
                drawerLayout.close()
            }
            R.id.nd_bless -> {
                startActivity(Intent(this, Blessings::class.java))
                drawerLayout.close()
            }
            R.id.nd_highscores -> {
                startActivity(Intent(this, Highscores::class.java))
                drawerLayout.close()
            }
            R.id.nd_houses -> {
                startActivity(Intent(this, HouseActivity::class.java))
                drawerLayout.close()
            }
            R.id.id_about -> {
                startActivity(Intent(this, About::class.java))
                drawerLayout.close()
            }
            R.id.nd_maps -> {
                startActivity(Intent(this, TibiaMaps::class.java))
                drawerLayout.close()
            }
        }
        return false
    }
}
