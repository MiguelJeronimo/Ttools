package com.example.TibiaTools.View;

import android.os.Bundle;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiHighScores;
import com.example.TibiaTools.APISERVER.models.HighScores.HighScore;
import com.example.TibiaTools.APISERVER.models.HighScores.HighscoreList.HighscoreList;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.RegularWorlds;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewHighScores;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewHighScores;
import com.example.TibiaTools.utilidades.DataHighScores;
import com.example.TibiaTools.utilidades.Spinners;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityHighscoresBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Highscores extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityHighscoresBinding binding;
    String url = "https://api.tibiadata.com/v4/";
    DataHighScores dataHighScores = new DataHighScores();
    AutoCompleteTextView spinnerWorlds, spinnerVocations, spinnerCategorys;
    ArrayAdapter<String> adapterWorlds;
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    //recycler
    RecyclerView recyclerView;
    AdapterRecyclerViewHighScores adaptador;
    List<ItemsRecyclerViewHighScores> lista_highscore;
    String mundo = null, categoria = null, vocacion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHighscoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        //Aparicion del boton regresar en el action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.VISIBLE);
        spinnerWorlds = findViewById(R.id.spinner_worldss);
        spinnerVocations = findViewById(R.id.spinner_vocationss);
        spinnerCategorys = findViewById(R.id.spinner_category);
        Hilos();
        spinnerWorlds.setOnItemClickListener(this);
        spinnerVocations.setOnItemClickListener(this);
        spinnerCategorys.setOnItemClickListener(this);
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
                    adapterWorlds = new ArrayAdapter<String>(getApplicationContext(), R.layout.auto_complete,arrayWorlds);
                    spinnerWorlds.setAdapter(adapterWorlds);
                    binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
                }else{
                    binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {
                System.out.println(t.getMessage());
                binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error al llenar lista de mundos...\n intente de nuevo mas tarde", Toast.LENGTH_SHORT).show();
            }
        });
    };

    public void llenarRecyclerViewHighScores(String world, String category, String vocation){
        recyclerView = findViewById(R.id.recycler_highscores);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        String pattern = "#,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setGroupingSize(3);
        String url_highscores = "https://api.tibiadata.com/v4/";
        TibiaAPIServer apiServer = servicio.getRetrofit(url_highscores).create(TibiaAPIServer.class);
        Call <ApiHighScores> call = apiServer.getHighScoreInformation(world,category,vocation);
        binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.VISIBLE);
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
                    binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
                }else{
                    Toast.makeText(getApplicationContext(),"No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
                    lista_highscore.clear();
                    adaptador.notifyDataSetChanged();
                    binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ApiHighScores> call, Throwable t) {
                System.out.println("MENSAJE: "+t.getMessage());
                Toast.makeText(getApplicationContext(),"Error de conexión intente mas tarde :)", Toast.LENGTH_SHORT).show();
                binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
                //lista_highscore.clear();
                //adaptador.notifyDataSetChanged();
            }
        });
    }

    public void spinners(){
        Spinners spinners = new Spinners();
        ArrayAdapter<String> adapterVocations, adapterCategorys;
        ArrayList<String> dataVocation = spinners.spinnerVocations(getResources().openRawResource(R.raw.vocations));
        ArrayList<String> dataCategorys= spinners.spinnerCategory(getResources().openRawResource(R.raw.categorias));
        adapterVocations = new ArrayAdapter<>(getApplicationContext(), R.layout.auto_complete, dataVocation);
        adapterCategorys = new ArrayAdapter<>(getApplicationContext(), R.layout.auto_complete, dataCategorys);
        // Crea un nuevo Handler para enviar la actualización de la UI al hilo principal
        new Handler(Looper.getMainLooper()).post(() -> {
            spinnerVocations.setAdapter(adapterVocations);
            spinnerCategorys.setAdapter(adapterCategorys);
        });
    }

    //implementacion de tareas asincronas
    public void Hilos(){
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(()->spinnerWorlds(url));
        executor.execute(()->spinners());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (
                !spinnerWorlds.getText().toString().isEmpty() && !spinnerWorlds.getText().toString().equalsIgnoreCase("Seleccione un mundo")&&
                !spinnerCategorys.getText().toString().isEmpty() && !spinnerCategorys.getText().toString().equalsIgnoreCase("Seleccione una categoria")&&
                !spinnerVocations.getText().toString().isEmpty() && !spinnerVocations.getText().toString().equalsIgnoreCase("Seleccione una vocación")
        ){
            mundo = spinnerWorlds.getText().toString();
            dataHighScores.setMundo(mundo);
            vocacion = spinnerVocations.getText().toString();
            dataHighScores.setVocacion(vocacion);
            categoria = spinnerCategorys.getText().toString();
            dataHighScores.setCategoria(categoria);
            llenarRecyclerViewHighScores(
                    dataHighScores.getMundo(),
                    dataHighScores.getCategoria().replace(" ","").toLowerCase(),
                    dataHighScores.getVocacion());
        }
    }
}