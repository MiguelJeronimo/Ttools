package com.example.TibiaTools.View;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.TibiaTools.View.ViewModel.ViewModelHighScore;
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

public class Highscores extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityHighscoresBinding binding;
    DataHighScores dataHighScores = new DataHighScores();
    AutoCompleteTextView spinnerWorlds, spinnerVocations, spinnerCategorys;
    ArrayAdapter<String> adapterWorlds;
    //recycler
    RecyclerView recyclerView;
    AdapterRecyclerViewHighScores adaptador;
    List<ItemsRecyclerViewHighScores> lista_highscore;
    String mundo = null, categoria = null, vocacion = null;
    ViewModelHighScore viewModelHighScore;
    ViewModelProvider viewModelProvider;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHighscoresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        //Aparicion del boton regresar en el action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModelProvider = new ViewModelProvider(this);
        viewModelHighScore = viewModelProvider.get(ViewModelHighScore.class);
        binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.VISIBLE);
        spinnerWorlds = findViewById(R.id.spinner_worldss);
        spinnerVocations = findViewById(R.id.spinner_vocationss);
        spinnerCategorys = findViewById(R.id.spinner_category);
        Hilos();
        spinnerWorlds.setOnItemClickListener(this);
        spinnerVocations.setOnItemClickListener(this);
        spinnerCategorys.setOnItemClickListener(this);

        recyclerView = findViewById(R.id.recycler_highscores);
        lista_highscore = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adaptador = new AdapterRecyclerViewHighScores(lista_highscore);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptador);

        viewModelHighScore.Worlds().observe(this, worlds -> {
            if (worlds != null){
                adapterWorlds = new ArrayAdapter<>(getApplicationContext(), R.layout.auto_complete, worlds);
                spinnerWorlds.setAdapter(adapterWorlds);
                binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
            } else{
                binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
            }
        });
        viewModelHighScore.highScoreList().observe(this, highScores -> {
            lista_highscore.clear();
            String pattern = "#,###.###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setGroupingSize(3);
            if (highScores != null){
                highScores.getHighscore_list().forEach(highscoreList -> {
                    String categorias = highScores.getCategory();
                    String value = null;
                    switch (categorias){
                        case "achievements":
                            value = "Archivements: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "axefighting":
                            value = "Axe Fighting : "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "charmpoints":
                            value = "Charm Points: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "clubfighting":
                            value = "Club Fighting: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "distancefighting":
                            value = "Distance Fighting: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "experience":
                            value = "Experience: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "fishing":
                            value = "Fishing: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "fistfighting":
                            value = "Fist Fighting: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "goshnarstaint":
                            value = "Goshnar's Taint: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "loyaltypoints":
                            value = "Loyalty Points: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "magiclevel":
                            value = "Magic Level: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "shielding":
                            value = "Shielding: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "swordfighting":
                            value = "Sword Fighting: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "dromescore":
                            value = "Drome Score: "+decimalFormat.format(highscoreList.getValue());
                            break;
                        case "bosspoints":
                            value = "Boss Points: "+decimalFormat.format(highscoreList.getValue());
                            break;
                    }

                    lista_highscore.add(new ItemsRecyclerViewHighScores(
                            String.valueOf(highscoreList.getRank()),
                            highscoreList.getName(),
                            highscoreList.getVocation(),
                            highscoreList.getWorld(),
                            String.valueOf(highscoreList.getLevel()),
                            value,
                            highscoreList.getTitle()
                    ));
                });

            } else {
                System.out.println("No hay respuesta del servidor");
                Toast.makeText(getApplicationContext(),"No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
            }
            binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.GONE);
            adaptador.notifyDataSetChanged();
        });
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
        executor.execute(this::spinners);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (
                !spinnerWorlds.getText().toString().isEmpty() && !spinnerWorlds.getText().toString().equalsIgnoreCase("Seleccione un mundo")&&
                !spinnerCategorys.getText().toString().isEmpty() && !spinnerCategorys.getText().toString().equalsIgnoreCase("Seleccione una categoria")&&
                !spinnerVocations.getText().toString().isEmpty() && !spinnerVocations.getText().toString().equalsIgnoreCase("Seleccione una vocación")
        ){
            binding.getRoot().findViewById(R.id.carga_highscores).setVisibility(View.VISIBLE);
            mundo = spinnerWorlds.getText().toString();
            dataHighScores.setMundo(mundo);
            vocacion = spinnerVocations.getText().toString();
            dataHighScores.setVocacion(vocacion);
            categoria = spinnerCategorys.getText().toString();
            dataHighScores.setCategoria(categoria);
            viewModelHighScore.setHighScores(
                    dataHighScores.getMundo(),
                    dataHighScores.getCategoria().replace(" ","").toLowerCase(),
                    dataHighScores.getVocacion());
        } else {
            lista_highscore.clear();
            adaptador.notifyDataSetChanged();
        }
    }
}