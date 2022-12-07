package com.example.ttools;

import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;



import com.example.ttools.databinding.ActivitySpellsTibiaBinding;

public class Spells_Tibia extends AppCompatActivity {

    private ActivitySpellsTibiaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpellsTibiaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar


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
}