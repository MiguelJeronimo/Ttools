package com.example.TibiaTools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiSpellsInformation;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.Spell;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.Spells;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.spell_information.Spell_Information;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.spell_information.rune_information.rune_information;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivitySpellInformationBinding;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpellInformationActivity extends AppCompatActivity {
    Intent intent;
    String id_spell;
    private ActivitySpellInformationBinding binding;
    ImageView imgLogo;
    LinearLayout linearLayoutVocation, linearLayoutProfeciones, linearLayoutCitys,linearLayoutVocacionesPermitidas;
    TextView textViewSpellName,textViewSpellFormula,textViewDescription,textViewGrupo,textViewTipo,textViewDamageType,
            textViewCooldown,textViewSoulPoint,textViewAmount,textViewMana,textViewNivel,textViewPrice,textViewStatus,
            textViewCooldownGroup;
    String url="https://api.tibiadata.com/v4/spell/";
    InstanciaRetrofit services = new InstanciaRetrofit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpellInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        intent = getIntent();
        id_spell = intent.getStringExtra("ID");
        imgLogo = findViewById(R.id.imageViewSpellLogo);
        linearLayoutVocation = findViewById(R.id.linearLayoutVocation);
        linearLayoutProfeciones = findViewById(R.id.linearLayoutProfeciones);
        linearLayoutCitys = findViewById(R.id.linearLayoutCitys);
        linearLayoutVocacionesPermitidas = findViewById(R.id.linearLayoutVocacionesPermitidas);
        textViewSpellName = findViewById(R.id.textViewSpellName);
        textViewSpellFormula = findViewById(R.id.textViewSpellFormula);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewGrupo = findViewById(R.id.textViewGrupo);
        textViewTipo = findViewById(R.id.textViewTipo);
        textViewDamageType = findViewById(R.id.textViewDamageType);
        textViewCooldown = findViewById(R.id.textViewCooldown);
        textViewSoulPoint = findViewById(R.id.textViewSoulPoint);
        textViewAmount = findViewById(R.id.textViewAmount);
        textViewMana = findViewById(R.id.textViewMana);
        textViewNivel = findViewById(R.id.textViewNivel);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewCooldownGroup = findViewById(R.id.textViewCooldownGroup);
        infoSpells(url,id_spell);
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

    public void infoSpells(String url, String id_spell){
        linearLayoutCitys = findViewById(R.id.linearLayoutCitys);
        linearLayoutProfeciones = findViewById(R.id.linearLayoutProfeciones);
        linearLayoutVocation = findViewById(R.id.linearLayoutVocation);
        linearLayoutVocacionesPermitidas = findViewById(R.id.linearLayoutVocacionesPermitidas);
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiSpellsInformation> call = tibiaAPIServer.getSpellInformation(id_spell);
        call.enqueue(new Callback<ApiSpellsInformation>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ApiSpellsInformation> call, Response<ApiSpellsInformation> response) {
                if (response.isSuccessful()){
                    ApiSpellsInformation information = response.body();
                    Spell spell = information.getSpells();
                    Glide.with(getApplicationContext()).load(spell.getImage_url()).into(imgLogo);
                    textViewSpellName.setText(spell.getName());
                    textViewDescription.setText(spell.getDescription());
                    //dentro de spell_information en el api
                    Spell_Information spell_information = spell.getSpell_information();
                    textViewSpellFormula.setText(spell_information.getFormula());
                    binding.getRoot().findViewById(R.id.CardSpellFormula).setVisibility(View.VISIBLE);
                    //Validar el tipo de grupo al que pertenece
                    String groupStatus=null;
                    if (spell_information.isGroup_attack()){
                        groupStatus = "Attack";
                    }
                    if (spell_information.isGroup_healing()){
                        groupStatus = "Healing";
                    }
                    if (spell_information.isGroup_support()){
                        groupStatus = "Support";
                    }
                    textViewGrupo.setText(groupStatus);
                    //Validar a que tipo pertenece
                    String statusType = null;
                    if (spell_information.isType_instant()){
                        statusType = "Instant";
                    }
                    if (spell_information.isType_rune()){
                        statusType = "Rune";
                    }
                    textViewTipo.setText(statusType);
                    //validar premium
                    String statusPremium;
                    if (spell_information.isPremium_only()){
                        statusPremium = "Premium Only";
                    } else{
                        statusPremium = "Free";
                    }
                    textViewStatus.setText(statusPremium);
                    textViewDamageType.setText(spell_information.getDamage_type());
                    textViewCooldown.setText(String.valueOf(spell_information.getCooldown_alone()));
                    textViewCooldownGroup.setText(String.valueOf(spell_information.getCooldown_group()));
                    textViewSoulPoint.setText(String.valueOf(spell_information.getSoul_points()));
                    textViewAmount.setText(String.valueOf(spell_information.getAmount()));
                    textViewNivel.setText(String.valueOf(spell_information.getLevel()));
                    textViewMana.setText(String.valueOf(spell_information.getMana()));
                    if (spell_information.getVocation() != null){
                        TextView txtVocation = new TextView(SpellInformationActivity.this);
                        txtVocation.setText("Vocation");
                        txtVocation.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                        txtVocation.setTextSize(15);
                        txtVocation.setTypeface(null, Typeface.BOLD);
                        txtVocation.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        txtVocation.setGravity(Gravity.CENTER);
                        linearLayoutVocation.addView(txtVocation);
                        for (String vocations:spell_information.getVocation()) {
                            txtVocation = new TextView(SpellInformationActivity.this);
                            txtVocation.setText(vocations);
                            txtVocation.setGravity(Gravity.CENTER);
                            txtVocation.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_dark_error
                            ));
                            //txtVocation.setTextColor(Color.parseColor("#CE93D8"));
                            txtVocation.setTypeface(null, Typeface.ITALIC);
                            txtVocation.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayoutVocation.addView(txtVocation);
                        }
                        binding.getRoot().findViewById(R.id.CardSpellGeneral).setVisibility(View.VISIBLE);
                    }
                    if (spell_information.getCity()!=null){
                        TextView txtCitys = new TextView(SpellInformationActivity.this);
                        txtCitys.setText("Citys");
                        txtCitys.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                        txtCitys.setTextSize(15);
                        txtCitys.setTypeface(null, Typeface.BOLD);
                        txtCitys.setGravity(Gravity.CENTER);
                        txtCitys.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutCitys.addView(txtCitys);
                        for (String citys:spell_information.getCity()) {
                            txtCitys = new TextView(SpellInformationActivity.this);
                            txtCitys.setText(citys);
                            txtCitys.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_dark_error));
                            //txtCitys.setTextColor(Color.parseColor("#CE93D8"));
                            txtCitys.setTypeface(null, Typeface.ITALIC);
                            txtCitys.setGravity(Gravity.CENTER);
                            txtCitys.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayoutCitys.addView(txtCitys);
                        }
                    }

                    rune_information rune_information = spell.getRune_information();
                    if (rune_information.getVocation()!=null){
                        TextView txtVocacionesPermitidas = new TextView(SpellInformationActivity.this);
                        txtVocacionesPermitidas.setText("Vocations permited");
                        txtVocacionesPermitidas.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                        txtVocacionesPermitidas.setTextSize(15);
                        txtVocacionesPermitidas.setTypeface(null, Typeface.BOLD);
                        txtVocacionesPermitidas.setGravity(Gravity.CENTER);
                        txtVocacionesPermitidas.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutVocacionesPermitidas.addView(txtVocacionesPermitidas);
                        for (String vocaciones_permitidas:rune_information.getVocation()) {
                            txtVocacionesPermitidas = new TextView(SpellInformationActivity.this);
                            txtVocacionesPermitidas.setText(vocaciones_permitidas);
                            txtVocacionesPermitidas.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_dark_error));
                            //txtVocacionesPermitidas.setTextColor(Color.parseColor("#CE93D8"));
                            txtVocacionesPermitidas.setTypeface(null, Typeface.ITALIC);
                            txtVocacionesPermitidas.setGravity(Gravity.CENTER);
                            txtVocacionesPermitidas.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayoutVocacionesPermitidas.addView(txtVocacionesPermitidas);
                        }
                    }
                    binding.getRoot().findViewById(R.id.Card_Listas).setVisibility(View.VISIBLE);
                    binding.getRoot().findViewById(R.id.carga_spell_information).setVisibility(View.GONE);
                } else{
                    Toast.makeText(getApplicationContext(),"No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiSpellsInformation> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de conexión, intente mas tarde...", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }

}