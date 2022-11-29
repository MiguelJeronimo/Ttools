package com.example.ttools;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.GuildInformation.ApiGuilds;
import com.example.ttools.APISERVER.models.GuildInformation.guilds.Active.Active;
import com.example.ttools.APISERVER.models.GuildInformation.guilds.Guilds;
import com.example.ttools.APISERVER.models.Worlds.DataWords;
import com.example.ttools.APISERVER.models.Worlds.RegularWorlds;
import com.example.ttools.APISERVER.models.Worlds.Worlds;
import com.example.ttools.recyclerview.Adapters.AdapterRecyclerViewGuildsList;
import com.example.ttools.recyclerview.ItemsRecyclerViewGuilds;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ttools.databinding.ActivityGuildsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuildInformation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityGuildsBinding binding;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    //recyclerview
    RecyclerView recyclerView;
    AdapterRecyclerViewGuildsList adaptador;
    List<ItemsRecyclerViewGuilds> itemsRecyclerViewGuilds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        spinner = findViewById(R.id.spinner_guild);
        spinner.setOnItemSelectedListener(this);
        String url = "https://api.tibiadata.com/v3/";
        //llenarRecyclerView("Wintera");
        llenarSpinner(url);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void llenarSpinner(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArrayList<String> arrayWorlds = new ArrayList<>();
        TibiaAPIServer tibiaAPIServer = retrofit.create(TibiaAPIServer.class);
        Call <DataWords> call = tibiaAPIServer.getWorlds();
        call.enqueue(new Callback<DataWords>() {
            @Override
            public void onResponse(Call<DataWords> call, Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    Worlds worlds = dataWords.getWorlds();
                    //foreach en java
                    arrayWorlds.add("Seleccione");
                    for (RegularWorlds mundos: worlds.getRegular_worlds()) {
                        arrayWorlds.add(mundos.getName());
                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayWorlds);
                    spinner.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {
                Toast.makeText(GuildInformation.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void llenarRecyclerView(String world){
        if (!world.equals("Seleccione")){
            recyclerView = (RecyclerView) findViewById(R.id.recyclerGuilds);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            String url_world = "https://api.tibiadata.com/v3/guilds/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url_world)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TibiaAPIServer services = retrofit.create(TibiaAPIServer.class);
            Call<ApiGuilds> call = services.getGuildsInformation(world);
            call.enqueue(new Callback<ApiGuilds>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(Call<ApiGuilds> call, Response<ApiGuilds> response) {
                    if (response.isSuccessful()){
                        ApiGuilds apiGuilds = response.body();
                        Guilds guilds = apiGuilds.getGuilds();
                        System.out.println(guilds.getActive().size());
                        itemsRecyclerViewGuilds = new ArrayList<>();
                        for (Active active: guilds.getActive()) {
                            System.out.println("Guild: " + active.getName()+" Descripción: "+active.getDescription());
                            itemsRecyclerViewGuilds.add(
                                    new ItemsRecyclerViewGuilds(
                                            active.getName(),
                                            active.getLogo_url(),
                                            active.getDescription()));
                        }

                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(layoutManager);
                        adaptador = new AdapterRecyclerViewGuildsList(itemsRecyclerViewGuilds);
                        adaptador.notifyDataSetChanged();
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adaptador);

                    } else {
                        System.out.println("No hay respuesta "+response.code());
                    }
                }
                @Override
                public void onFailure(Call<ApiGuilds> call, Throwable t) {
                    Toast.makeText(GuildInformation.this, "ERROR: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else{
            Toast.makeText(this,"Seleccione una opción", Toast.LENGTH_SHORT).show();
            if (itemsRecyclerViewGuilds!=null){
                itemsRecyclerViewGuilds.clear();
                adaptador.notifyDataSetChanged();
            }
        }
    }

//eventos del spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("OPCIONES"+adapterView.getItemAtPosition(i));
        if (adapterView.getItemAtPosition(i).toString() != "Seleccione un mundo"){
            //System.out.println(adapterView.getItemAtPosition(i).toString());
           llenarRecyclerView(adapterView.getItemAtPosition(i).toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        adapterView.getItemAtPosition(1);
    }
}