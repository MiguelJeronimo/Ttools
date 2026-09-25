package com.example.TibiaTools.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.ttools.R
import com.example.ttools.databinding.ActivityAboutBinding

class About : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val urlApp = "https://play-lh.googleusercontent.com/118SsYd1X2DyQRhNPYRRNjl96V4j1x_c-iGvOljJjO6nRhbtnTDrzW73SxQMVU5f-A=w240-h480-rw"
        val urlCoffe = "https://es.ewebinar.com/hubfs/ko-fi%20logo.png"
        val coffe = binding.root.findViewById<ImageButton>(R.id.imageButtonCoffe)
        val app = binding.root.findViewById<ImageButton>(R.id.imageButtonApp)

        Glide.with(applicationContext).load(urlCoffe).centerCrop().into(coffe)
        Glide.with(applicationContext).load(urlApp).centerCrop().into(app)

        app.setOnClickListener {
            val application = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.miguel.shoppinglistpro"))
            startActivity(application)
        }
        coffe.setOnClickListener {
            val coffeDonation = Intent(Intent.ACTION_VIEW, Uri.parse("https://ko-fi.com/selursan"))
            startActivity(coffeDonation)
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
