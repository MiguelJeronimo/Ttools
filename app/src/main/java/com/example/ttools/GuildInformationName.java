package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ttools.databinding.ActivityGuildInformationNameBinding;

public class GuildInformationName extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityGuildInformationNameBinding binding;
    Intent intent;
    String guildName;
    ImageView imageViewGuildLogo;
    TextView textViewGuildName,textViewDescription,textViewInWar,textViewOnline,
             textViewNombre,textViewMundo,textViewPiad,textViewFounded,textViewActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildInformationNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        //respuesta de la pantalla anterior
        intent = getIntent();
        guildName = intent.getStringExtra("nameGuild");
        imageViewGuildLogo = binding.getRoot().findViewById(R.id.imageViewLogo);
        textViewGuildName = binding.getRoot().findViewById(R.id.textViewGuildName);
        textViewDescription = binding.getRoot().findViewById(R.id.textViewDescription);
        textViewInWar = binding.getRoot().findViewById(R.id.textViewInWar);
        textViewOnline = binding.getRoot().findViewById(R.id.textViewOnline);
        textViewNombre = binding.getRoot().findViewById(R.id.textViewNombre);
        textViewMundo = binding.getRoot().findViewById(R.id.textViewMundo);
        textViewPiad = binding.getRoot().findViewById(R.id.textViewPiad);
        textViewFounded = binding.getRoot().findViewById(R.id.textViewFounded);
        textViewActive = binding.getRoot().findViewById(R.id.textViewActive);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
            finish();// finalizamos la actividad
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}