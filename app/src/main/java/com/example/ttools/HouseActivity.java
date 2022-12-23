package com.example.ttools;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.Worlds.DataWords;
import com.example.ttools.APISERVER.models.Worlds.RegularWorlds;
import com.example.ttools.APISERVER.models.Worlds.Worlds;
import com.example.ttools.Operaciones.InstanciaRetrofit;
import com.example.ttools.utilidades.DataHighScores;
import com.example.ttools.utilidades.Spinners;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ttools.databinding.ActivityHouseBinding;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHouseBinding binding;
    String url = "https://api.tibiadata.com/v3/";
    DataHighScores dataHighScores = new DataHighScores();
    Spinner spinnerWorlds, spinnerCitys;
    ArrayAdapter<String> adapterWorlds,adapterCitys;
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    Asincronia asincronia = new Asincronia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHouseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        spinnerWorlds = (Spinner) findViewById(R.id.spinner_mundos);
        spinnerCitys = (Spinner) findViewById(R.id.spinner_citys);
        spinnerWorlds.setOnItemSelectedListener(this);
        spinnerCitys.setOnItemSelectedListener(this);
        asincronia.execute();
    }

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

    public void spinnersCity(){
        Spinners spinners = new Spinners();
        adapterCitys = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text_style,spinners.LeerDataCitys(getResources().openRawResource(R.raw.data)));
        spinnerCitys.setAdapter(adapterCitys);
    }

    public void spinnerWorlds(String url){
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url).create(TibiaAPIServer.class);
        Call<DataWords> call = tibiaAPIServer.getWorlds();
        ArrayList<String> arrayWorlds = new ArrayList<>();
        call.enqueue(new Callback<DataWords>() {
            @Override
            public void onResponse(Call<DataWords> call, Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    Worlds worlds = dataWords.getWorlds();
                    //foreach en java
                    arrayWorlds.add("All");
                    for (RegularWorlds mundos: worlds.getRegular_worlds()) {
                        arrayWorlds.add(mundos.getName());
                    }
                    adapterWorlds = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text_style,arrayWorlds);
                    spinnerWorlds.setAdapter(adapterWorlds);
                }
            }

            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //implementacion de tareas asincronas
    private class Asincronia extends AsyncTask {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                spinnerWorlds(url);
            }
        });
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                spinnersCity();
            }
        });
        //Thread hilo = new Thread();
        @Override
        protected Object doInBackground(Object[] objects) {
            hilo.start();
            hilo2.start();
            return null;
        }
    }
}