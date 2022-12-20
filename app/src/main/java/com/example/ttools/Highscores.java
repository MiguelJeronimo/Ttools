package com.example.ttools;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.Worlds.DataWords;
import com.example.ttools.APISERVER.models.Worlds.RegularWorlds;
import com.example.ttools.APISERVER.models.Worlds.Worlds;
import com.example.ttools.Operaciones.InstanciaRetrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ttools.databinding.ActivityHighscoresBinding;
import com.example.ttools.utilidades.DataHighScores;
import com.example.ttools.utilidades.Spinners;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Highscores extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityHighscoresBinding binding;
    String url = "https://api.tibiadata.com/v3/";
    DataHighScores highscores = new DataHighScores();
    Spinner spinnerWorlds, spinnerVocations, spinnerCategorys;
    ArrayAdapter<String> adapterWorlds, adapterVocations, adapterCategorys;
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    Asincronia asincronia = new Asincronia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHighscoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        spinnerWorlds = (Spinner) findViewById(R.id.spinner_worldss);
        spinnerVocations = (Spinner) findViewById(R.id.spinner_vocationss);
        spinnerCategorys = (Spinner) findViewById(R.id.spinner_category);
        spinnerVocations.setOnItemSelectedListener(this);
        spinnerCategorys.setOnItemSelectedListener(this);
        spinnerWorlds.setOnItemSelectedListener(this);
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
                    arrayWorlds.add("Seleccione");
                    for (RegularWorlds mundos: worlds.getRegular_worlds()) {
                        arrayWorlds.add(mundos.getName());
                    }
                    adapterWorlds = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text_style,arrayWorlds);
                    System.out.println("Datos: "+arrayWorlds);
                    spinnerWorlds.setAdapter(adapterWorlds);
                }
            }

            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {

            }
        });
    };

    public void spinners(){
        Spinners spinners = new Spinners();
        adapterVocations = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text_style,spinners.spinnerVocations());
        adapterCategorys = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text_style,spinners.spinnerCategory());
        System.out.println("DATOS: "+ spinners.spinnerCategory());
        spinnerVocations.setAdapter(adapterVocations);
        spinnerCategorys.setAdapter(adapterCategorys);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String mundo = null, categoria = null, vocacion = null;
            //System.out.println(spinnerWorlds.getItemAtPosition(i));
            if (adapterView.getId() == R.id.spinner_worldss){
                System.out.println(spinnerWorlds.getItemAtPosition(i));
                mundo = (String) spinnerWorlds.getItemAtPosition(i);
                highscores.setMundo(mundo);

            }
            if (adapterView.getId() == R.id.spinner_category){
                System.out.println(spinnerCategorys.getItemAtPosition(i));
                categoria = (String) spinnerCategorys.getItemAtPosition(i);
                highscores.setCategoria(categoria);
            }
            if (adapterView.getId()== R.id.spinner_vocationss){
                System.out.println(spinnerVocations.getItemAtPosition(i));
                vocacion = (String) spinnerVocations.getItemAtPosition(i);
                highscores.setVocacion(vocacion);
            }
            //System.out.println("Mundo: "+highscores.getMundo()+" Categoria: "+highscores.getCategoria()+" Vocacion: "+highscores.getVocacion());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        System.out.println("NO ESTA CHECKEADO");
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
                spinners();
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