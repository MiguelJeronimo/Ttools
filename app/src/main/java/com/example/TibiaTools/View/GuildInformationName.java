package com.example.TibiaTools.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuildsName;
import com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.GuildName;
import com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.Guild;
import com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.members.MembersGuild;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.View.ViewModel.ViewModelGuildInformation;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewGuildName;
import com.example.TibiaTools.recyclerview.itemsRecyclerViewGuildsName;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityGuildInformationNameBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuildInformationName extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityGuildInformationNameBinding binding;
    Intent intent;
    String guildName;
    ImageView imageViewGuildLogo;
    TextView textViewGuildName,textViewDescription,textViewInWar,textViewOnline,
             textViewNombre,textViewMundo,textViewPiad,textViewFounded,textViewActive;
    String url = "https://api.tibiadata.com/v4/guild/";
    RecyclerView recyclerView;
    AdapterRecyclerViewGuildName adapter;
    List<itemsRecyclerViewGuildsName> itemsRecyclerViewGuildsNames = new ArrayList<>();
    InstanciaRetrofit services = new InstanciaRetrofit();
    ViewModelGuildInformation viewModelGuildInformation;
    ViewModelProvider viewModelProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildInformationNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        viewModelProvider = new ViewModelProvider(this);
        viewModelGuildInformation = viewModelProvider.get(ViewModelGuildInformation.class);
        //respuesta de la pantalla anterior
        intent = getIntent();
        guildName = intent.getStringExtra("nameGuild");
        viewModelGuildInformation.setGuild(guildName);
        imageViewGuildLogo = binding.getRoot().findViewById(R.id.imageViewGuildLogo);
        textViewGuildName = binding.getRoot().findViewById(R.id.textViewGuildName);
        textViewDescription = binding.getRoot().findViewById(R.id.textViewDescription);
        textViewInWar = binding.getRoot().findViewById(R.id.textViewInWar);
        textViewOnline = binding.getRoot().findViewById(R.id.textViewOnline);
        textViewNombre = binding.getRoot().findViewById(R.id.textViewNombre);
        textViewMundo = binding.getRoot().findViewById(R.id.textViewMundo);
        textViewPiad = binding.getRoot().findViewById(R.id.textViewPiad);
        textViewFounded = binding.getRoot().findViewById(R.id.textViewFounded);
        textViewActive = binding.getRoot().findViewById(R.id.textViewActive);
        recyclerView = findViewById(R.id.recyclerViewGuildName);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterRecyclerViewGuildName(itemsRecyclerViewGuildsNames);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        viewModelGuildInformation.guild().observe(this, guild -> {
            if (guild != null){
                Glide.with(getApplicationContext()).load(guild.getLogo_url()).into(imageViewGuildLogo);
                textViewGuildName.setText(guild.getName());
                textViewDescription.setText(guild.getDescription());
                if (guild.getIn_war()){
                    textViewInWar.setText("Si");
                } else{
                    textViewInWar.setText("No");
                }
                textViewOnline.setText(guild.getPlayers_online()+"/"+guild.getMembers_total());
                if (guild.getGuildhalls() != null){
                    String name = guild.getGuildhalls().get(0).getName();
                    String Mundo = guild.getGuildhalls().get(0).getWorld();
                    String Paid = guild.getGuildhalls().get(0).getPaid_until();
                    textViewNombre.setText(name);
                    textViewMundo.setText(Mundo);
                    textViewPiad.setText(Paid);
                }
                textViewFounded.setText("Fundada: "+guild.getFounded());
                if (guild.getActive()){
                    textViewActive.setText("Active: Si");
                } else {
                    textViewActive.setText("Active: No");
                }
                if (guild.getMembers() != null){
                    for (MembersGuild membersGuild:guild.getMembers()) {
                        itemsRecyclerViewGuildsNames.add(
                                new itemsRecyclerViewGuildsName(
                                        membersGuild.getName(),
                                        membersGuild.getTitle(),
                                        membersGuild.getRank(),
                                        membersGuild.getVocation(),
                                        membersGuild.getLevel(),
                                        membersGuild.getJoined(),
                                        membersGuild.getStatus()
                                )
                        );
                    }
                    recyclerView.setVisibility(View.VISIBLE);
                    binding.getRoot().findViewById(R.id.cardHeader).setVisibility(View.VISIBLE);
                    binding.getRoot().findViewById(R.id.separador_header_guild).setVisibility(View.VISIBLE);
                    binding.getRoot().findViewById(R.id.miembros).setVisibility(View.VISIBLE);
                    binding.getRoot().findViewById(R.id.carga_guild_information).setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            } else {
                binding.getRoot().findViewById(R.id.carga_guild_information).setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.cardHeader).setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.separador_header_guild).setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.miembros).setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.carga_guild_information).setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
            finish();// finalizamos la actividad
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}