package com.example.TibiaTools.View;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuilds;
import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Active.Active;
import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Guilds;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.RegularWorlds;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.View.ViewModel.ViewModelGuilds;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewGuildsList;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewGuilds;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityGuildsBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuildInformation extends AppCompatActivity {
    private ActivityGuildsBinding binding;
    AutoCompleteTextView spinner;
    ArrayAdapter<String> adapter;
    //recyclerview
    RecyclerView recyclerView;
    AdapterRecyclerViewGuildsList adaptador;
    List<ItemsRecyclerViewGuilds> itemsRecyclerViewGuilds = new ArrayList<>();
    String url = "https://api.tibiadata.com/v4/";
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    LinearProgressIndicator linearProgressIndicator;
    ViewModelProvider viewModelProvider;
    ViewModelGuilds viewModelGuilds;
    View view;
    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        linearProgressIndicator = findViewById(R.id.carga_guilds);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        spinner = findViewById(R.id.spinner_guild);
        view = findViewById(android.R.id.content);
        //RecyclerView configuration
        recyclerView = findViewById(R.id.recyclerGuilds);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adaptador = new AdapterRecyclerViewGuildsList(itemsRecyclerViewGuilds);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptador);

        viewModelProvider = new ViewModelProvider(this);
        viewModelGuilds = viewModelProvider.get(ViewModelGuilds.class);
        viewModelGuilds.Worlds().observe(this, worlds -> {
            if (worlds != null){
                adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.auto_complete, worlds);
                spinner.setAdapter(adapter);
                linearProgressIndicator.setVisibility(View.GONE);
            } else {
                Snackbar.make(view, "No charged worlds", Snackbar.LENGTH_LONG).show();
                linearProgressIndicator.setVisibility(View.GONE);
            }
        });

        viewModelGuilds.Guild().observe(this,guilds -> {
            itemsRecyclerViewGuilds.clear();
            if (guilds != null){
                if (guilds.getActive() != null){
                    guilds.getActive().forEach(guild->{
                        itemsRecyclerViewGuilds.add(
                                new ItemsRecyclerViewGuilds(
                                        guild.getName(),
                                        guild.getLogo_url(),
                                        guild.getDescription()
                                )
                        );
                    });
                    adaptador.notifyDataSetChanged();
                    linearProgressIndicator.setVisibility(View.GONE);
                } else {
                    Snackbar.make(view, "No guilds active", Snackbar.LENGTH_LONG).show();
                    linearProgressIndicator.setVisibility(View.GONE);
                }
            } else {
                Snackbar.make(view, "Error loading data", Snackbar.LENGTH_LONG).show();
                linearProgressIndicator.setVisibility(View.GONE);
            }
        });

        adaptador.setOnClickListener(view -> {
            String nameGuild = itemsRecyclerViewGuilds.get(recyclerView.getChildAdapterPosition(view)).getLbName();
            Intent intent = new Intent(GuildInformation.this, GuildInformationName.class);
            intent.putExtra("nameGuild",nameGuild);
            startActivity(intent);
        });

        spinner.setOnItemClickListener((parent, view, position, id) -> {
            if (parent.getItemAtPosition(position).toString() != "Seleccione"){
                linearProgressIndicator.setVisibility(View.VISIBLE);
                String guildName = parent.getItemAtPosition(position).toString();
                viewModelGuilds.setGuild(guildName);
            } else {
                linearProgressIndicator.setVisibility(View.GONE);
            }
        });
    }

    //para que el boton regresar funcione
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) { //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
            finish();// finalizamos la actividad
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}