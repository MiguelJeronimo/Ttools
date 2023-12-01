package com.example.TibiaTools;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiSpells;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.SpellsList;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.Spells;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewSpells;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewSpells;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivitySpellsTibiaBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Spells_Tibia extends AppCompatActivity {

    private ActivitySpellsTibiaBinding binding;
    String url = "https://api.tibiadata.com/v3/";
    //RecyclerView
    RecyclerView recyclerView;
    AdapterRecyclerViewSpells adapter;
    List<ItemsRecyclerViewSpells> itemsRecyclerViewSpellsList;
    InstanciaRetrofit services = new InstanciaRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpellsTibiaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        llenarRecyclerViewSpells(url);
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

    public void llenarRecyclerViewSpells(String url){
        recyclerView = findViewById(R.id.recyclerSpells);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerViewSpellsList = new ArrayList<>();
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call <ApiSpells> call = tibiaAPIServer.getSpells();
        call.enqueue(new Callback<ApiSpells>() {
            @Override
            public void onResponse(Call<ApiSpells> call, Response<ApiSpells> response) {
                if (response.isSuccessful()){
                    ApiSpells apiSpells = response.body();
                    Spells spells = apiSpells.getSpells();
                    String stateGroup=null;
                    String stateType = null;
                    String statePremium = null;
                    for (SpellsList spellList:spells.getSpells_list()) {

                        if (spellList.isGroup_support()){
                            stateGroup = "Support";
                        }
                        if (spellList.isGroup_healing()){
                            stateGroup = "Healing";
                        }
                        if(spellList.isGroup_attack()){
                            stateGroup = "Attack";
                        }
                        if (spellList.isType_instant()){
                            stateType = "Instant";
                        }
                        if (spellList.isType_rune()){
                            stateType = "Rune";
                        }
                        if (spellList.isPremium_only()){
                            statePremium = "Premium Only";
                        } else{
                            statePremium = "Free";
                        }

                        itemsRecyclerViewSpellsList.add(new ItemsRecyclerViewSpells(
                                spellList.getName(),
                                spellList.getFormula(),
                                String.valueOf(spellList.getMana()),
                                String.valueOf(spellList.getPrice()),
                                stateType,
                                stateGroup,
                                spellList.getSpell_id(),
                                statePremium,
                                String.valueOf(spellList.getLevel())
                        ));
                    }
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new AdapterRecyclerViewSpells(itemsRecyclerViewSpellsList);
                    adapter.notifyDataSetChanged();
                    recyclerView.hasFixedSize();
                    recyclerView.setAdapter(adapter);
                    binding.getRoot().findViewById(R.id.carga_spells).setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String id = itemsRecyclerViewSpellsList.get(recyclerView.getChildAdapterPosition(view)).getSpellId();
                            //para el caso de apprenticestrike, para quietar la 's y el espacio
                            //id = id.replace("'s ","");
                            String id1 = id.replace("'s","s");
                            String id2 = id1;
                            //quitar los espacios en blanco y unir los caracteres
                            String id3 = id2.replace(" ","");
                            //convertir ese id en minusculas
                            String id_minusculas = null;
                             id_minusculas = id3.toLowerCase();
                            String ids= id_minusculas;
                            if (ids.equals("apprenticesstrike")){
                                id = id.replace("'s ","");
                                id_minusculas = id.toLowerCase();
                            }
                            Intent intent = new Intent(Spells_Tibia.this,SpellInformationActivity.class);
                            intent.putExtra("ID",id_minusculas);
                            startActivity(intent);
                        }
                    });
                }else{
                    binding.getRoot().findViewById(R.id.carga_spells).setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiSpells> call, Throwable t) {
                System.out.println(t.getMessage());
                binding.getRoot().findViewById(R.id.carga_spells).setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Error de conexión intente mas tarde :)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}