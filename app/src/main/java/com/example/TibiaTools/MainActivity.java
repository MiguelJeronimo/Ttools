package com.example.TibiaTools;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.APISERVER.models.ApiNews;
import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.APICriatures;
import com.example.TibiaTools.APISERVER.models.ApiNewsTicker;
import com.example.TibiaTools.APISERVER.models.criatures.BoostableBosses;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewNews;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewNews;
import com.example.TibiaTools.utilidades.RedValidator;

import com.example.ttools.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    String url_rashid_image = "https://raw.githubusercontent.com/MiguelJeronimo/TtoolsDesktop/main/src/img/rashid.gif";
    //recyclerview
    RecyclerView recyclerViewNoticas;
    AdapterRecyclerViewNews adapterRecyclerViewNews;
    LinearProgressIndicator linearProgressIndicator;
    List<ItemsRecyclerViewNews> itemsRecyclerViewNewsList;
    MaterialCardView cardCreatureBooss,cardBoostedBoos,cardNews, cardNews2;
    //RedValidator redValidator = new RedValidator();
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    //Asincronia asincronia = new Asincronia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Cards
        cardCreatureBooss = findViewById(R.id.cardView3);
        cardBoostedBoos = findViewById(R.id.cardView2);
        cardNews = findViewById(R.id.cardView4);
        cardNews2 = findViewById(R.id.cardView5);
        drawerLayout = findViewById(R.id.navegacion);
        //Agrega utilidades a la toolbar, asi como el incono de hamburguesa, y los iconos de cuando se despliegue el menu y cuando este cerrado
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open,R.string.nav_close);
        //agrega el evento al drawelayout, recibe por parametro el actionBarDrawerToggle en la cual se encuentra las animaciones
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //sincroniza los estados del navigationDrawer
        actionBarDrawerToggle.syncState();
        linearProgressIndicator = findViewById(R.id.carga_main);
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
        if (RedValidator.ValidarInternet(getApplication())){
            //ejecutando los multiple hilos para el consumo de api
            Hilos();
        } else{
           Toast.makeText(this,"Revisa tu conexion a internet :)",Toast.LENGTH_SHORT).show();
           linearProgressIndicator.setVisibility(View.GONE);
        }

    }

    public void llenarRashid(String url){
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url).create(TibiaAPIServer.class);
        Call<String> call = tibiaAPIServer.getRashidLocalitation();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()){
                    lbRashid.setText(response.body());
                    Glide.with(getApplicationContext()).load(url_rashid_image).into(imgRashid);
                    imgRashid.setVisibility(View.VISIBLE);
                    lbRashid.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                System.out.println("MENSAJE"+t.getMessage());
            }
        });
    }

    public void creatureBoss(String url_creature_boss){
        TibiaAPIServer tibiaAPIServer =  servicio.getRetrofit(url_creature_boss).create(TibiaAPIServer.class);
        Call<APICriatures> call = tibiaAPIServer.getCreatures();
        call.enqueue(new Callback<APICriatures>() {
            @Override
            public void onResponse(@NonNull Call<APICriatures> call, @NonNull Response<APICriatures> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APICriatures apiCriatures = response.body();
                    Criatures criatures = apiCriatures.getCreatures();
                    Glide.with(getApplicationContext()).load(criatures.getBoosted().getImage_url()).into(imgBossCreature);
                    textBossCreature.setText(criatures.getBoosted().getName());
                    cardCreatureBooss.setVisibility(View.VISIBLE);
                } else{
                    linearProgressIndicator.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<APICriatures> call, @NonNull Throwable t) {
                System.out.println("ERROR: "+t.getMessage());
                linearProgressIndicator.setVisibility(View.GONE);
            }
        });
    }
    public void bostedBoss(String url_boosted_boss){
        TibiaAPIServer apiServer = servicio.getRetrofit(url_boosted_boss).create(TibiaAPIServer.class);
        Call<APICriatures> call = apiServer.getBooted();
        call.enqueue(new Callback<APICriatures>() {
            @Override
            public void onResponse(@NonNull Call<APICriatures> call, @NonNull Response<APICriatures> response) {
                if (response.isSuccessful()){
                    APICriatures apiCriatures = response.body();
                    assert apiCriatures != null;
                    BoostableBosses boostableBosses = apiCriatures.getBoostable_bosses();
                    Glide.with(getApplicationContext()).load(boostableBosses.getBoosted().getImage_url()).into(imgBossBosstable);
                    lbBossBosstable.setText(boostableBosses.getBoosted().getName());
                    cardBoostedBoos.setVisibility(View.VISIBLE);
                }else{
                    linearProgressIndicator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APICriatures> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void Noticas(String url_new){
        TibiaAPIServer tibiaAPIServer = servicio.getRetrofit(url_new).create(TibiaAPIServer.class);
        Call<ApiNews> call = tibiaAPIServer.getNewsLatest();
        call.enqueue(new Callback<ApiNews>() {
            @Override
            public void onResponse(@NonNull Call<ApiNews> call, @NonNull Response<ApiNews> response) throws NullPointerException{
                if (response.isSuccessful()){
                   ApiNews apiNews = response.body();
                   assert apiNews != null;
                   int sizeNews = apiNews.getNews().size();
                   int rangeNews = Math.min(sizeNews, 2);
                   if (sizeNews >0){
                       for (int i = 0; i < rangeNews; i++) {
                           textViewFecha.setText(apiNews.getNews().get(i).getDate());
                           textViewCategoria.setText(apiNews.getNews().get(i).getCategory());
                           textViewNoticia.setText(apiNews.getNews().get(i).getNews());
                           textViewTipo.setText(apiNews.getNews().get(i).getType());
                           //Segundo Card
                           textViewDate.setText(apiNews.getNews().get(i+1).getDate());
                           textViewCategory.setText(apiNews.getNews().get(i+1).getCategory());
                           textViewNew.setText(apiNews.getNews().get(i+1).getNews());
                           textViewtype.setText(apiNews.getNews().get(i+1).getType());
                           cardNews.setVisibility(View.VISIBLE);
                           cardNews2.setVisibility(View.VISIBLE);
                       }
                   }
                } else {
                    linearProgressIndicator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiNews> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                linearProgressIndicator.setVisibility(View.GONE);
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
            public void onResponse(@NonNull Call<ApiNewsTicker> call, @NonNull Response<ApiNewsTicker> response) {
                if (response.isSuccessful()){
                    ApiNewsTicker apiNewsTicker = response.body();
                    if(apiNewsTicker != null && apiNewsTicker.getNews() != null){
                    int size = apiNewsTicker.getNews().size();
                    int range = Math.min(size, 5);
                    itemsRecyclerViewNewsList = new ArrayList<>();
                    if (size>0){
                        for (int i=0; i<range; i++){
                            itemsRecyclerViewNewsList.add(new ItemsRecyclerViewNews(
                                    apiNewsTicker.getNews().get(i).getId(),
                                    apiNewsTicker.getNews().get(i).getDate(),
                                    apiNewsTicker.getNews().get(i).getNews(),
                                    apiNewsTicker.getNews().get(i).getCategory(),
                                    apiNewsTicker.getNews().get(i).getType(),
                                    apiNewsTicker.getNews().get(i).getUrl()
                            ));
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "NO ESTA ENTRANDO A LA VALIDACION"+apiNewsTicker.getNews(), Toast.LENGTH_SHORT).show();
                    }
                    }
                    recyclerViewNoticas.setLayoutManager(layoutManager);
                    adapterRecyclerViewNews = new AdapterRecyclerViewNews(itemsRecyclerViewNewsList);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerViewNoticas.setHasFixedSize(true);
                    recyclerViewNoticas.setAdapter(adapterRecyclerViewNews);
                    linearProgressIndicator.setVisibility(View.GONE);
                }else{
                    linearProgressIndicator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiNewsTicker> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                linearProgressIndicator.setVisibility(View.GONE);
            }
        });
    }
    public void Hilos(){
        String url = "https://api.tibialabs.com/v2/";
        String url_creature_boss = "https://api.tibiadata.com/v3/";
        //creatureBoss(url_creature_boss);
        String url_boosted_boss = "https://api.tibiadata.com/v3/";
        String url_new = "https://api.tibiadata.com/v3/";
        //crear el pool de hilos
        Executor executor = Executors.newFixedThreadPool(5);
        // Submit tasks to the thread pool
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                llenarRashid(url);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                creatureBoss(url_creature_boss);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                bostedBoss(url_boosted_boss);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                Noticas(url_new);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                NewsTickers(url_new);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
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

