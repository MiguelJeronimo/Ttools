package com.example.TibiaTools;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuilds;
import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Active.Active;
import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Guilds;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.RegularWorlds;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewGuildsList;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewGuilds;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityGuildsBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
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
    List<ItemsRecyclerViewGuilds> itemsRecyclerViewGuilds;
    String url = "https://api.tibiadata.com/v3/";
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    LinearProgressIndicator linearProgressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        linearProgressIndicator = findViewById(R.id.carga_guilds);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        spinner = findViewById(R.id.spinner_guild);
        Hilo();
        spinner.setOnItemClickListener((parent, view, position, id) -> {
            if (parent.getItemAtPosition(position).toString() != "Seleccione un mundo"){
                linearProgressIndicator.setVisibility(View.VISIBLE);
                llenarRecyclerView(parent.getItemAtPosition(position).toString());
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

    public void llenarSpinner(String url){
        ArrayList<String> arrayWorlds = new ArrayList<>();
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url).create(TibiaAPIServer.class);
        Call <DataWords> call = tibiaAPIServer.getWorlds();
        call.enqueue(new Callback<DataWords>() {
            @Override
            public void onResponse(@NonNull Call<DataWords> call, @NonNull Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    assert dataWords != null;
                    Worlds worlds = dataWords.getWorlds();
                    //foreach en java
                    arrayWorlds.add("Seleccione");
                    for (RegularWorlds mundos: worlds.getRegular_worlds()) {
                        arrayWorlds.add(mundos.getName());
                    }
                    adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.auto_complete, arrayWorlds);
                    spinner.setAdapter(adapter);
                    linearProgressIndicator.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(@NonNull Call<DataWords> call, @NonNull Throwable t) {
                Toast.makeText(GuildInformation.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                linearProgressIndicator.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void llenarRecyclerView(String world){
        if (!world.equals("Seleccione")){
            recyclerView = (RecyclerView) findViewById(R.id.recyclerGuilds);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            String url_world = "https://api.tibiadata.com/v3/guilds/";
            InstanciaRetrofit retrofit = new InstanciaRetrofit();
            TibiaAPIServer tibiaAPIServer = retrofit.getRetrofit(url_world).create(TibiaAPIServer.class);
            Call<ApiGuilds> call = tibiaAPIServer.getGuildsInformation(world);
            call.enqueue(new Callback<ApiGuilds>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(@NonNull Call<ApiGuilds> call, @NonNull Response<ApiGuilds> response) {
                    if (response.isSuccessful()){
                        ApiGuilds apiGuilds = response.body();
                        assert apiGuilds != null;
                        Guilds guilds = apiGuilds.getGuilds();
                        System.out.println(guilds.getActive().size());
                        itemsRecyclerViewGuilds = new ArrayList<>();
                        for (Active active: guilds.getActive()) {
                            //System.out.println("Name: "+active.getName()+": "+active.getLogo_url());
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
                        linearProgressIndicator.setVisibility(View.GONE);
                        //agregando el evento onclick con un lambda en java
                        adaptador.setOnClickListener(view -> {
                           String nameGuild = itemsRecyclerViewGuilds.get(recyclerView.getChildAdapterPosition(view)).getLbName();
                            Intent intent = new Intent(GuildInformation.this,GuildInformationName.class);
                            intent.putExtra("nameGuild",nameGuild);
                            startActivity(intent);
                        });

                    } else {
                        System.out.println("No hay respuesta "+response.code());
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ApiGuilds> call, @NonNull Throwable t) {
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

    public void Hilo(){
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(3000);
                llenarSpinner(url);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        hilo.start();
    }

//eventos del spinner
    /*@Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("option: "+adapterView.getItemAtPosition(i).toString());
        if (adapterView.isSelected() !=)
        /*if (adapterView.getItemAtPosition(i).toString() != "Seleccione un mundo"){
            System.out.println(adapterView.getItemAtPosition(i).toString());
           //llenarRecyclerView(adapterView.getItemAtPosition(i).toString());
        }
    }*/

   /* @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        adapterView.getItemAtPosition(1);
    }*/
}