package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.APICriatures;
import com.example.ttools.APISERVER.models.criatures.Criatures;
import com.example.ttools.Operaciones.InstanciaRetrofit;
import com.example.ttools.recyclerview.Adapters.adapterRecyclerViewCriatures;
import com.example.ttools.recyclerview.ItemsRecyclerViewCriatures;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttools.databinding.ActivityCriaturasBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Criaturas extends AppCompatActivity {
    RecyclerView recyclerView;
    adapterRecyclerViewCriatures myAdapter;
    List<ItemsRecyclerViewCriatures> itemsRecyclerViewCriatures;
    String url = "https://api.tibiadata.com/v3/";
    private AppBarConfiguration appBarConfiguration;
    private ActivityCriaturasBinding binding;
    InstanciaRetrofit services = new InstanciaRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCriaturasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        llenarRecyclerViewCriaturas(url);
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

    public void llenarRecyclerViewCriaturas(String url) {
        recyclerView = findViewById(R.id.recyclerCriaturas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<APICriatures> call = tibiaAPIServer.getCreatures();
        call.enqueue(new retrofit2.Callback<APICriatures>() {
            @Override
            public void onResponse(Call<APICriatures> call, retrofit2.Response<APICriatures> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Codigo: " + response.code());
                    return;
                }
                APICriatures apiCriatures = response.body();
                Criatures criatures = apiCriatures.getCreatures();
                itemsRecyclerViewCriatures = new ArrayList<>();
                itemsRecyclerViewCriatures.add(new ItemsRecyclerViewCriatures(
                        "Today's Boss: "+criatures.getBoosted().getName(),
                        criatures.getBoosted().getRace(),
                        criatures.getBoosted().getImage_url()));
                for (int i = 0; i < criatures.getCriatures_list().size(); i++) {
                    itemsRecyclerViewCriatures.add(
                            new ItemsRecyclerViewCriatures(criatures.getCriatures_list().get(i).getName(),
                            criatures.getCriatures_list().get(i).getRace(),
                            criatures.getCriatures_list().get(i).getImage_url()));
                }
                recyclerView.setLayoutManager(layoutManager);
                myAdapter = new adapterRecyclerViewCriatures(itemsRecyclerViewCriatures);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(myAdapter);
                myAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String raceCriatures = itemsRecyclerViewCriatures.get(recyclerView.getChildAdapterPosition(view)).getLbrace();
                        Intent intent = new Intent(Criaturas.this, CriaturesInformation.class);
                        intent.putExtra("raceCriatures", raceCriatures);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<APICriatures> call, Throwable t) {
                Toast.makeText(Criaturas.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

