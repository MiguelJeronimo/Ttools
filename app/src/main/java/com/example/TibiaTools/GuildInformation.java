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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuildInformation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityGuildsBinding binding;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    //recyclerview
    RecyclerView recyclerView;
    AdapterRecyclerViewGuildsList adaptador;
    List<ItemsRecyclerViewGuilds> itemsRecyclerViewGuilds;
    String url = "https://api.tibiadata.com/v3/";
    InstanciaRetrofit servicio = new InstanciaRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        spinner = findViewById(R.id.spinner_guild);
        spinner.setOnItemSelectedListener(this);
        Hilo();
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
            public void onResponse(Call<DataWords> call, Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    Worlds worlds = dataWords.getWorlds();
                    //foreach en java
                    arrayWorlds.add("Seleccione");
                    for (RegularWorlds mundos: worlds.getRegular_worlds()) {
                        arrayWorlds.add(mundos.getName());
                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text_style,arrayWorlds);
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
            InstanciaRetrofit retrofit = new InstanciaRetrofit();
            TibiaAPIServer tibiaAPIServer = retrofit.getRetrofit(url_world).create(TibiaAPIServer.class);
            Call<ApiGuilds> call = tibiaAPIServer.getGuildsInformation(world);
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
                            System.out.println(active.getLogo_url());
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
                        adaptador.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               String nameGuild = itemsRecyclerViewGuilds.get(recyclerView.getChildAdapterPosition(view)).getLbName();
                                Intent intent = new Intent(GuildInformation.this,GuildInformationName.class);
                                intent.putExtra("nameGuild",nameGuild);
                                startActivity(intent);
                            }
                        });

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

    public void Hilo(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                llenarSpinner(url);
            }
        });
        hilo.start();
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