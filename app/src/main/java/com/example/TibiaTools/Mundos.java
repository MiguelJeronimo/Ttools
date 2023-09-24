package com.example.TibiaTools;

import android.graphics.Color;
import android.os.Bundle;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.recyclerview.Adapters.adapterRecyclerviewMundos;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewMundos;
import com.example.ttools.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Mundos extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterRecyclerviewMundos myAdapter;

    List<ItemsRecyclerViewMundos> itemsRecyclerViewMundos;
    String url = "https://api.tibiadata.com/v3/";
    TextView playersOnline;
    InstanciaRetrofit services = new InstanciaRetrofit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mundos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        playersOnline = (TextView) findViewById(R.id.playersOnline);
        API(url);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                playersOnline = (TextView) findViewById(R.id.playersOnline);
                API(url);
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewmundos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call <DataWords> call = tibiaAPIServer.getWorlds();
        call.enqueue(new retrofit2.Callback<DataWords>() {
            @Override
            public void onResponse(Call<DataWords> call, Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    Worlds worlds = dataWords.getWorlds();
                    playersOnline.setText("Players Online "+ String.valueOf(worlds.getPlayers_online()));
                    playersOnline.setTextColor(Color.parseColor("#76FF03"));
                    System.out.println(worlds.getRegular_worlds().size());
                    itemsRecyclerViewMundos = new ArrayList<>();
                    for (int i = 0; i < worlds.getRegular_worlds().size(); i++) {
                        itemsRecyclerViewMundos.add(new ItemsRecyclerViewMundos(
                                worlds.getRegular_worlds().get(i).getName(),
                                worlds.getRegular_worlds().get(i).getStatus(),
                                String.valueOf(worlds.getRegular_worlds().get(i).getPlayers_online()),
                                worlds.getRegular_worlds().get(i).getLocation(),
                                worlds.getRegular_worlds().get(i).getPvp_type(),
                                String.valueOf(worlds.getRegular_worlds().get(i).getPremium_only()),
                                worlds.getRegular_worlds().get(i).getTransfer_type(),
                                String.valueOf(worlds.getRegular_worlds().get(i).getBattleye_protected()),
                                worlds.getRegular_worlds().get(i).getBattleye_date(),
                                worlds.getRegular_worlds().get(i).getGame_world_type(),
                                worlds.getRegular_worlds().get(i).getTournament_world_type()
                        ));
                }
                    recyclerView.setHasFixedSize(true);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter = new adapterRecyclerviewMundos(itemsRecyclerViewMundos);
                    recyclerView.setAdapter(myAdapter);
            } else{
                    playersOnline.setText("No tienes internet :v");
                }

            }

            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {
                playersOnline.setText(t.getMessage());
                playersOnline.setTextColor(Color.RED);
                System.out.println(t.getMessage());
            }
        });
    }
}

