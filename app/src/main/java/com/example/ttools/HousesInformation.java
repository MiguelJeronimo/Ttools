package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.ApiHousesInformation;
import com.example.ttools.APISERVER.models.Houses.House;
import com.example.ttools.Operaciones.InstanciaRetrofit;
import com.example.ttools.databinding.ActivityHousesInformationBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HousesInformation extends AppCompatActivity {
    Intent intent;
    String id_house, mundo;
    String url = "https://api.tibiadata.com/v3/";
    ImageView imgCasa;
    private ActivityHousesInformationBinding binding;
    InstanciaRetrofit services = new InstanciaRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHousesInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        intent = getIntent();
        id_house = intent.getStringExtra("ID");
        mundo = intent.getStringExtra("mundo");
        imgCasa = findViewById(R.id.imgCasa);
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

    public void llenarData(String url, String mundo, String id_house){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiHousesInformation> call = tibiaAPIServer.getHouseInformation(mundo, id_house);
        call.enqueue(new Callback<ApiHousesInformation>() {
            @Override
            public void onResponse(Call<ApiHousesInformation> call, Response<ApiHousesInformation> response) {
                if (response.isSuccessful()){
                    ApiHousesInformation apiHousesInformation = response.body();
                    House house = apiHousesInformation.getHouse();
                    Glide.with(getApplicationContext()).load(house.getImg()).into(imgCasa);
                }
            }

            @Override
            public void onFailure(Call<ApiHousesInformation> call, Throwable t) {

            }
        });
    }
}