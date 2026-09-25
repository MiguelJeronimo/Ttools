package com.example.TibiaTools.View

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.TibiaTools.utilidades.RedValidator
import com.example.TibiaTools.utilidades.utilidades
import com.example.ttools.R
import com.example.ttools.databinding.ActivityTibiaMapsBinding

class TibiaMaps : AppCompatActivity() {
    private lateinit var binding: ActivityTibiaMapsBinding
    private val utilidadesObj = utilidades()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTibiaMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val maps = binding.root.findViewById<WebView>(R.id.mapsImage)
        if (RedValidator.ValidarInternet(application)) {
            maps.loadUrl("https://tibiamaps.github.io/tibia-map/#32371,32219,7:1")
            val webSettings: WebSettings = maps.settings
            webSettings.javaScriptEnabled = true
        } else {
            Toast.makeText(applicationContext, "No estas conectado a internet...", Toast.LENGTH_SHORT).show()
            maps.visibility = View.GONE
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
