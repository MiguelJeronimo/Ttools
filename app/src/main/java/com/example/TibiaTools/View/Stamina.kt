package com.example.TibiaTools.View

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.TibiaTools.Operaciones.staminaTibia
import com.example.ttools.R
import com.google.android.material.snackbar.Snackbar
import java.util.Objects
import java.util.regex.Pattern

class Stamina : AppCompatActivity(), View.OnClickListener {
    private lateinit var tiempo: EditText
    private lateinit var lbHora: TextView
    private lateinit var lbMinutos: TextView
    private lateinit var lbmedio: TextView
    private lateinit var btnalcular: Button
    private val stamina = staminaTibia()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamina)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        tiempo = findViewById(R.id.Tiempo)
        btnalcular = findViewById(R.id.calcular)
        btnalcular.setOnClickListener(this)
        lbHora = findViewById(R.id.labelHora)
        lbmedio = findViewById(R.id.medio)
        lbMinutos = findViewById(R.id.lableMinutos)
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
        if (v.id == R.id.calcular) {
            val txtTiempo = tiempo.text.toString()
            val pattern = Pattern.compile("^([01]?[0-9]|2[0-9]|3[0-9]|4[0-2]):[0-5][0-9]$")
            val matcher = pattern.matcher(txtTiempo)
            if (matcher.matches()) {
                val particion = txtTiempo.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val horas = particion[0].toInt()
                val minutos = particion[1].toInt()
                if (horas > 0 && horas <= 42) {
                    stamina.convertirHoraMinutosStamina(horas, minutos)
                    val minutoStamina = stamina.getConvertirHoraMinutosStamina()
                    stamina.minutoStamina(minutoStamina)
                    val tiempoStamina = stamina.getminutoStamina()
                    stamina.convertirMinutosHora(tiempoStamina)
                    val horaReal = stamina.getHorasReales()
                    val minutosReales = stamina.getMinutosReales()
                    lbHora.text = horaReal.toString()
                    lbmedio.text = ":"
                    lbMinutos.text = minutosReales.toString()
                }
            } else {
                Snackbar.make(v, "El dato ingresado no es valido", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }
}
