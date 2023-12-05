package com.example.ttools;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.utilidades.utilidades;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ttools.databinding.ActivityTibiaMapsBinding;

public class TibiaMaps extends AppCompatActivity {

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
        //ImageView imageMaps = binding.getRoot().findViewById(R.id.mapsImage);
        //String urlmap = "https://tibiamaps.github.io/tibia-map-data/floor-07-map.png";
        //Glide.with(getApplicationContext()).load(urlmap).into(imageMaps);
        WebView maps = binding.getRoot().findViewById(R.id.mapsImage);
        String mapa = utilidades.TibiaMapps(getResources().openRawResource(R.raw.tibiamaps));
        //maps.loadUrl("https://tibiamaps.io/map#32381,32213,7:0");
        //maps.loadData(mapa, "text/html", "UTF-8");
        maps.loadDataWithBaseURL("https://tibiamaps.io/map#32381,32213,7:0", mapa, "text/html", "UTF-8", null);
        WebSettings webSettings = maps.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
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
        if (id == android.R.id.home) { //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
            finish();// finalizamos la actividad
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}