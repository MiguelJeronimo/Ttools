package com.example.TibiaTools.View

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.TibiaTools.View.ViewModel.ViewModelSpell
import com.example.ttools.R
import com.example.ttools.databinding.ActivitySpellInformationBinding
import java.util.Objects

class SpellInformationActivity : AppCompatActivity() {
    private lateinit var intentObj: Intent
    private var idSpell: String? = null
    private lateinit var binding: ActivitySpellInformationBinding
    private lateinit var imgLogo: ImageView
    private lateinit var linearLayoutVocation: LinearLayout
    private lateinit var linearLayoutProfeciones: LinearLayout
    private lateinit var linearLayoutCitys: LinearLayout
    private lateinit var linearLayoutVocacionesPermitidas: LinearLayout
    private lateinit var textViewSpellName: TextView
    private lateinit var textViewSpellFormula: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var textViewGrupo: TextView
    private lateinit var textViewTipo: TextView
    private lateinit var textViewDamageType: TextView
    private lateinit var textViewCooldown: TextView
    private lateinit var textViewSoulPoint: TextView
    private lateinit var textViewAmount: TextView
    private lateinit var textViewMana: TextView
    private lateinit var textViewNivel: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var textViewStatus: TextView
    private lateinit var textViewCooldownGroup: TextView
    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var viewModelSpell: ViewModelSpell

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpellInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        intentObj = intent
        idSpell = intentObj.getStringExtra("ID")
        val name = intentObj.getStringExtra("name")
        supportActionBar?.title = name

        imgLogo = findViewById(R.id.imageViewSpellLogo)
        linearLayoutVocation = findViewById(R.id.linearLayoutVocation)
        linearLayoutProfeciones = findViewById(R.id.linearLayoutProfeciones)
        linearLayoutCitys = findViewById(R.id.linearLayoutCitys)
        linearLayoutVocacionesPermitidas = findViewById(R.id.linearLayoutVocacionesPermitidas)
        textViewSpellName = findViewById(R.id.textViewSpellName)
        textViewSpellFormula = findViewById(R.id.textViewSpellFormula)
        textViewDescription = findViewById(R.id.textViewDescription)
        textViewGrupo = findViewById(R.id.textViewGrupo)
        textViewTipo = findViewById(R.id.textViewTipo)
        textViewDamageType = findViewById(R.id.textViewDamageType)
        textViewCooldown = findViewById(R.id.textViewCooldown)
        textViewSoulPoint = findViewById(R.id.textViewSoulPoint)
        textViewAmount = findViewById(R.id.textViewAmount)
        textViewMana = findViewById(R.id.textViewMana)
        textViewNivel = findViewById(R.id.textViewNivel)
        textViewPrice = findViewById(R.id.textViewPrice)
        textViewStatus = findViewById(R.id.textViewStatus)
        textViewCooldownGroup = findViewById(R.id.textViewCooldownGroup)

        viewModelProvider = ViewModelProvider(this)
        viewModelSpell = viewModelProvider[ViewModelSpell::class.java]
        idSpell?.let { viewModelSpell.setSpell(it) }

        viewModelSpell.spell().observe(this) { spell ->
            if (spell != null) {
                linearLayoutCitys = findViewById(R.id.linearLayoutCitys)
                linearLayoutProfeciones = findViewById(R.id.linearLayoutProfeciones)
                linearLayoutVocation = findViewById(R.id.linearLayoutVocation)
                linearLayoutVocacionesPermitidas = findViewById(R.id.linearLayoutVocacionesPermitidas)

                Glide.with(applicationContext).load(spell.image_url).into(imgLogo)
                textViewSpellName.text = spell.name
                textViewDescription.text = spell.description

                val spellInformation = spell.spell_information
                if (spellInformation != null) {
                    textViewSpellFormula.text = spellInformation.formula
                    binding.root.findViewById<View>(R.id.CardSpellFormula)?.visibility = View.VISIBLE

                    var groupStatus: String? = null
                    if (spellInformation.isGroup_attack()) groupStatus = "Attack"
                    if (spellInformation.isGroup_healing()) groupStatus = "Healing"
                    if (spellInformation.isGroup_support()) groupStatus = "Support"
                    textViewGrupo.text = groupStatus

                    var statusType: String? = null
                    if (spellInformation.isType_instant()) statusType = "Instant"
                    if (spellInformation.isType_rune()) statusType = "Rune"
                    textViewTipo.text = statusType

                    val statusPremium = if (spellInformation.isPremium_only()) "Premium Only" else "Free"
                    textViewStatus.text = statusPremium
                    textViewDamageType.text = spellInformation.damage_type
                    textViewCooldown.text = spellInformation.cooldown_alone.toString()
                    textViewCooldownGroup.text = spellInformation.cooldown_group.toString()
                    textViewSoulPoint.text = spellInformation.soul_points.toString()
                    textViewAmount.text = spellInformation.amount.toString()
                    textViewNivel.text = spellInformation.level.toString()
                    textViewMana.text = spellInformation.mana.toString()

                    val vocationsList = spellInformation.vocation
                    if (vocationsList != null) {
                        val txtVocation = TextView(this).apply {
                            text = "Vocation"
                            setTextColor(ContextCompat.getColor(context, R.color.md_theme_light_primary))
                            textSize = 15f
                            setTypeface(null, Typeface.BOLD)
                            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            gravity = Gravity.CENTER
                        }
                        linearLayoutVocation.addView(txtVocation)
                        vocationsList.forEach { vocations ->
                            val tv = TextView(this).apply {
                                text = vocations
                                gravity = Gravity.CENTER
                                setTextColor(ContextCompat.getColor(context, R.color.md_theme_dark_error))
                                setTypeface(null, Typeface.ITALIC)
                                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            }
                            linearLayoutVocation.addView(tv)
                        }
                        binding.root.findViewById<View>(R.id.CardSpellGeneral)?.visibility = View.VISIBLE
                    }

                    val cityList = spellInformation.city
                    if (cityList != null) {
                        val txtCitys = TextView(this).apply {
                            text = "Citys"
                            setTextColor(ContextCompat.getColor(context, R.color.md_theme_light_primary))
                            textSize = 15f
                            setTypeface(null, Typeface.BOLD)
                            gravity = Gravity.CENTER
                            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        }
                        linearLayoutCitys.addView(txtCitys)
                        cityList.forEach { citys ->
                            val tv = TextView(this).apply {
                                text = citys
                                setTextColor(ContextCompat.getColor(context, R.color.md_theme_dark_error))
                                setTypeface(null, Typeface.ITALIC)
                                gravity = Gravity.CENTER
                                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            }
                            linearLayoutCitys.addView(tv)
                        }
                    }
                }

                val runeInformation = spell.rune_information
                if (runeInformation != null) {
                    val runeVocations = runeInformation.vocation
                    if (runeVocations != null) {
                        val txtVocacionesPermitidas = TextView(this).apply {
                            text = "Vocations permited"
                            setTextColor(ContextCompat.getColor(context, R.color.md_theme_light_primary))
                            textSize = 15f
                            setTypeface(null, Typeface.BOLD)
                            gravity = Gravity.CENTER
                            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        }
                        linearLayoutVocacionesPermitidas.addView(txtVocacionesPermitidas)
                        runeVocations.forEach { vocaciones_permitidas ->
                            val tv = TextView(this).apply {
                                text = vocaciones_permitidas
                                setTextColor(ContextCompat.getColor(context, R.color.md_theme_dark_error))
                                setTypeface(null, Typeface.ITALIC)
                                gravity = Gravity.CENTER
                                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            }
                            linearLayoutVocacionesPermitidas.addView(tv)
                        }
                    }
                }
                binding.root.findViewById<View>(R.id.Card_Listas)?.visibility = View.VISIBLE
            } else {
                Toast.makeText(applicationContext, "Error de conexión, intente mas tarde...", Toast.LENGTH_SHORT).show()
            }
            binding.root.findViewById<View>(R.id.carga_spell_information)?.visibility = View.GONE
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
