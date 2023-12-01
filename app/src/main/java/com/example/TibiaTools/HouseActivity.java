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

import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HouseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityHouseBinding binding;
    String url = "https://api.tibiadata.com/v3/";
    String mundo;
    DataHighScores dataHighScores = new DataHighScores();
    AutoCompleteTextView spinnerWorlds, spinnerCitys;
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
        binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.VISIBLE);
        spinnerWorlds = findViewById(R.id.spinner_mundos);
        spinnerCitys = findViewById(R.id.spinner_citys);
        spinnerWorlds.setOnItemClickListener(this);
        spinnerCitys.setOnItemClickListener(this);
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
        new Handler(Looper.getMainLooper()).post(()->{
            spinnerCitys.setAdapter(adapterCitys);
        });
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
                    binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
                }else{
                    Toast.makeText(getApplicationContext(),"Fallo en la opcion mundos.. Intente mas tarde", Toast.LENGTH_SHORT).show();
                    binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DataWords> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(),"Error de conexión intente mas tarde :)", Toast.LENGTH_SHORT).show();
                binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
            }
        });
    }

    public void Casas(String url, String World, String Town){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_houses);
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiHouses> call = tibiaAPIServer.getHousesInformation(World, Town);
        binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.VISIBLE);
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
                binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
                recyclerView.setHasFixedSize(true);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapterRecyclerViewHouses = new AdapterRecyclerViewHouses(list_houses);
                recyclerView.setAdapter(adapterRecyclerViewHouses);
                adapterRecyclerViewHouses.setOnClickListener(view -> {
                    //Enviando el id de la casa a al activity HousesInformation
                    String id_house = list_houses.get(recyclerView.getChildAdapterPosition(view)).getHouseId();
                    Intent intent = new Intent(HouseActivity.this,HousesInformation.class);
                    intent.putExtra("ID",id_house);
                    intent.putExtra("mundo", mundo);
                    startActivity(intent);
                });
                }else{
                    Toast.makeText(getApplicationContext(),"Fallo en la opcion mundos.. Intente mas tarde", Toast.LENGTH_SHORT).show();
                    binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ApiHouses> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(),"Error de conexión intente mas tarde :)", Toast.LENGTH_SHORT).show();
                binding.getRoot().findViewById(R.id.carga_houses).setVisibility(View.GONE);
            }
        });

    }

    //llamadas asincornas con hilos
    public void Hilos(){
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> spinnerWorlds(url));
        executor.execute(() -> spinnersCity());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!spinnerCitys.getText().toString().isEmpty() && !spinnerWorlds.getText().toString().isEmpty()){
            if (!spinnerCitys.getText().toString().equalsIgnoreCase("Seleccione una ciudad") && !spinnerWorlds.getText().toString().equalsIgnoreCase("Seleccione un mundo")){
                String mundo, ciudad;
                mundo = spinnerWorlds.getText().toString();
                ciudad = spinnerCitys.getText().toString();
                dataHighScores.setMundo(mundo);
                dataHighScores.setCiudad(ciudad);
                Casas(url,dataHighScores.getMundo(),dataHighScores.getCiudad());
            }
        }
    }
}