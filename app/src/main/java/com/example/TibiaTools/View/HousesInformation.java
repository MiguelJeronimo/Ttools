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
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.View.ViewModel.ViewModelHouseInformation;
import com.example.TibiaTools.utilidades.RedValidator;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityHousesInformationBinding;

import java.text.DecimalFormat;

public class HousesInformation extends AppCompatActivity {
    Intent intent;
    String id_house, mundo, name;
    TextView txtName,txtWorld,txtCity,txtType,txtBeds,txtSize,txtPrice,txtOwner;
    ImageView imgCasa;
    private ActivityHousesInformationBinding binding;
    // para formatear numeros a formato de dinero.
    DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
    ViewModelProvider viewModelProvider;
    ViewModelHouseInformation viewModelHouseInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHousesInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        viewModelProvider = new ViewModelProvider(this);
        viewModelHouseInformation = viewModelProvider.get(ViewModelHouseInformation.class);
        intent = getIntent();
        id_house = intent.getStringExtra("ID");
        mundo = intent.getStringExtra("mundo");
        name = intent.getStringExtra("name");
        getSupportActionBar().setTitle(name);
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
            viewModelHouseInformation.setHouse(mundo,id_house);
            //Hilos();
        } else {
            binding.getRoot().findViewById(R.id.cardHouseGeneral).setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.textView19).setVisibility(View.GONE);
            txtOwner.setVisibility(View.GONE);
            binding.getRoot().findViewById(R.id.carga_house_information).setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "No estas conectado a internet...", Toast.LENGTH_SHORT).show();
        }
        viewModelHouseInformation.getHouse().observe(this, house -> {
            if (house != null){
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
            } else {
                binding.getRoot().findViewById(R.id.cardHouseGeneral).setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.textView19).setVisibility(View.GONE);
                txtOwner.setVisibility(View.GONE);
                binding.getRoot().findViewById(R.id.carga_house_information).setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error de conexión, intente mas tarde...", Toast.LENGTH_SHORT).show();
            }
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