package com.example.TibiaTools.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.APICriatures;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.View.ViewModel.ViewModelCreatures;
import com.example.TibiaTools.recyclerview.Adapters.adapterRecyclerViewCriatures;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewCriatures;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityCriaturasBinding;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;

public class Criaturas extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterRecyclerViewCriatures myAdapter;
    List<ItemsRecyclerViewCriatures> itemsRecyclerViewCriatures = new ArrayList<>();
    private ActivityCriaturasBinding binding;
    ViewModelProvider viewModelProvider;
    ViewModelCreatures viewModelCreatures;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCriaturasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        recyclerView = findViewById(R.id.recyclerCriaturas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new adapterRecyclerViewCriatures(itemsRecyclerViewCriatures);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myAdapter);

        viewModelProvider = new ViewModelProvider(this);
        viewModelCreatures = viewModelProvider.get(ViewModelCreatures.class);

        viewModelCreatures.creature().observe(this, creatures->{
            if (creatures != null){
                //binding.getRoot().findViewById(R.id.carga_criatures).setVisibility(View.GONE);
                itemsRecyclerViewCriatures.add(new ItemsRecyclerViewCriatures(
                        "Today's Boss: "+creatures.getBoosted().getName(),
                        creatures.getBoosted().getRace(),
                        creatures.getBoosted().getImage_url()));
                creatures.getCriatures_list().forEach(creature->{
                    itemsRecyclerViewCriatures.add(new ItemsRecyclerViewCriatures(
                            creature.getName(),
                            creature.getRace(),
                            creature.getImage_url()));
                });
                myAdapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(getApplicationContext(),"Error al obtener las criaturas, intente mas tarde", Toast.LENGTH_SHORT).show();
            }
            binding.getRoot().findViewById(R.id.carga_criatures).setVisibility(View.GONE);
        });

        myAdapter.setOnClickListener(view -> {
            String raceCriatures = itemsRecyclerViewCriatures.get(recyclerView.getChildAdapterPosition(view)).getLbrace();
            Intent intent = new Intent(Criaturas.this, CriaturesInformation.class);
            intent.putExtra("raceCriatures", raceCriatures);
            startActivity(intent);
        });
    }
/*
* Este metodo se encarga de la navegacion entre las diferentes pantallas
* */
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

