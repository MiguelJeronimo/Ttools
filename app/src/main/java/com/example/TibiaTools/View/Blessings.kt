package com.example.TibiaTools.View

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.TibiaTools.operations.BlessingCalculator
import com.example.TibiaTools.utils.GoldFormatter
import com.example.ttools.R
import com.example.ttools.databinding.ActivityBlessingsBinding
import com.google.android.material.materialswitch.MaterialSwitch

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
        val level = nivel.text.toString().trim().toIntOrNull()?.takeIf { it >= 1 }
        if (level == null) {
            Toast.makeText(this, "Favor de ingresar el nivel", Toast.LENGTH_SHORT).show()
            return
        }

        val costs = BlessingCalculator.calculate(
            level = level,
            includeHeartOfTheMountain = switchHeart.isChecked,
            includeBloodOfTheMountain = switchBlood.isChecked
        )

        val regular = GoldFormatter.format(costs.regularBlessing)
        spiritual.text = regular
        embrace.text = regular
        suns.text = regular
        solitude.text = regular
        phoenix.text = regular
        twitsOfFate.text = GoldFormatter.format(costs.twistOfFate)
        heart.text = if (switchHeart.isChecked) GoldFormatter.format(costs.heartOfTheMountain) else ""
        blood.text = if (switchBlood.isChecked) GoldFormatter.format(costs.bloodOfTheMountain) else ""
        total.text = GoldFormatter.format(costs.total)
    }
}
