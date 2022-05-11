package com.example.ttools;

import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.Operaciones.information.DataWords;
import com.example.ttools.Operaciones.information.RegularWorlds;
import com.example.ttools.Operaciones.information.Worlds;
import com.example.ttools.recyclerview.Adapters.adapterRecyclerviewMundos;
import com.example.ttools.recyclerview.ItemsRecyclerViewMundos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mundos extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterRecyclerviewMundos myAdapter;
    ItemsRecyclerViewMundos items;
    List<ItemsRecyclerViewMundos> itemsRecyclerViewMundos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mundos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar

        String url = "https://api.tibiadata.com/v3/";
        API(url);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
        if (id == android.R.id.home) {
            finish();// finalizamos la actividad
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void API(String url){
        //aqui se hace la llamada a la api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TibiaAPIServer tibiaAPIServer = retrofit.create(TibiaAPIServer.class);
        Call <DataWords> call = tibiaAPIServer.getWorlds();
        call.enqueue(new retrofit2.Callback<DataWords>() {
            @Override
            public void onResponse(Call<DataWords> call, Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    Worlds worlds = dataWords.getWorlds();
                    System.out.println(worlds.getPlayers_online());
                }
            }

            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {

            }
        });

            itemsRecyclerViewMundos = new ArrayList<>(5);
        items = new ItemsRecyclerViewMundos();
        for (int i=0; i<5;i++){
            items.setLbName("Wintera");
            items.setLbStatus("online");
            itemsRecyclerViewMundos.add(items);
            /**itemsRecyclerViewMundos.get(i).setLbStatus("Hola");
             itemsRecyclerViewMundos.get(i).setLbPlayersOnline("100");*/
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewmundos);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new adapterRecyclerviewMundos(itemsRecyclerViewMundos);
        recyclerView.setAdapter(myAdapter);
    }
}

