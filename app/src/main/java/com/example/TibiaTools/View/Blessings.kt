package com.example.TibiaTools.View

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.TibiaTools.Operaciones.calcularBlessings
import com.example.ttools.R
import com.example.ttools.databinding.ActivityBlessingsBinding
import com.google.android.material.materialswitch.MaterialSwitch
import java.text.DecimalFormat

class Blessings : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityBlessingsBinding
    private lateinit var spiritual: TextView
    private lateinit var embrace: TextView
    private lateinit var suns: TextView
    private lateinit var solitude: TextView
    private lateinit var phoenix: TextView
    private lateinit var twitsOfFate: TextView
    private lateinit var heart: TextView
    private lateinit var blood: TextView
    private lateinit var total: TextView
    private lateinit var nivel: EditText
    private lateinit var calcular: Button
    private lateinit var switchHeart: MaterialSwitch
    private lateinit var switchBlood: MaterialSwitch

    private val c = calcularBlessings()
    private val decimalFormat = DecimalFormat("###,###.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlessingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nivel = findViewById(R.id.Nivel)
        spiritual = findViewById(R.id.spiritual)
        embrace = findViewById(R.id.Embrace)
        suns = findViewById(R.id.Suns)
        solitude = findViewById(R.id.Solicitude)
        phoenix = findViewById(R.id.Phoenix)
        twitsOfFate = findViewById(R.id.Twits)
        heart = findViewById(R.id.Heart_mountain)
        blood = findViewById(R.id.Blood_moutain)
        total = findViewById(R.id.Total)

        calcular = findViewById(R.id.calcular)
        calcular.setOnClickListener(this)

        switchHeart = findViewById(R.id.switch1)
        switchHeart.setOnClickListener(this)

        switchBlood = findViewById(R.id.switch2)
        switchBlood.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.calcular) {
            val nivelPersonaje = nivel.text.toString()
            if (nivelPersonaje.equals("", ignoreCase = true)) {
                Toast.makeText(this, "Favor de ingresar el nivel", Toast.LENGTH_SHORT).show()
            } else {
                val nvl = nivelPersonaje.toInt()
                c.blessingIndividual(nvl)
                c.blessingTwistOfFate(nvl)
                c.sumaBlessingsPrincipales(nvl)
                val blessingIndividual = decimalFormat.format(c.getBlessingIndividual().toDouble())
                val blessingTwitsOfFate = decimalFormat.format(c.getblessingTwistOfFate().toDouble())
                spiritual.text = blessingIndividual
                embrace.text = blessingIndividual
                suns.text = blessingIndividual
                solitude.text = blessingIndividual
                phoenix.text = blessingIndividual
                twitsOfFate.text = blessingTwitsOfFate
            }
        }

        if (switchBlood.isChecked) {
            val nivelPersonaje = nivel.text.toString()
            if (nivelPersonaje.equals("", ignoreCase = true)) {
                Toast.makeText(this, "Favor de ingresar el nivel", Toast.LENGTH_SHORT).show()
            } else {
                val nvl = nivelPersonaje.toInt()
                c.blessingEspecial(nvl)
                val getEspecial = c.getBlessingEspecial()
                c.setHeartOfMountain(getEspecial)
                val blessingEspecial = decimalFormat.format(c.getBlessingEspecial().toDouble())
                blood.text = blessingEspecial
            }
        } else {
            blood.text = ""
            c.setHeartOfMountain(0)
        }

        if (switchHeart.isChecked) {
            val nivelPersonaje = nivel.text.toString()
            if (nivelPersonaje.equals("", ignoreCase = true)) {
                Toast.makeText(this, "Favor de ingresar el nivel", Toast.LENGTH_SHORT).show()
            } else {
                val nvl = nivelPersonaje.toInt()
                c.blessingEspecial(nvl)
                val getEspecial = c.getBlessingEspecial()
                c.setBloodOfMountain(getEspecial)
                val blessingEspecial = decimalFormat.format(c.getBlessingEspecial().toDouble())
                heart.text = blessingEspecial
            }
        } else {
            heart.text = ""
            c.setBloodOfMountain(0)
        }

        val totalBlessing = decimalFormat.format(
            (c.getSumaBlessingsPrincipales() + c.getblessingTwistOfFate() + c.getHeartOfMountain() + c.getBloodOfMountain()).toDouble()
        )
        total.text = totalBlessing
    }
}
