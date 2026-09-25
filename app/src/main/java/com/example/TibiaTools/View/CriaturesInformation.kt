package com.example.TibiaTools.View

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelCreature
import com.example.ttools.R
import com.example.ttools.databinding.ActivityCriaturesInformationBinding
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import java.util.Objects

class CriaturesInformation : AppCompatActivity() {
    private lateinit var binding: ActivityCriaturesInformationBinding
    private var raceCreature: String? = null
    private var nameCreature: String? = null
    private lateinit var creatureName: TextView
    private lateinit var creatureDescription: TextView
    private lateinit var creatureBehaviour: TextView
    private lateinit var creatureHealth: TextView
    private lateinit var creatureExp: TextView
    private lateinit var creatureImage: ImageView
    private lateinit var linearLayout: LinearLayout
    private lateinit var linearLayoutInmune: LinearLayout
    private lateinit var linearLayoutStrong: LinearLayout
    private lateinit var linearLayoutWeakness: LinearLayout
    private val viewModelCreature: ViewModelCreature by inject()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriaturesInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        raceCreature = intent.getStringExtra("raceCreatures")
        nameCreature = intent.getStringExtra("nameCreatures")
        supportActionBar?.title = nameCreature

        creatureName = binding.root.findViewById(R.id.id_criature_name)
        creatureDescription = binding.root.findViewById(R.id.descripcion_criature)
        creatureBehaviour = binding.root.findViewById(R.id.behavior_criature)
        creatureHealth = binding.root.findViewById(R.id.id_health)
        creatureExp = binding.root.findViewById(R.id.id_experience_criature)
        creatureImage = binding.root.findViewById(R.id.imageViewCreature)

        raceCreature?.let { viewModelCreature.setCreature(it) }

        viewModelCreature.creature().observe(this) { creature ->
            linearLayout = binding.root.findViewById(R.id.linearLayoutLoot)
            linearLayoutInmune = binding.root.findViewById(R.id.linearLayoutInmune)
            linearLayoutStrong = binding.root.findViewById(R.id.linearLayoutStrong)
            linearLayoutWeakness = binding.root.findViewById(R.id.linearLayoutWeakness)

            if (creature != null) {
                creatureName.text = creature.name
                creatureName.visibility = View.VISIBLE
                creatureDescription.text = creature.description
                creatureBehaviour.text = creature.behaviour
                creatureHealth.text = "Hit Point: " + creature.experience_points
                creatureHealth.setTextColor(Color.parseColor("#4CAF50"))
                creatureHealth.visibility = View.VISIBLE
                creatureExp.setTextColor(Color.parseColor("#FFAB00"))
                creatureExp.text = "Experience Points: " + creature.hitpoints
                creatureExp.visibility = View.VISIBLE

                val imageCreature = creature.image_url
                Picasso.get().load(imageCreature).into(creatureImage)
                creatureImage.visibility = View.VISIBLE

                binding.root.findViewById<View>(R.id.cardDescriptionCreature)?.visibility = View.VISIBLE
                binding.root.findViewById<View>(R.id.cardBehavior)?.visibility = View.VISIBLE

                creature.loot_list?.forEach { loot ->
                    val textViewLoot = TextView(this).apply {
                        text = "- $loot"
                        setTextColor(Color.parseColor("#CE93D8"))
                        setTypeface(null, Typeface.ITALIC)
                        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    }
                    linearLayout.addView(textViewLoot)
                    binding.root.findViewById<View>(R.id.CardLootList)?.visibility = View.VISIBLE
                }

                creature.immune?.forEach { immune ->
                    val textViewImmune = TextView(this).apply {
                        text = "- $immune"
                        setTextColor(Color.parseColor("#CE93D8"))
                        setTypeface(null, Typeface.ITALIC)
                        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    }
                    linearLayoutInmune.addView(textViewImmune)
                    binding.root.findViewById<View>(R.id.CardImmune)?.visibility = View.VISIBLE
                }

                creature.strong?.forEach { strong ->
                    val textViewStrong = TextView(this).apply {
                        text = "- $strong"
                        setTextColor(Color.parseColor("#CE93D8"))
                        setTypeface(null, Typeface.ITALIC)
                        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    }
                    linearLayoutStrong.addView(textViewStrong)
                    binding.root.findViewById<View>(R.id.CardStrong)?.visibility = View.VISIBLE
                }

                creature.weakness?.forEach { weakness ->
                    val textViewWeakness = TextView(this).apply {
                        text = "- $weakness"
                        setTextColor(Color.parseColor("#CE93D8"))
                        setTypeface(null, Typeface.ITALIC)
                        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    }
                    linearLayoutWeakness.addView(textViewWeakness)
                    binding.root.findViewById<View>(R.id.CardWeakness)?.visibility = View.VISIBLE
                }
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
            binding.root.findViewById<View>(R.id.carga_creature_information)?.visibility = View.GONE
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
