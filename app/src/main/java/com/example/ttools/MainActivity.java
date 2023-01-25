package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.ttools.APISERVER.models.ApiNews;
import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.APICriatures;
import com.example.ttools.APISERVER.models.ApiNewsTicker;
import com.example.ttools.APISERVER.models.criatures.BoostableBosses;
import com.example.ttools.APISERVER.models.criatures.Criatures;
import com.example.ttools.Operaciones.InstanciaRetrofit;
import com.example.ttools.recyclerview.Adapters.AdapterRecyclerViewNews;
import com.example.ttools.recyclerview.ItemsRecyclerViewNews;
import com.example.ttools.utilidades.RedValidator;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MenuItem.OnMenuItemClickListener {
    /**
     * Navigation Drawer
     * */
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    ImageView imgRashid, imgBossCreature, imgBossBosstable;
    TextView lbRashid, textBossCreature,lbBossBosstable;
    //para las new latest
    TextView textViewFecha,textViewCategoria, textViewNoticia,textViewTipo;
    TextView textViewDate, textViewCategory, textViewNew, textViewtype;
    String url = "https://api.tibialabs.com/v2/";
    String url_rashid_image = "https://raw.githubusercontent.com/MiguelJeronimo/TtoolsDesktop/main/src/img/rashid.gif";
    String url_creature_boss = "https://api.tibiadata.com/v3/";
    String url_boosted_boss = "https://api.tibiadata.com/v3/";
    String url_new = "https://api.tibiadata.com/v3/";
    //recyclerview
    RecyclerView recyclerViewNoticas;
    AdapterRecyclerViewNews adapterRecyclerViewNews;
    List<ItemsRecyclerViewNews> itemsRecyclerViewNewsList;
    //RedValidator redValidator = new RedValidator();
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    //Asincronia asincronia = new Asincronia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.navegacion);
        //Agrega utilidades a la toolbar, asi como el incono de hamburguesa, y los iconos de cuando se despliegue el menu y cuando este cerrado
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open,R.string.nav_close);
        //agrega el evento al drawelayout, recibe por parametro el actionBarDrawerToggle en la cual se encuentra las animaciones
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //sincroniza los estados del navigationDrawer
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.barraNavegacion);
        //a cada item del menuo agregamos su evento MenuItemClickListener
        navigationView.getMenu().findItem(R.id.nd_character).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_criaturas).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_stamina).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_experiencia_compartida).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_mundos).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_guilds).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_spells).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_bless).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_highscores).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_houses).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.id_about).setOnMenuItemClickListener(this);

        imgRashid = findViewById(R.id.imageViewRashid);
        lbRashid = findViewById(R.id.textViewRashid);
        imgBossCreature = findViewById(R.id.BossCreature);
        textBossCreature = findViewById(R.id.textBossCreature);
        imgBossBosstable = findViewById(R.id.BossBosstable);
        lbBossBosstable = findViewById(R.id.lbBossBosstable);
        //noticias
        textViewFecha = findViewById(R.id.textViewFecha);
        textViewCategoria = findViewById(R.id.textViewCategoria);
        textViewNoticia = findViewById(R.id.textViewNoticia);
        textViewTipo = findViewById(R.id.textViewTipo);
        textViewDate = findViewById(R.id.textViewDate);
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewNew = findViewById(R.id.textViewNew);
        textViewtype = findViewById(R.id.textViewtype);
        //Validamos si tenenemos conexion a internet
        if (RedValidator.ValidarInternet(this)){
            //ejecutando los multiple hilos para el consumo de api
            //asincronia.execute();
            Hilos();
        } else{
            Toast.makeText(this,"Revisa tu conexion a internet :)",Toast.LENGTH_SHORT).show();
        }

    }

    public void llenarRashid(String url){
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url).create(TibiaAPIServer.class);
        Call<String> call = tibiaAPIServer.getRashidLocalitation();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    lbRashid.setText(response.body());
                    Glide.with(getApplicationContext()).load(url_rashid_image).asGif().into(imgRashid);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("MENSAJE"+t.getMessage());
            }
        });
    }

    public void creatureBoss(String url_creature_boss){
        TibiaAPIServer tibiaAPIServer =  servicio.getRetrofit(url_creature_boss).create(TibiaAPIServer.class);
        Call<APICriatures> call = tibiaAPIServer.getCreatures();
        call.enqueue(new Callback<APICriatures>() {
            @Override
            public void onResponse(Call<APICriatures> call, Response<APICriatures> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                APICriatures apiCriatures = response.body();
                assert apiCriatures != null;
                Criatures criatures = apiCriatures.getCreatures();
                Glide.with(getApplicationContext()).load(criatures.getBoosted().getImage_url()).asGif().into(imgBossCreature);
                textBossCreature.setText(criatures.getBoosted().getName());

            }

            @Override
            public void onFailure(Call<APICriatures> call, Throwable t) {
                System.out.println("ERROR: "+t.getMessage());
            }
        });
    }
    public void bostedBoss(String url_boosted_boss){
        TibiaAPIServer apiServer = servicio.getRetrofit(url_boosted_boss).create(TibiaAPIServer.class);
        Call<APICriatures> call = apiServer.getBooted();
        call.enqueue(new Callback<APICriatures>() {
            @Override
            public void onResponse(Call<APICriatures> call, Response<APICriatures> response) {
                if (response.isSuccessful()){
                    APICriatures apiCriatures = response.body();
                    assert apiCriatures != null;
                    BoostableBosses boostableBosses = apiCriatures.getBoostable_bosses();
                    Glide.with(getApplicationContext()).load(boostableBosses.getBoosted().getImage_url()).asGif().into(imgBossBosstable);
                    lbBossBosstable.setText(boostableBosses.getBoosted().getName());
                }
            }

            @Override
            public void onFailure(Call<APICriatures> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void Noticas(String url_new){
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url_new).create(TibiaAPIServer.class);
        Call<ApiNews> call = tibiaAPIServer.getNewsLatest();
        call.enqueue(new Callback<ApiNews>() {
            @Override
            public void onResponse(Call<ApiNews> call, Response<ApiNews> response) {
                if (response.isSuccessful()){
                   ApiNews apiNews = response.body();
                   //Primer Card
                   textViewFecha.setText(apiNews.getNews().get(0).getDate());
                   textViewCategoria.setText(apiNews.getNews().get(0).getCategory());
                   textViewNoticia.setText(apiNews.getNews().get(0).getNews());
                   textViewTipo.setText(apiNews.getNews().get(0).getType());
                   //Segundo Card
                   textViewDate.setText(apiNews.getNews().get(1).getDate());
                   textViewCategory.setText(apiNews.getNews().get(1).getCategory());
                   textViewNew.setText(apiNews.getNews().get(1).getNews());
                   textViewtype.setText(apiNews.getNews().get(1).getType());
                }
            }

            @Override
            public void onFailure(Call<ApiNews> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void NewsTickers(String url_new){
        recyclerViewNoticas = findViewById(R.id.recyclerNews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url_new).create(TibiaAPIServer.class);
        Call<ApiNewsTicker> call = tibiaAPIServer.getNewsTickers();
        call.enqueue(new Callback<ApiNewsTicker>() {
            @Override
            public void onResponse(Call<ApiNewsTicker> call, Response<ApiNewsTicker> response) {
                if (response.isSuccessful()){
                    ApiNewsTicker apiNewsTicker = response.body();
                    itemsRecyclerViewNewsList = new ArrayList<>();
                    for (int i=0; i<5; i++){
                        itemsRecyclerViewNewsList.add(new ItemsRecyclerViewNews(
                                apiNewsTicker.getNews().get(i).getId(),
                                apiNewsTicker.getNews().get(i).getDate(),
                                apiNewsTicker.getNews().get(i).getNews(),
                                apiNewsTicker.getNews().get(i).getCategory(),
                                apiNewsTicker.getNews().get(i).getType(),
                                apiNewsTicker.getNews().get(i).getUrl()
                        ));
                    }
                    recyclerViewNoticas.setLayoutManager(layoutManager);
                    adapterRecyclerViewNews = new AdapterRecyclerViewNews(itemsRecyclerViewNewsList);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    //recyclerViewNoticas.setHasFixedSize(true);
                    recyclerViewNoticas.setAdapter(adapterRecyclerViewNews);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiNewsTicker> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    public void Hilos(){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                llenarRashid(url);
            }
        });
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                creatureBoss(url_creature_boss);
            }
        });
        Thread hilo3 = new Thread(new Runnable() {
            @Override
            public void run() {
                bostedBoss(url_boosted_boss);
            }
        });

        Thread hilo4 = new Thread(new Runnable() {
            @Override
            public void run() {
                Noticas(url_new);
            }
        });
        Thread hilo5 = new Thread(new Runnable() {
            @Override
            public void run() {
                NewsTickers(url_new);
            }
        });
        hilo.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }

//implementacion de tareas asincronas
/*private class Asincronia extends AsyncTask <String, Void, String> {
    Thread hilo = new Thread(new Runnable() {
        @Override
        public void run() {
            llenarRashid(url);
        }
    });
    Thread hilo2 = new Thread(new Runnable() {
        @Override
        public void run() {
            creatureBoss(url_creature_boss);
        }
    });
    Thread hilo3 = new Thread(new Runnable() {
        @Override
        public void run() {
            bostedBoss(url_boosted_boss);
        }
    });

    Thread hilo4 = new Thread(new Runnable() {
        @Override
        public void run() {
            Noticas(url_new);
        }
    });
    Thread hilo5 = new Thread(new Runnable() {
        @Override
        public void run() {
            NewsTickers(url_new);
        }
    });

    @Override
    protected String doInBackground(String... strings) {
        hilo.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        return null;
    }
}*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.nd_about){
            Intent about = new Intent(this, About.class);
            startActivity(about);
        } else if (id == R.id.nd_salir){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.nd_experiencia_compartida){
            Intent expcompartida = new Intent(this, experiencia_compartida.class);
            startActivity(expcompartida);
            drawerLayout.close();
        } else if (menuItem.getItemId() == R.id.nd_character){
            Intent characters = new Intent(this, characters.class);
            startActivity(characters);
            drawerLayout.close();
        } else if (menuItem.getItemId() == R.id.nd_stamina){
            Intent stamina = new Intent(this, Stamina.class);
            startActivity(stamina);
            drawerLayout.close();
        }else if (menuItem.getItemId() == R.id.nd_mundos){
            Intent mundos = new Intent(this, Mundos.class);
            startActivity(mundos);
            drawerLayout.close();
        }else if(menuItem.getItemId() == R.id.nd_criaturas){
            Intent criaturas = new Intent(this, Criaturas.class);
            startActivity(criaturas);
            drawerLayout.close();
        } else if (menuItem.getItemId() == R.id.nd_guilds){
            Intent guilds = new Intent(this, GuildInformation.class);
            startActivity(guilds);
            drawerLayout.close();
        } else if (menuItem.getItemId() == R.id.nd_spells){
            Intent spellsTibia = new Intent(this, Spells_Tibia.class);
            startActivity(spellsTibia);
            drawerLayout.close();
        }else if (menuItem.getItemId() == R.id.nd_bless){
            Intent blessTibia= new Intent(this, Blessings.class);
            startActivity(blessTibia);
            drawerLayout.close();
        } else if(menuItem.getItemId() == R.id.nd_highscores){
            Intent HighscoresTibia= new Intent(this, Highscores.class);
            startActivity(HighscoresTibia);
            drawerLayout.close();
        } else if (menuItem.getItemId() == R.id.nd_houses){
            Intent tibiaHouses = new Intent(this, HouseActivity.class);
            startActivity(tibiaHouses);
            drawerLayout.close();
        } else if(menuItem.getItemId() == R.id.id_about){
            Intent about = new Intent(this, About.class);
            startActivity(about);
            drawerLayout.close();
        }
        return false;
    }
}

