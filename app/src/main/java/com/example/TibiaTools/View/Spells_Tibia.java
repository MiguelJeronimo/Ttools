package com.example.TibiaTools.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.TibiaTools.View.ViewModel.ViewModelSpells;
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
    //RecyclerView
    RecyclerView recyclerView;
    AdapterRecyclerViewSpells adapter;
    List<ItemsRecyclerViewSpells> itemsRecyclerViewSpellsList = new ArrayList<>();;
    ViewModelProvider viewModelProvider;
    ViewModelSpells viewModelSpells;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpellsTibiaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        recyclerView = findViewById(R.id.recyclerSpells);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AdapterRecyclerViewSpells(itemsRecyclerViewSpellsList);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
        viewModelProvider = new ViewModelProvider(this);
        viewModelSpells = viewModelProvider.get(ViewModelSpells.class);
        viewModelSpells.spells().observe(this, spells -> {
            if (spells != null){
                String stateGroup=null;
                String stateType = null;
                String statePremium = null;
                if (spells.getSpells_list() != null){
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
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(),"No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
            }
            binding.getRoot().findViewById(R.id.carga_spells).setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        });
        adapter.setOnClickListener(view -> {
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
            Intent intent = new Intent(Spells_Tibia.this, SpellInformationActivity.class);
            intent.putExtra("ID",id_minusculas);
            startActivity(intent);
        });
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