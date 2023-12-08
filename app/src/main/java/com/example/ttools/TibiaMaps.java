package com.example.ttools;

import android.os.Bundle;

import com.example.TibiaTools.utilidades.RedValidator;
import com.example.TibiaTools.utilidades.utilidades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;
import com.example.ttools.databinding.ActivityTibiaMapsBinding;

public class TibiaMaps extends AppCompatActivity {
    RedValidator redValidator = new RedValidator();
    private AppBarConfiguration appBarConfiguration;
    private ActivityTibiaMapsBinding binding;
    utilidades utilidades = new utilidades();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTibiaMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        WebView maps = binding.getRoot().findViewById(R.id.mapsImage);
       if (redValidator.ValidarInternet(getApplication())){
           String mapa = utilidades.TibiaMapps(getResources().openRawResource(R.raw.tibiamaps));
           maps.loadDataWithBaseURL("https://tibiamaps.io/map#32381,32213,7:0", mapa, "text/html", "UTF-8", null);
           WebSettings webSettings = maps.getSettings();
           webSettings.setJavaScriptEnabled(true);
       }else{
           Toast.makeText(getApplicationContext(), "No estas conectado a internet...", Toast.LENGTH_SHORT).show();
           maps.setVisibility(View.GONE);
       }
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

}