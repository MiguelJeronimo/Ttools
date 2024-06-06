package com.example.TibiaTools.View;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.View.ViewModel.ViewModelWorlds;
import com.example.TibiaTools.recyclerview.Adapters.adapterRecyclerviewMundos;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewMundos;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityHighscoresBinding;
import com.example.ttools.databinding.ActivityMundosBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Mundos extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterRecyclerviewMundos myAdapter;
    ActivityMundosBinding binding;
    List<ItemsRecyclerViewMundos> itemsRecyclerViewMundos = new ArrayList<>();
    ViewModelProvider viewModelProvider;
    ViewModelWorlds viewModelWorlds;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMundosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerviewmundos);
        recyclerView.setHasFixedSize(true);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new adapterRecyclerviewMundos(itemsRecyclerViewMundos);
        recyclerView.setAdapter(myAdapter);
        viewModelProvider = new ViewModelProvider(this);
        viewModelWorlds = viewModelProvider.get(ViewModelWorlds.class);
        viewModelWorlds.worlds().observe(this, worlds -> {
            if (worlds != null){
                itemsRecyclerViewMundos.clear();
                System.out.println(worlds.getRegular_worlds().get(0).getName());
                worlds.getRegular_worlds().forEach(world->{
                    itemsRecyclerViewMundos.add(new ItemsRecyclerViewMundos(
                            world.getName(),
                            world.getStatus(),
                            String.valueOf(world.getPlayers_online()),
                            world.getLocation(),
                            world.getPvp_type(),
                            String.valueOf(world.getPremium_only()),
                            world.getTransfer_type(),
                            String.valueOf(world.getBattleye_protected()),
                            world.getBattleye_date(),
                            world.getGame_world_type(),
                            world.getTournament_world_type()
                    ));
                });
                myAdapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
            } else{
                Toast.makeText(getApplicationContext(),"No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
            }
            binding.getRoot().findViewById(R.id.carga_mundos).setVisibility(View.GONE);
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                viewModelWorlds.setWorlds();
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
}

