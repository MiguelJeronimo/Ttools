package com.example.TibiaTools.View

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.TibiaTools.operations.StaminaCalculator
import com.example.ttools.R
import com.google.android.material.snackbar.Snackbar
import java.util.Objects

class Stamina : AppCompatActivity(), View.OnClickListener {
    private lateinit var tiempo: EditText
    private lateinit var lbHora: TextView
    private lateinit var lbMinutos: TextView
    private lateinit var lbmedio: TextView
    private lateinit var btnalcular: Button

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
            val current = StaminaCalculator.parse(tiempo.text.toString())
            if (current != null) {
                val offline = StaminaCalculator.timeToFullStamina(current)
                lbHora.text = offline.hours.toString()
                lbmedio.text = ":"
                lbMinutos.text = offline.minutes.toString().padStart(2, '0')
            } else {
                Snackbar.make(v, "El dato ingresado no es valido", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }
}
