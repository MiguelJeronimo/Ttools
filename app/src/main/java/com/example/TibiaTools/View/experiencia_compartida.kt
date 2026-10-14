package com.example.TibiaTools.View

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.TibiaTools.operations.SharedExperienceCalculator
import com.example.ttools.R
import java.util.Objects

class experiencia_compartida : AppCompatActivity(), View.OnClickListener {
    private lateinit var txtnivel: EditText
    private lateinit var calcular: Button
    private lateinit var rangoMenor: TextView
    private lateinit var rangoMayor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiencia_compartida)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        txtnivel = findViewById(R.id.Nivel)
        calcular = findViewById(R.id.calcular)
        calcular.setOnClickListener(this)
        rangoMenor = findViewById(R.id.Menor)
        rangoMayor = findViewById(R.id.Mayor)
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
            val level = txtnivel.text.toString().trim().toIntOrNull()?.takeIf { it >= 1 }
            if (level == null) {
                Toast.makeText(this, "Debe ingresar el nivel", Toast.LENGTH_SHORT).show()
                rangoMayor.text = "-"
                rangoMenor.text = "-"
            } else {
                val range = SharedExperienceCalculator.calculate(level)
                rangoMayor.text = range.maxLevel.toString()
                rangoMenor.text = range.minLevel.toString()
            }
        }
    }
}
