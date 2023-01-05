package com.example.ttools;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.ApiHighScores;
import com.example.ttools.APISERVER.models.HighScores.HighScore;
import com.example.ttools.APISERVER.models.HighScores.HighscoreList.HighscoreList;
import com.example.ttools.APISERVER.models.Worlds.DataWords;
import com.example.ttools.APISERVER.models.Worlds.RegularWorlds;
import com.example.ttools.APISERVER.models.Worlds.Worlds;
import com.example.ttools.Operaciones.InstanciaRetrofit;

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

import com.example.ttools.databinding.ActivityHighscoresBinding;
import com.example.ttools.recyclerview.Adapters.AdapterRecyclerViewHighScores;
import com.example.ttools.recyclerview.ItemsRecyclerViewHighScores;
import com.example.ttools.utilidades.DataHighScores;
import com.example.ttools.utilidades.Spinners;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Highscores extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityHighscoresBinding binding;
    String url = "https://api.tibiadata.com/v3/";
    DataHighScores dataHighScores = new DataHighScores();
    Spinner spinnerWorlds, spinnerVocations, spinnerCategorys;
    ArrayAdapter<String> adapterWorlds, adapterVocations, adapterCategorys;
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    Asincronia asincronia = new Asincronia();
    //recycler
    RecyclerView recyclerView;
    AdapterRecyclerViewHighScores adaptador;
    List<ItemsRecyclerViewHighScores> lista_highscore;

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
    };

    public void llenarRecyclerViewHighScores(String world, String category, String vocation){
        recyclerView = findViewById(R.id.recycler_highscores);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        String pattern = "#,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setGroupingSize(3);
        String url_highscores = "https://api.tibiadata.com/v3/";
        TibiaAPIServer apiServer = servicio.getRetrofit(url_highscores).create(TibiaAPIServer.class);
        Call <ApiHighScores> call = apiServer.getHighScoreInformation(world,category,vocation);
        call.enqueue(new Callback<ApiHighScores>() {
            @Override
            public void onResponse(Call<ApiHighScores> call, Response<ApiHighScores> response) {
                if (response.isSuccessful()){
                    ApiHighScores apiHighScores = response.body();
                    HighScore highScore = apiHighScores.getHighScores();
                    lista_highscore = new ArrayList<>();
                    String value = null;
                    String categorias = null;
                    for (HighscoreList lista : highScore.getHighscore_list()) {
                        categorias = highScore.getCategory();
                        if (categorias.equalsIgnoreCase("achievements")){
                            value = "Archivements: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("axefighting")){
                            value = "Axe Fighting : "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("charmpoints")){
                            value = "Charm Points: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("clubfighting")){
                            value = "Club Fighting: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("distancefighting")){
                            value = "Distance Fighting: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("experience")){
                            value = "Experience: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("fishing")){
                            value = "Fishing: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("fistfighting")){
                            value = "Fist Fighting: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("goshnarstaint")){
                            value = "Goshnar's Taint: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("loyaltypoints")){
                            value = "Loyalty Points: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("magiclevel")){
                            value = "Magic Level: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("shielding")){
                            value = "Shielding: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("swordfighting")){
                            value = "Sword Fighting: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("dromescore")){
                            value = "Drome Score: "+decimalFormat.format(lista.getValue());}
                        if (categorias.equalsIgnoreCase("bosspoints")){
                            value = "Boss Points: "+decimalFormat.format(lista.getValue());}

                        lista_highscore.add(new ItemsRecyclerViewHighScores(
                           String.valueOf(lista.getRank()),
                           lista.getName(),
                           lista.getVocation(),
                           lista.getWorld(),
                           String.valueOf(lista.getLevel()),
                           value,
                           lista.getTitle()
                        ));
                    }
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    adaptador = new AdapterRecyclerViewHighScores(lista_highscore);
                    adaptador.notifyDataSetChanged();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adaptador);
                }else{
                    Toast.makeText(getApplicationContext(),"No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
                    lista_highscore.clear();
                    adaptador.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ApiHighScores> call, Throwable t) {
                System.out.println("MENSAJE: "+t.getMessage());
                //lista_highscore.clear();
                //adaptador.notifyDataSetChanged();
            }
        });
    }

    public void spinners(){
        Spinners spinners = new Spinners();
        adapterVocations = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text_style,spinners.spinnerVocations(getResources().openRawResource(R.raw.vocations)));
        adapterCategorys = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text_style,spinners.spinnerCategory(getResources().openRawResource(R.raw.categorias)));
        spinnerVocations.setAdapter(adapterVocations);
        spinnerCategorys.setAdapter(adapterCategorys);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String mundo = null, categoria = null, vocacion = null;
            //System.out.println(spinnerWorlds.getItemAtPosition(i));
            if (adapterView.getId() == R.id.spinner_worldss){
                mundo = (String) spinnerWorlds.getItemAtPosition(i);
                dataHighScores.setMundo(mundo);
            }
            if (adapterView.getId() == R.id.spinner_category){
                categoria = (String) spinnerCategorys.getItemAtPosition(i);
                dataHighScores.setCategoria(categoria);
            }
            if (adapterView.getId()== R.id.spinner_vocationss){
                vocacion = (String) spinnerVocations.getItemAtPosition(i);
                dataHighScores.setVocacion(vocacion);
            }
            llenarRecyclerViewHighScores(dataHighScores.getMundo(),dataHighScores.getCategoria().replace(" ","").toLowerCase(),dataHighScores.getVocacion());
            //System.out.println("Mundo: "+dataHighScores.getMundo()+" Categoria: "+dataHighScores.getCategoria().replace(" ","").toLowerCase()+" Vocacion: "+dataHighScores.getVocacion());
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