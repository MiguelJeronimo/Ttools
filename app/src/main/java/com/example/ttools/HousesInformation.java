package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ttools.databinding.ActivityHousesInformationBinding;

public class HousesInformation extends AppCompatActivity {
    Intent intent;
    String id_house, mundo;
    String url = "https://api.tibiadata.com/v3/";
    ImageView imgCasa;
    private ActivityHousesInformationBinding binding;

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

    }
}