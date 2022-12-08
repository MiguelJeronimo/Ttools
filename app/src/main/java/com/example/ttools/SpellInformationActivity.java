package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.ApiSpellsInformation;
import com.example.ttools.APISERVER.models.SpellsInformation.SpellList.Spell;
import com.example.ttools.APISERVER.models.SpellsInformation.Spells;
import com.example.ttools.APISERVER.models.SpellsInformation.spell_information.Spell_Information;
import com.example.ttools.databinding.ActivitySpellInformationBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpellInformationActivity extends AppCompatActivity {
    Intent intent;
    String id_spell;
    private ActivitySpellInformationBinding binding;
    ImageView imgLogo;
    LinearLayout linearLayoutVocation, linearLayoutProfeciones, linearLayoutCitys,linearLayoutVocacionesPermitidas;
    TextView textViewSpellName,textViewSpellFormula,textViewDescription,textViewGrupo,textViewTipo,textViewDamageType,
            textViewCooldown,textViewSoulPoint,textViewAmount,textViewMana,textViewNivel,textViewPrice,textViewStatus,
            textViewCooldownGroup;
    String url="https://api.tibiadata.com/v3/spell/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpellInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        intent = getIntent();
        id_spell = intent.getStringExtra("ID");
        imgLogo = findViewById(R.id.imageViewGuildLogo);
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
        Retrofit services = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TibiaAPIServer tibiaAPIServer = services.create(TibiaAPIServer.class);
        Call<ApiSpellsInformation> call = tibiaAPIServer.getSpellInformation(id_spell);
        call.enqueue(new Callback<ApiSpellsInformation>() {
            @Override
            public void onResponse(Call<ApiSpellsInformation> call, Response<ApiSpellsInformation> response) {
                ApiSpellsInformation information = response.body();
                Spells spells = information.getSpells();
                Spell spell = spells.getSpell();
                Glide.with(getApplicationContext()).load(spell.getImage_url()).asGif().into(imgLogo);
                textViewSpellName.setText(spell.getName());
                textViewDescription.setText(spell.getDescription());
                //dentro de spell_information en el api
                Spell_Information spell_information = spell.getSpell_information();
                textViewSpellFormula.setText(spell_information.getFormula());
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
                String statusPremium = null;
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

            }

            @Override
            public void onFailure(Call<ApiSpellsInformation> call, Throwable t) {

            }
        });
    }

}