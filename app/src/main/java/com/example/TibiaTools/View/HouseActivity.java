package com.example.TibiaTools.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.TibiaTools.View.ViewModel.ViewModelHouses;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewHouses;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewHouses;
import com.example.TibiaTools.utilidades.DataHighScores;
import com.example.TibiaTools.utilidades.Spinners;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityHouseBinding;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HouseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityHouseBinding binding;
    String mundo;
    DataHighScores dataHighScores = new DataHighScores();
    AutoCompleteTextView spinnerWorlds, spinnerCitys;
    ArrayAdapter<String> adapterWorlds,adapterCitys;
    //Recyclerview
    RecyclerView recyclerView;
    AdapterRecyclerViewHouses adapterRecyclerViewHouses;
    ArrayList<ItemsRecyclerViewHouses> list_houses = new ArrayList<>();
    ViewModelProvider viewModelProvider;
    ViewModelHouses viewModelHouses;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHouseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_houses);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterRecyclerViewHouses = new AdapterRecyclerViewHouses(list_houses);
        recyclerView.setAdapter(adapterRecyclerViewHouses);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.VISIBLE);
        viewModelProvider = new ViewModelProvider(this);
        viewModelHouses = viewModelProvider.get(ViewModelHouses.class);
        spinnerWorlds = findViewById(R.id.spinner_mundos);
        spinnerCitys = findViewById(R.id.spinner_citys);
        spinnerWorlds.setOnItemClickListener(this);
        spinnerCitys.setOnItemClickListener(this);
        Threads();
        viewModelHouses.Worlds().observe(this, worlds -> {
            if (worlds != null){
                adapterWorlds = new ArrayAdapter<>(getApplicationContext(), R.layout.auto_complete, worlds);
                spinnerWorlds.setAdapter(adapterWorlds);
                binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
            } else{
                Toast.makeText(getApplicationContext(),"Fallo en la opcion mundos.. Intente mas tarde", Toast.LENGTH_SHORT).show();
                binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
            }
        });
        viewModelHouses.houses().observe(this, houses -> {
            list_houses.clear();
            if (houses != null){
                mundo = houses.getWorld();
                houses.getHouse_list().forEach(houseList -> {
                    String rented;
                    if (houseList.isRented()){
                        rented = "Ocupada";
                    } else{
                        rented = "Desocupada";
                    }
                    list_houses.add(new ItemsRecyclerViewHouses(
                            houseList.getName(),
                            String.valueOf(houseList.getSize()),
                            String.valueOf(houseList.getRent()),
                            rented,
                            String.valueOf(houseList.getHouse_id())
                    ));
                });
                //validar que el array de guildhall es diferente de null
                if (houses.getGuildhall_list()!= null) {
                    houses.getGuildhall_list().forEach(guildhallList -> {
                        String rented;
                        if (guildhallList.isRented()){
                            rented = "Ocupada";
                        } else{
                            rented = "Desocupada";
                        }
                        list_houses.add(new ItemsRecyclerViewHouses(
                                guildhallList.getName(),
                                String.valueOf(guildhallList.getSize()),
                                String.valueOf(guildhallList.getRent()),
                                rented,
                                String.valueOf(guildhallList.getHouse_id())
                        ));
                    });
                }
            }
            binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
            adapterRecyclerViewHouses.notifyDataSetChanged();
        });

        adapterRecyclerViewHouses.setOnClickListener(view -> {
            //Enviando el id de la casa a al activity HousesInformation
            String id_house = list_houses.get(
                    recyclerView.getChildAdapterPosition(view)
            ).getHouseId();
            Intent intent = new Intent(HouseActivity.this, HousesInformation.class);
            intent.putExtra("ID",id_house);
            intent.putExtra("mundo", mundo);
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
        if (id == android.R.id.home) { //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
            finish();// finalizamos la actividad
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void spinnersCity(){
        Spinners spinners = new Spinners();
        adapterCitys = new ArrayAdapter<>(getApplicationContext(), R.layout.auto_complete, spinners.LeerDataCitys(getResources().openRawResource(R.raw.data)));
        new Handler(Looper.getMainLooper()).post(()->{
            spinnerCitys.setAdapter(adapterCitys);
        });
    }

    //llamadas asincornas con hilos
    public void Threads(){
        Executor executor = Executors.newFixedThreadPool(1);
        executor.execute(this::spinnersCity);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!spinnerCitys.getText().toString().isEmpty() && !spinnerWorlds.getText().toString().isEmpty()){
            if (!spinnerCitys.getText().toString().equalsIgnoreCase("Seleccione una ciudad") && !spinnerWorlds.getText().toString().equalsIgnoreCase("Seleccione")){
                String mundo, ciudad;
                mundo = spinnerWorlds.getText().toString();
                ciudad = spinnerCitys.getText().toString();
                dataHighScores.setMundo(mundo);
                dataHighScores.setCiudad(ciudad);
                viewModelHouses.setHouses(dataHighScores.getMundo(),dataHighScores.getCiudad());
            }
            else {
                list_houses.clear();
                adapterRecyclerViewHouses.notifyDataSetChanged();
            }
        }
    }
}