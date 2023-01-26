package com.example.TibiaTools;

import android.content.Intent;
import android.os.Bundle;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiHouses;
import com.example.TibiaTools.APISERVER.models.Houses.Houses;
import com.example.TibiaTools.APISERVER.models.Houses.house_list.GuildhallList;
import com.example.TibiaTools.APISERVER.models.Houses.house_list.HouseList;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.RegularWorlds;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewHouses;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewHouses;
import com.example.TibiaTools.utilidades.DataHighScores;
import com.example.TibiaTools.utilidades.Spinners;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityHouseBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityHouseBinding binding;
    String url = "https://api.tibiadata.com/v3/";
    String mundo;
    DataHighScores dataHighScores = new DataHighScores();
    Spinner spinnerWorlds, spinnerCitys;
    ArrayAdapter<String> adapterWorlds,adapterCitys;
    //Recyclerview
    RecyclerView recyclerView;
    AdapterRecyclerViewHouses adapterRecyclerViewHouses;
    ArrayList<ItemsRecyclerViewHouses> list_houses;
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHouseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        spinnerWorlds = findViewById(R.id.spinner_mundos);
        spinnerCitys = findViewById(R.id.spinner_citys);
        spinnerWorlds.setOnItemSelectedListener(this);
        spinnerCitys.setOnItemSelectedListener(this);
        Hilos();
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
        adapterCitys = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_text_style, spinners.LeerDataCitys(getResources().openRawResource(R.raw.data)));
        spinnerCitys.setAdapter(adapterCitys);
    }

    public void spinnerWorlds(String url){
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url).create(TibiaAPIServer.class);
        Call<DataWords> call = tibiaAPIServer.getWorlds();
        ArrayList<String> arrayWorlds = new ArrayList<>();
        call.enqueue(new Callback<DataWords>() {
            @Override
            public void onResponse(@NonNull Call<DataWords> call, @NonNull Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    Worlds worlds = dataWords.getWorlds();
                    //foreach en java
                    for (RegularWorlds mundos: worlds.getRegular_worlds()) {
                        arrayWorlds.add(mundos.getName());
                    }
                    adapterWorlds = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_text_style,arrayWorlds);
                    spinnerWorlds.setAdapter(adapterWorlds);
                }
            }

            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void Casas(String url, String World, String Town){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_houses);
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiHouses> call = tibiaAPIServer.getHousesInformation(World, Town);
        call.enqueue(new Callback<ApiHouses>() {
            @Override
            public void onResponse(Call<ApiHouses> call, Response<ApiHouses> response) {
                if (response.isSuccessful()){
                    ApiHouses apiHouses = response.body();
                    Houses houses = apiHouses.getHouses();
                    mundo = houses.getWorld();
                    list_houses = new ArrayList<>();
                    String rented;
                    for (HouseList houseList: houses.getHouse_list()) {
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
                    }
                    //validar que el array de guildhall es diferente de null
                    if (houses.getGuildhall_list()!=null){
                        for (GuildhallList guildhallList: houses.getGuildhall_list()) {
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
                        }
                    }

                }
                recyclerView.setHasFixedSize(true);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapterRecyclerViewHouses = new AdapterRecyclerViewHouses(list_houses);
                recyclerView.setAdapter(adapterRecyclerViewHouses);
                adapterRecyclerViewHouses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Enviando el id de la casa a al activity HousesInformation
                        String id_house = list_houses.get(recyclerView.getChildAdapterPosition(view)).getHouseId();
                        Intent intent = new Intent(HouseActivity.this,HousesInformation.class);
                        intent.putExtra("ID",id_house);
                        intent.putExtra("mundo", mundo);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ApiHouses> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String mundo, ciudad;
        if (adapterView.getId() == R.id.spinner_mundos){
            mundo = (String) spinnerWorlds.getItemAtPosition(i);
            dataHighScores.setMundo(mundo);

        }
        if (adapterView.getId() == R.id.spinner_citys){
            ciudad = (String) spinnerCitys.getItemAtPosition(i);
            dataHighScores.setCiudad(ciudad);
        }
        Casas(url,dataHighScores.getMundo(),dataHighScores.getCiudad());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //llamadas asincornas con hilos
    public void Hilos(){
        Thread hilo = new Thread(() -> spinnerWorlds(url));
        Thread hilo2 = new Thread(() -> spinnersCity());
        hilo.start();
        hilo2.start();
    }
}