package com.example.TibiaTools.View

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelHouseInformation
import com.example.TibiaTools.utilidades.RedValidator
import com.example.ttools.R
import com.example.ttools.databinding.ActivityHousesInformationBinding
import java.text.DecimalFormat

class HousesInformation : AppCompatActivity() {
    private lateinit var intentObj: Intent
    private var idHouse: String? = null
    private var mundo: String? = null
    private var name: String? = null
    private lateinit var txtName: TextView
    private lateinit var txtWorld: TextView
    private lateinit var txtCity: TextView
    private lateinit var txtType: TextView
    private lateinit var txtBeds: TextView
    private lateinit var txtSize: TextView
    private lateinit var txtPrice: TextView
    private lateinit var txtOwner: TextView
    private lateinit var imgCasa: ImageView
    private lateinit var binding: ActivityHousesInformationBinding
    private val decimalFormat = DecimalFormat("###,###.00")
    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var viewModelHouseInformation: ViewModelHouseInformation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHousesInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModelProvider = ViewModelProvider(this)
        viewModelHouseInformation = viewModelProvider[ViewModelHouseInformation::class.java]

        intentObj = intent
        idHouse = intentObj.getStringExtra("ID")
        mundo = intentObj.getStringExtra("mundo")
        name = intentObj.getStringExtra("name")
        supportActionBar?.title = name

        imgCasa = findViewById(R.id.imgCasa)
        txtName = findViewById(R.id.txtName)
        txtWorld = findViewById(R.id.txtWorld)
        txtCity = findViewById(R.id.txtCity)
        txtType = findViewById(R.id.txtType)
        txtBeds = findViewById(R.id.txtBeds)
        txtSize = findViewById(R.id.txtSize)
        txtPrice = findViewById(R.id.txtPrice)
        txtOwner = findViewById(R.id.txtOwner)

        if (RedValidator.ValidarInternet(application)) {
            if (mundo != null && idHouse != null) {
                viewModelHouseInformation.setHouse(mundo!!, idHouse!!)
            }
        } else {
            binding.root.findViewById<View>(R.id.cardHouseGeneral)?.visibility = View.GONE
            binding.root.findViewById<View>(R.id.textView19)?.visibility = View.GONE
            txtOwner.visibility = View.GONE
            binding.root.findViewById<View>(R.id.carga_house_information)?.visibility = View.GONE
            Toast.makeText(applicationContext, "No estas conectado a internet...", Toast.LENGTH_SHORT).show()
        }

        viewModelHouseInformation.getHouse().observe(this) { house ->
            if (house != null) {
                Glide.with(applicationContext).load(house.img).into(imgCasa)
                txtName.text = house.name
                txtWorld.text = house.world
                txtCity.text = house.town
                txtType.text = house.type
                txtBeds.text = house.beds.toString()
                txtSize.text = house.size.toString()
                txtPrice.text = decimalFormat.format(house.rent)
                txtOwner.text = house.status?.original
                binding.root.findViewById<View>(R.id.cardHouseGeneral)?.visibility = View.VISIBLE
                binding.root.findViewById<View>(R.id.textView19)?.visibility = View.VISIBLE
                txtOwner.visibility = View.VISIBLE
                binding.root.findViewById<View>(R.id.carga_house_information)?.visibility = View.GONE
            } else {
                binding.root.findViewById<View>(R.id.cardHouseGeneral)?.visibility = View.GONE
                binding.root.findViewById<View>(R.id.textView19)?.visibility = View.GONE
                txtOwner.visibility = View.GONE
                binding.root.findViewById<View>(R.id.carga_house_information)?.visibility = View.GONE
                Toast.makeText(applicationContext, "Error de conexión, intente mas tarde...", Toast.LENGTH_SHORT).show()
            }
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
