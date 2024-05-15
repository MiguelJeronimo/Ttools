package com.example.TibiaTools.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiHousesInformation;
import com.example.TibiaTools.APISERVER.models.Houses.House;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.utilidades.RedValidator;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityHousesInformationBinding;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HousesInformation extends AppCompatActivity {
    Intent intent;
    String id_house, mundo;
    TextView txtName,txtWorld,txtCity,txtType,txtBeds,txtSize,txtPrice,txtOwner;
    String url = "https://api.tibiadata.com/v4/";
    ImageView imgCasa;
    private ActivityHousesInformationBinding binding;
    InstanciaRetrofit services = new InstanciaRetrofit();
    // para formatear numeros a formato de dinero.
    DecimalFormat decimalFormat = new DecimalFormat("###,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHousesInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        intent = getIntent();
        id_house = intent.getStringExtra("ID");
        mundo = intent.getStringExtra("mundo");
        imgCasa = findViewById(R.id.imgCasa);
        txtName = findViewById(R.id.txtName);
        txtWorld = findViewById(R.id.txtWorld);
        txtCity = findViewById(R.id.txtCity);
        txtType = findViewById(R.id.txtType);
        txtBeds = findViewById(R.id.txtBeds);
        txtSize = findViewById(R.id.txtSize);
        txtPrice = findViewById(R.id.txtPrice);
        txtOwner = findViewById(R.id.txtOwner);
        RedValidator redValidator = new RedValidator();
        if (redValidator.ValidarInternet(getApplication())){
            Hilos();
        } else {
            binding.getRoot().findViewById(R.id.cardHouseGeneral).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView19).setVisibility(View.GONE);
            txtOwner.setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.carga_house_information).setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "No estas conectado a internet...", Toast.LENGTH_SHORT).show();
        }

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

    public void llenarData(String url, String mundo, String id_house){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiHousesInformation> call = tibiaAPIServer.getHouseInformation(mundo, id_house);
        call.enqueue(new Callback<ApiHousesInformation>() {
            @Override
            public void onResponse(Call<ApiHousesInformation> call, Response<ApiHousesInformation> response) {
                if (response.isSuccessful()){
                    ApiHousesInformation apiHousesInformation = response.body();
                    House house = apiHousesInformation.getHouse();
                    Glide.with(getApplicationContext()).load(house.getImg()).into(imgCasa);
                    txtName.setText(house.getName());
                    txtWorld.setText(house.getWorld());
                    txtCity.setText(house.getTown());
                    txtType.setText(house.getType());
                    txtBeds.setText(String.valueOf(house.getBeds()));
                    txtSize.setText(String.valueOf(house.getSize()));
                    txtPrice.setText(decimalFormat.format(house.getRent()));
                    txtOwner.setText(house.getStatus().getOriginal());
                    binding.getRoot().findViewById(R.id.cardHouseGeneral).setVisibility(View.VISIBLE);
                    binding.getRoot().findViewById(R.id.textView19).setVisibility(View.VISIBLE);
                    txtOwner.setVisibility(View.VISIBLE);
                    binding.getRoot().findViewById(R.id.carga_house_information).setVisibility(View.GONE);
                }else{
                    binding.getRoot().findViewById(R.id.cardHouseGeneral).setVisibility(View.GONE);
                    binding.getRoot().findViewById(R.id.textView19).setVisibility(View.GONE);
                    txtOwner.setVisibility(View.GONE);
                    binding.getRoot().findViewById(R.id.carga_house_information).setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Error de conexión, intente mas tarde...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiHousesInformation> call, Throwable t) {
                System.out.println(t.getMessage());
                binding.getRoot().findViewById(R.id.cardHouseGeneral).setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.textView19).setVisibility(View.GONE);
                txtOwner.setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.carga_house_information).setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error de conexión, intente mas tarde...", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //implementacion de llamadas asincronas con hilos
    public void Hilos(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                llenarData(url, mundo, id_house);
            }
        });
        hilo.start();
    }
}