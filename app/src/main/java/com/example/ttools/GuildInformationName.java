package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildInformationNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        //respuesta de la pantalla anterior
        intent = getIntent();
        guildName = intent.getStringExtra("nameGuild");
        Toast.makeText(this,guildName,Toast.LENGTH_SHORT).show();
    }

}