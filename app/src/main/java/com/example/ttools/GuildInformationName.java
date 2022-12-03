package com.example.ttools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.APICriatures;
import com.example.ttools.APISERVER.models.APICriaturesInformation;
import com.example.ttools.APISERVER.models.GuildInformation.ApiGuildsName;
import com.example.ttools.APISERVER.models.GuildInformation.GuildName.GuildName;
import com.example.ttools.APISERVER.models.GuildInformation.GuildName.Guildss;
import com.example.ttools.APISERVER.models.GuildInformation.GuildName.members.MembersGuild;
import com.example.ttools.recyclerview.Adapters.AdapterRecyclerViewGuildName;
import com.example.ttools.recyclerview.Adapters.AdapterRecyclerViewGuildsList;
import com.example.ttools.recyclerview.itemsRecyclerViewGuildsName;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttools.databinding.ActivityGuildInformationNameBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuildInformationName extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityGuildInformationNameBinding binding;
    Intent intent;
    String guildName;
    ImageView imageViewGuildLogo;
    TextView textViewGuildName,textViewDescription,textViewInWar,textViewOnline,
             textViewNombre,textViewMundo,textViewPiad,textViewFounded,textViewActive;
    String url = "https://api.tibiadata.com/v3/guild/";
    RecyclerView recyclerView;
    AdapterRecyclerViewGuildName adapter;
    List<itemsRecyclerViewGuildsName> itemsRecyclerViewGuildsNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuildInformationNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        //respuesta de la pantalla anterior
        intent = getIntent();
        guildName = intent.getStringExtra("nameGuild");
        imageViewGuildLogo = binding.getRoot().findViewById(R.id.imageViewGuildLogo);
        textViewGuildName = binding.getRoot().findViewById(R.id.textViewGuildName);
        textViewDescription = binding.getRoot().findViewById(R.id.textViewDescription);
        textViewInWar = binding.getRoot().findViewById(R.id.textViewInWar);
        textViewOnline = binding.getRoot().findViewById(R.id.textViewOnline);
        textViewNombre = binding.getRoot().findViewById(R.id.textViewNombre);
        textViewMundo = binding.getRoot().findViewById(R.id.textViewMundo);
        textViewPiad = binding.getRoot().findViewById(R.id.textViewPiad);
        textViewFounded = binding.getRoot().findViewById(R.id.textViewFounded);
        textViewActive = binding.getRoot().findViewById(R.id.textViewActive);
        Informacion(url, guildName);
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


    public void Informacion(String url, String guildName){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewGuildName);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Retrofit services = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TibiaAPIServer tibiaAPIServer = services.create(TibiaAPIServer.class);
        Call<ApiGuildsName> call = tibiaAPIServer.getGuildsInformationName(guildName);
        call.enqueue(new Callback<ApiGuildsName>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ApiGuildsName> call, Response<ApiGuildsName> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Codigo: " + response.code());
                    return;
                }
                ApiGuildsName apiGuildsName = response.body();
                Guildss guildss = apiGuildsName.getGuilds();
                GuildName data = guildss.getGuild();
                Glide.with(getApplicationContext()).load(data.getLogo_url()).asGif().into(imageViewGuildLogo);
                textViewGuildName.setText(data.getName());
                textViewDescription.setText(data.getDescription());
                if (data.getIn_war()){
                    textViewInWar.setText("Si");
                } else{
                    textViewInWar.setText("No");
                }
                textViewOnline.setText(data.getPlayers_online()+"/"+data.getMembers_total());
                if (data.getGuildhalls() != null){
                    String name = data.getGuildhalls().get(0).getName();
                    String Mundo = data.getGuildhalls().get(0).getWorld();
                    String Paid = data.getGuildhalls().get(0).getPaid_until();
                    textViewNombre.setText(name);
                    textViewMundo.setText(Mundo);
                    textViewPiad.setText(Paid);
                }
                textViewFounded.setText("Fundada: "+data.getFounded());
                if (data.getActive()){
                    textViewActive.setText("Active: Si");
                } else {
                    textViewActive.setText("Active: No");
                }
                if (data.getMembers() != null){
                    itemsRecyclerViewGuildsNames = new ArrayList<>();
                    for (MembersGuild membersGuild:data.getMembers()) {
                        itemsRecyclerViewGuildsNames.add(
                                new itemsRecyclerViewGuildsName(
                                        membersGuild.getName(),
                                        membersGuild.getTitle(),
                                        membersGuild.getRank(),
                                        membersGuild.getVocation(),
                                        membersGuild.getLevel(),
                                        membersGuild.getJoined(),
                                        membersGuild.getStatus()
                                )
                        );
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new AdapterRecyclerViewGuildName(itemsRecyclerViewGuildsNames);
                        adapter.notifyDataSetChanged();
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiGuildsName> call, Throwable t) {

            }
        });
    }

}