package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ttools.databinding.ActivitySpellInformationBinding;

public class SpellInformationActivity extends AppCompatActivity {
    Intent intent;
    String id_spell;
    private ActivitySpellInformationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpellInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        intent = getIntent();
        id_spell = intent.getStringExtra("ID");
        Toast.makeText(this, "El id es: "+id_spell, Toast.LENGTH_SHORT).show();
    }

}