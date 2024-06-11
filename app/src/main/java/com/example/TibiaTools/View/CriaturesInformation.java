package com.example.TibiaTools.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.TibiaTools.View.ViewModel.ViewModelCreature;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityCriaturesInformationBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class CriaturesInformation extends AppCompatActivity {

    private ActivityCriaturesInformationBinding binding;
    String raceCreature;
    TextView creatureName, creatureDescription, creatureBehaviour, creatureHealth, creatureExp;
    ImageView creatureImage;
    LinearLayout linearLayout, linearLayoutInmune, linearLayoutStrong, linearLayoutWeakness;
    ViewModelProvider viewModelProvider;
    ViewModelCreature viewModelCreature;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCriaturesInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        Intent intent = getIntent();
        raceCreature = intent.getStringExtra("raceCriatures");
        creatureName = binding.getRoot().findViewById(R.id.id_criature_name);
        creatureDescription = binding.getRoot().findViewById(R.id.descripcion_criature);
        creatureBehaviour = binding.getRoot().findViewById(R.id.behavior_criature);
        creatureHealth = binding.getRoot().findViewById(R.id.id_health);
        creatureExp = binding.getRoot().findViewById(R.id.id_experience_criature);
        creatureImage = binding.getRoot().findViewById(R.id.imageViewCreature);
        viewModelProvider = new ViewModelProvider(this);
        viewModelCreature = viewModelProvider.get(ViewModelCreature.class);
        viewModelCreature.setCreature(raceCreature);
        viewModelCreature.creature().observe(this, creature -> {
            linearLayout = binding.getRoot().findViewById(R.id.linearLayoutLoot);
            linearLayoutInmune = binding.getRoot().findViewById(R.id.linearLayoutInmune);
            linearLayoutStrong = binding.getRoot().findViewById(R.id.linearLayoutStrong);
            linearLayoutWeakness = binding.getRoot().findViewById(R.id.linearLayoutWeakness);
            if (creature != null) {
                creatureName.setText(creature.getName());
                creatureName.setVisibility(View.VISIBLE);
                creatureDescription.setText(creature.getDescription());
                creatureBehaviour.setText(creature.getBehaviour());
                creatureHealth.setText("Hit Point: "+creature.getExperience_points());
                creatureHealth.setTextColor(Color.parseColor("#4CAF50"));
                creatureHealth.setVisibility(View.VISIBLE);
                creatureExp.setTextColor(Color.parseColor("#FFAB00"));
                creatureExp.setText("Experience Points: "+creature.getHitpoints());
                creatureExp.setVisibility(View.VISIBLE);
                String imageCreature = creature.getImage_url();
                Picasso.get().load(imageCreature).into(creatureImage);
                creatureImage.setVisibility(View.VISIBLE);
                binding.getRoot().findViewById(R.id.cardDescriptionCreature).setVisibility(View.VISIBLE);
                binding.getRoot().findViewById(R.id.cardBehavior).setVisibility(View.VISIBLE);
                if (creature.getLoot_list() != null){
                    creature.getLoot_list().forEach(loot->{
                        TextView textViewLoot = new TextView(CriaturesInformation.this);
                        textViewLoot.setText("- "+loot);
                        textViewLoot.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                        textViewLoot.setTextColor(Color.parseColor("#CE93D8"));
                        textViewLoot.setTypeface(null, Typeface.ITALIC);
                        textViewLoot.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayout.addView(textViewLoot);
                        binding.getRoot().findViewById(R.id.CardLootList).setVisibility(View.VISIBLE);
                    });
                }
                if (creature.getImmune()!=null){
                    creature.getImmune().forEach(immune->{
                        TextView textViewImmune = new TextView(CriaturesInformation.this);
                        textViewImmune.setText("- "+immune);
                        textViewImmune.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                        textViewImmune.setTextColor(Color.parseColor("#CE93D8"));
                        textViewImmune.setTypeface(null, Typeface.ITALIC);
                        textViewImmune.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutInmune.addView(textViewImmune);
                        binding.getRoot().findViewById(R.id.CardImmune).setVisibility(View.VISIBLE);
                    });
                }
                if (creature.getStrong()!=null){
                    for(int i = 0; i < creature.getStrong().size(); i++) {
                        TextView textViewStrong = new TextView(CriaturesInformation.this);
                        textViewStrong.setText("- "+creature.getStrong().get(i));
                        textViewStrong.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                        textViewStrong.setTextColor(Color.parseColor("#CE93D8"));
                        textViewStrong.setTypeface(null, Typeface.ITALIC);
                        textViewStrong.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutStrong.addView(textViewStrong);
                        binding.getRoot().findViewById(R.id.CardStrong).setVisibility(View.VISIBLE);
                    }
                }
                if (creature.getWeakness()!=null){
                    creature.getWeakness().forEach(weakness->{
                        TextView textViewWeakness = new TextView(CriaturesInformation.this);
                        textViewWeakness.setText("- "+weakness);
                        textViewWeakness.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                        textViewWeakness.setTextColor(Color.parseColor("#CE93D8"));
                        textViewWeakness.setTypeface(null, Typeface.ITALIC);
                        textViewWeakness.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutWeakness.addView(textViewWeakness);
                        binding.getRoot().findViewById(R.id.CardWeakness).setVisibility(View.VISIBLE);
                    });
                }
            } else {
                Toast.makeText(CriaturesInformation.this, "Error", Toast.LENGTH_SHORT).show();
            }
            binding.getRoot().findViewById(R.id.carga_creature_information).setVisibility(View.GONE);
        });
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