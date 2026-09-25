package com.example.TibiaTools.View

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.View.ViewModel.ViewModelGuildInformation
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewGuildName
import com.example.TibiaTools.recyclerview.itemsRecyclerViewGuildsName
import com.example.ttools.R
import com.example.ttools.databinding.ActivityGuildInformationNameBinding
import org.koin.android.ext.android.inject
import java.util.ArrayList

class GuildInformationName : AppCompatActivity() {
    private lateinit var binding: ActivityGuildInformationNameBinding
    private var guildName: String? = null
    private lateinit var imageViewGuildLogo: ImageView
    private lateinit var textViewGuildName: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var textViewInWar: TextView
    private lateinit var textViewOnline: TextView
    private lateinit var textViewNombre: TextView
    private lateinit var textViewMundo: TextView
    private lateinit var textViewPiad: TextView
    private lateinit var textViewFounded: TextView
    private lateinit var textViewActive: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterRecyclerViewGuildName
    private val itemsRecyclerViewGuildsNames = ArrayList<itemsRecyclerViewGuildsName>()
    private val viewModelGuildInformation: ViewModelGuildInformation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuildInformationNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        guildName = intent.getStringExtra("nameGuild")
        supportActionBar?.title = guildName
        guildName?.let { viewModelGuildInformation.setGuild(it) }

        imageViewGuildLogo = binding.root.findViewById(R.id.imageViewGuildLogo)
        textViewGuildName = binding.root.findViewById(R.id.textViewGuildName)
        textViewDescription = binding.root.findViewById(R.id.textViewDescription)
        textViewInWar = binding.root.findViewById(R.id.textViewInWar)
        textViewOnline = binding.root.findViewById(R.id.textViewOnline)
        textViewNombre = binding.root.findViewById(R.id.textViewNombre)
        textViewMundo = binding.root.findViewById(R.id.textViewMundo)
        textViewPiad = binding.root.findViewById(R.id.textViewPiad)
        textViewFounded = binding.root.findViewById(R.id.textViewFounded)
        textViewActive = binding.root.findViewById(R.id.textViewActive)

        recyclerView = findViewById(R.id.recyclerViewGuildName)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        adapter = AdapterRecyclerViewGuildName(itemsRecyclerViewGuildsNames)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModelGuildInformation.guild().observe(this) { guild ->
            if (guild != null) {
                Glide.with(applicationContext).load(guild.logo_url).into(imageViewGuildLogo)
                textViewGuildName.text = guild.name
                textViewDescription.text = guild.description
                textViewInWar.text = if (guild.in_war == true) "Si" else "No"
                textViewOnline.text = "${guild.players_online}/${guild.members_total}"

                val halls = guild.guildhalls
                if (!halls.isNullOrEmpty()) {
                    textViewNombre.text = halls[0].name
                    textViewMundo.text = halls[0].world
                    textViewPiad.text = halls[0].paid_until
                }
                textViewFounded.text = "Fundada: ${guild.founded}"
                textViewActive.text = if (guild.active == true) "Active: Si" else "Active: No"

                val membersList = guild.members
                if (membersList != null) {
                    itemsRecyclerViewGuildsNames.clear()
                    membersList.forEach { membersGuild ->
                        itemsRecyclerViewGuildsNames.add(
                            itemsRecyclerViewGuildsName(
                                membersGuild.name,
                                membersGuild.title,
                                membersGuild.rank,
                                membersGuild.vocation,
                                membersGuild.level,
                                membersGuild.joined,
                                membersGuild.status
                            )
                        )
                    }
                    recyclerView.visibility = View.VISIBLE
                    binding.root.findViewById<View>(R.id.cardHeader)?.visibility = View.VISIBLE
                    binding.root.findViewById<View>(R.id.separador_header_guild)?.visibility = View.VISIBLE
                    binding.root.findViewById<View>(R.id.miembros)?.visibility = View.VISIBLE
                    binding.root.findViewById<View>(R.id.carga_guild_information)?.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            } else {
                binding.root.findViewById<View>(R.id.carga_guild_information)?.visibility = View.GONE
                recyclerView.visibility = View.GONE
                binding.root.findViewById<View>(R.id.cardHeader)?.visibility = View.GONE
                binding.root.findViewById<View>(R.id.separador_header_guild)?.visibility = View.GONE
                binding.root.findViewById<View>(R.id.miembros)?.visibility = View.GONE
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
