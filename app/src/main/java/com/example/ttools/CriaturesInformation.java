package com.example.ttools;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.APICriaturesInformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.ttools.APISERVER.models.CriatureInformation.Creature;
import com.example.ttools.Operaciones.InstanciaRetrofit;
import com.example.ttools.databinding.ActivityCriaturesInformationBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CriaturesInformation extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCriaturesInformationBinding binding;
    String url = "https://api.tibiadata.com/v3/creature/";
    String raceCriatures;
    TextView creatureName, creatureDescription, creatureBehaviour, creatureHealth, creatureExp;
    ImageView creatureImage;
    LinearLayout linearLayout, linearLayoutInmune, linearLayoutStrong, linearLayoutWeakness;
    InstanciaRetrofit services = new InstanciaRetrofit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCriaturesInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        Intent intent = getIntent();
        raceCriatures = intent.getStringExtra("raceCriatures");
        creatureName = binding.getRoot().findViewById(R.id.id_criature_name);
        creatureDescription = binding.getRoot().findViewById(R.id.descripcion_criature);
        creatureBehaviour = binding.getRoot().findViewById(R.id.behavior_criature);
        creatureHealth = binding.getRoot().findViewById(R.id.id_health);
        creatureExp = binding.getRoot().findViewById(R.id.id_experience_criature);
        creatureImage = binding.getRoot().findViewById(R.id.imageViewCreature);
        criatureDataAPI(url, raceCriatures);

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

    public void criatureDataAPI(String url, String raceCriatures) {
        linearLayout = binding.getRoot().findViewById(R.id.linearLayoutLoot);
        linearLayoutInmune = binding.getRoot().findViewById(R.id.linearLayoutInmune);
        linearLayoutStrong = binding.getRoot().findViewById(R.id.linearLayoutStrong);
        linearLayoutWeakness = binding.getRoot().findViewById(R.id.linearLayoutWeakness);
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<APICriaturesInformation> call = tibiaAPIServer.getCriatureInformation(raceCriatures);
        call.enqueue(new Callback<APICriaturesInformation>() {
            @Override
            public void onResponse(Call<APICriaturesInformation> call, Response<APICriaturesInformation> response) {
                if (response.isSuccessful()) {
                    APICriaturesInformation apiCriaturesInformation = response.body();
                    Creature criature = apiCriaturesInformation.getCreature();
                    creatureName.setText(criature.getName());
                    creatureDescription.setText(criature.getDescription());
                    creatureBehaviour.setText(criature.getBehaviour());
                    creatureHealth.setText("Hit Point: "+criature.getExperience_points());
                    creatureHealth.setTextColor(Color.parseColor("#4CAF50"));
                    creatureExp.setTextColor(Color.parseColor("#FFAB00"));
                    creatureExp.setText("Experience Points: "+criature.getHitpoints());
                    String imageCreature = criature.getImage_url();
                    Picasso.get().load(imageCreature).into(creatureImage);
                    for (int i = 0; i < criature.getLoot_list().size(); i++) {
                        TextView textViewLoot = new TextView(CriaturesInformation.this);
                        textViewLoot.setText("-"+criature.getLoot_list().get(i));
                        textViewLoot.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.leyenda));
                        textViewLoot.setTextSize(20);
                        textViewLoot.setTextColor(Color.parseColor("#CE93D8"));
                        textViewLoot.setTypeface(null, Typeface.ITALIC);
                        textViewLoot.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayout.addView(textViewLoot);
                    }
                    if (criature.getImmune()!=null){
                        for(int i = 0; i < criature.getImmune().size(); i++) {
                            TextView textViewImmune = new TextView(CriaturesInformation.this);
                            textViewImmune.setText("-"+criature.getImmune().get(i));
                            textViewImmune.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.leyenda));
                            textViewImmune.setTextSize(20);
                            textViewImmune.setTextColor(Color.parseColor("#CE93D8"));
                            textViewImmune.setTypeface(null, Typeface.ITALIC);
                            textViewImmune.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayoutInmune.addView(textViewImmune);
                        }
                    }
                    if (criature.getStrong()!=null){
                        for(int i = 0; i < criature.getStrong().size(); i++) {
                            TextView textViewStrong = new TextView(CriaturesInformation.this);
                            textViewStrong.setText("-"+criature.getStrong().get(i));
                            textViewStrong.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.leyenda));
                            textViewStrong.setTextSize(20);
                            textViewStrong.setTextColor(Color.parseColor("#CE93D8"));
                            textViewStrong.setTypeface(null, Typeface.ITALIC);
                            textViewStrong.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayoutStrong.addView(textViewStrong);
                        }
                    }
                    if (criature.getWeakness()!=null){
                        for(int i = 0; i < criature.getWeakness().size(); i++) {
                            TextView textViewWeakness = new TextView(CriaturesInformation.this);
                            textViewWeakness.setText("-"+criature.getWeakness().get(i));
                            textViewWeakness.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.leyenda));
                            textViewWeakness.setTextSize(20);
                            textViewWeakness.setTextColor(Color.parseColor("#CE93D8"));
                            textViewWeakness.setTypeface(null, Typeface.ITALIC);
                            textViewWeakness.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayoutWeakness.addView(textViewWeakness);
                        }
                    }



                } else {
                    Toast.makeText(CriaturesInformation.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APICriaturesInformation> call, Throwable t) {

            }
        });
    }
}