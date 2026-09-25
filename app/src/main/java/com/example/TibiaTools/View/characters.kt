package com.example.TibiaTools.View

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TibiaTools.View.ViewModel.ViewModelCharacters
import com.example.TibiaTools.data.model.Achievements
import com.example.TibiaTools.data.model.CharacterHouse
import com.example.TibiaTools.data.model.OtherCharacters
import com.example.TibiaTools.recyclerview.Adapters.AdapterArchievementsCharacter
import com.example.TibiaTools.recyclerview.Adapters.AdapterHouseCharacters
import com.example.TibiaTools.recyclerview.Adapters.AdapterOtherCharacters
import com.example.TibiaTools.recyclerview.ItemsArchievementsCharacter
import com.example.TibiaTools.recyclerview.ItemsCharacters
import com.example.TibiaTools.recyclerview.ItemsHousesCharacters
import com.example.TibiaTools.utilidades.ConvertidorFecha
import com.example.TibiaTools.utilidades.IsVisibillityCharacters
import com.example.ttools.R
import com.example.ttools.databinding.ActivityCharactersBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.util.Objects

class characters : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCharactersBinding
    private lateinit var nombrePersona: EditText
    private lateinit var nombre: TextView
    private lateinit var titulo: TextView
    private lateinit var sexo: TextView
    private lateinit var vocacion: TextView
    private lateinit var nivel: TextView
    private lateinit var archiviement: TextView
    private lateinit var mundo: TextView
    private lateinit var residencia: TextView
    private lateinit var guild: TextView
    private lateinit var lastlogin: TextView
    private lateinit var comentario: TextView
    private lateinit var textViewPremium: TextView
    private lateinit var textViewMirried: TextView
    private lateinit var textViewLoyalty: TextView
    private lateinit var textViewCreated: TextView
    private lateinit var btnenviar: Button
    private val convertidorFecha = ConvertidorFecha()
    private lateinit var linearLayoutDeaths: LinearLayout
    private lateinit var linearLayoutHouses: RecyclerView
    private lateinit var linearLayoutOtherCharacters: RecyclerView
    private lateinit var linearLayoutAchievements: RecyclerView
    private var adapterHouseCharacters: AdapterHouseCharacters? = null
    private var itemsHousesCharacters: ArrayList<ItemsHousesCharacters>? = null
    private var adapterOtherCharacters: AdapterOtherCharacters? = null
    private var itemsCharacters: ArrayList<ItemsCharacters>? = null
    private var adapterArchievementsCharacter: AdapterArchievementsCharacter? = null
    private var itemsArchievementsCharacters: ArrayList<ItemsArchievementsCharacter>? = null
    private var isVisibillityCharacters: IsVisibillityCharacters? = null
    private val viewModelCharacters: ViewModelCharacters by inject()
    private lateinit var viewRoot: View

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        viewRoot = findViewById(android.R.id.content)

        nombrePersona = findViewById(R.id.editTextTextPersonName)
        nombre = findViewById(R.id.nombre)
        titulo = findViewById(R.id.titulo)
        sexo = findViewById(R.id.sexo)
        vocacion = findViewById(R.id.vocacion)
        nivel = findViewById(R.id.nivel)
        archiviement = findViewById(R.id.achivement)
        mundo = findViewById(R.id.mundo)
        residencia = findViewById(R.id.residencia)
        guild = findViewById(R.id.guild)
        lastlogin = findViewById(R.id.last_loguin)
        comentario = findViewById(R.id.comentario)
        textViewPremium = findViewById(R.id.textViewPremium)
        textViewMirried = findViewById(R.id.textViewMirried)
        textViewLoyalty = findViewById(R.id.textViewLoyalty)
        textViewCreated = findViewById(R.id.textViewCreated)
        btnenviar = findViewById(R.id.btnenviar)
        btnenviar.setOnClickListener(this)

        linearLayoutHouses = findViewById(R.id.linearLayoutHouses)
        linearLayoutOtherCharacters = findViewById(R.id.linearLayoutOtherCharacters)
        linearLayoutAchievements = findViewById(R.id.linearLayoutAchievements)

        viewModelCharacters.characters().observe(this) { apiResponse ->
            isVisibillityCharacters = IsVisibillityCharacters()
            linearLayoutDeaths = findViewById(R.id.linearLayoutDeaths)
            linearLayoutOtherCharacters.removeAllViews()
            linearLayoutDeaths.removeAllViews()
            linearLayoutHouses.removeAllViews()
            textViewMirried.text = ""
            textViewLoyalty.text = ""
            textViewCreated.text = ""
            isVisibillityCharacters?.setVisibility(false)
            isVisibillityCharacters?.VisibilityDataGeneral(binding)
            binding.root.findViewById<View>(R.id.carga_characters)?.visibility = View.VISIBLE
            binding.root.findViewById<View>(R.id.CardStatus)?.visibility = View.GONE
            linearLayoutDeaths.visibility = View.GONE
            linearLayoutHouses.visibility = View.GONE
            linearLayoutAchievements.visibility = View.GONE
            linearLayoutOtherCharacters.visibility = View.GONE

            if (apiResponse != null) {
                val code = apiResponse.information?.status?.http_code ?: 0
                if (code == 502) {
                    val messageError = apiResponse.information?.status?.message ?: "Error"
                    Snackbar.make(viewRoot, messageError, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                } else {
                    val charData = apiResponse.character?.character
                    if (charData != null) {
                        nombre.text = charData.name
                        nombre.visibility = View.VISIBLE
                        titulo.text = charData.title
                        sexo.text = charData.sex
                        vocacion.text = charData.vocation
                        nivel.text = charData.level.toString()
                        archiviement.text = charData.achievement_points.toString()
                        mundo.text = charData.world
                        residencia.text = charData.residence

                        val guildRank = charData.guild?.rank
                        val nameGuild = charData.guild?.name

                        if (guildRank != null && nameGuild != null) {
                            guild.text = "$guildRank of the $nameGuild"
                        } else {
                            guild.text = "No pertenece a una guild"
                        }

                        convertidorFecha.expiryDateString = charData.last_login
                        convertidorFecha.convertirFecha()
                        lastlogin.text = convertidorFecha.fechaConvertida
                        comentario.text = charData.comment
                        textViewPremium.text = charData.account_status

                        isVisibillityCharacters?.setVisibility(true)
                        isVisibillityCharacters?.VisibilityDataGeneral(binding)
                        binding.root.findViewById<View>(R.id.CardStatus)?.visibility = View.VISIBLE

                        if (charData.married_to != null) {
                            textViewMirried.text = "💍🔥: " + charData.married_to
                        }
                        if (charData.houses != null) {
                            recyclerHouse(charData.houses)
                        }
                    }

                    val deathsList = apiResponse.character?.deaths
                    if (!deathsList.isNullOrEmpty()) {
                        deathsList.forEach { dead ->
                            val textViewWeakness = TextView(this).apply {
                                convertidorFecha.expiryDateString = dead.time
                                convertidorFecha.convertirFecha()
                                text = "☠️️ " + convertidorFecha.fechaConvertida + " - " + dead.reason
                                setTextColor(Color.parseColor("#CE93D8"))
                                textSize = 15f
                                setTypeface(null, Typeface.ITALIC)
                                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            }
                            linearLayoutDeaths.addView(textViewWeakness)
                            linearLayoutDeaths.visibility = View.VISIBLE
                        }
                    }

                    val otherChars = apiResponse.character?.other_characters
                    if (!otherChars.isNullOrEmpty()) {
                        recyclerCharacters(otherChars)
                    }

                    val achs = apiResponse.character?.achievements
                    if (!achs.isNullOrEmpty()) {
                        recyclerArchievements(achs)
                    }

                    val accInfo = apiResponse.character?.account_information
                    if (accInfo != null) {
                        if (accInfo.loyalty_title != null) {
                            textViewLoyalty.text = "Loyalty Title: " + accInfo.loyalty_title
                        }
                        if (accInfo.created != null) {
                            convertidorFecha.expiryDateString = accInfo.created
                            convertidorFecha.convertirFecha()
                            textViewCreated.text = "Created: " + convertidorFecha.fechaConvertida
                        }
                    }
                }
            } else {
                Snackbar.make(viewRoot, "Error to conection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                binding.root.findViewById<View>(R.id.carga_characters)?.visibility = View.GONE
            }
            binding.root.findViewById<View>(R.id.carga_characters)?.visibility = View.GONE
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

    override fun onClick(v: View) {
        if (v.id == R.id.btnenviar) {
            if (nombrePersona.text.toString() == "") {
                Snackbar.make(v, "Ingrese el nombre del personaje", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                binding.root.findViewById<View>(R.id.carga_characters)?.visibility = View.VISIBLE
                viewModelCharacters.setCharacters(nombrePersona.text.toString())
            }
        }
    }

    fun recyclerHouse(houses: ArrayList<CharacterHouse>) {
        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        itemsHousesCharacters = ArrayList()
        for (i in houses.indices) {
            itemsHousesCharacters?.add(
                ItemsHousesCharacters(
                    houses[i].name,
                    houses[i].town,
                    houses[i].paid,
                    houses[i].houseid
                )
            )
        }
        linearLayoutHouses.setHasFixedSize(true)
        linearLayoutHouses.layoutManager = linearLayoutManager
        adapterHouseCharacters = AdapterHouseCharacters(itemsHousesCharacters!!)
        linearLayoutHouses.adapter = adapterHouseCharacters
        linearLayoutHouses.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun recyclerCharacters(otherCharacters: ArrayList<OtherCharacters>) {
        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        itemsCharacters = ArrayList()
        otherCharacters.forEach { charItem ->
            itemsCharacters?.add(
                ItemsCharacters(
                    charItem.name,
                    charItem.world,
                    charItem.status,
                    charItem.deleted,
                    charItem.main,
                    charItem.traded
                )
            )
        }
        linearLayoutOtherCharacters.setHasFixedSize(true)
        linearLayoutOtherCharacters.layoutManager = linearLayoutManager
        adapterOtherCharacters = AdapterOtherCharacters(itemsCharacters!!)
        linearLayoutOtherCharacters.adapter = adapterOtherCharacters
        linearLayoutOtherCharacters.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun recyclerArchievements(achievements: ArrayList<Achievements>) {
        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        itemsArchievementsCharacters = ArrayList()
        achievements.forEach { achievement ->
            itemsArchievementsCharacters?.add(
                ItemsArchievementsCharacter(
                    achievement.name,
                    achievement.grade,
                    achievement.secret
                )
            )
        }
        linearLayoutAchievements.setHasFixedSize(true)
        linearLayoutAchievements.layoutManager = linearLayoutManager
        adapterArchievementsCharacter = AdapterArchievementsCharacter(itemsArchievementsCharacters!!)
        linearLayoutAchievements.adapter = adapterArchievementsCharacter
        linearLayoutAchievements.visibility = View.VISIBLE
    }
}
