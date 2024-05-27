package com.example.TibiaTools;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.APISERVER.models.APIBoostableBosses;
import com.example.TibiaTools.APISERVER.models.ApiNews;
import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.APICriatures;
import com.example.TibiaTools.APISERVER.models.ApiNewsTicker;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.APISERVER.models.criatures.BoostableBosses;
import com.example.TibiaTools.APISERVER.models.criatures.Boosted;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.View.About;
import com.example.TibiaTools.View.Blessings;
import com.example.TibiaTools.View.Criaturas;
import com.example.TibiaTools.View.GuildInformation;
import com.example.TibiaTools.View.Highscores;
import com.example.TibiaTools.View.HouseActivity;
import com.example.TibiaTools.View.Mundos;
import com.example.TibiaTools.View.Spells_Tibia;
import com.example.TibiaTools.View.Stamina;
import com.example.TibiaTools.View.ViewModel.ViewModelHome;
import com.example.TibiaTools.View.experiencia_compartida;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewNews;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewNews;
import com.example.TibiaTools.utilidades.RedValidator;

import com.example.ttools.R;
import com.example.TibiaTools.View.TibiaMaps;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MenuItem.OnMenuItemClickListener {
    /**
     * Navigation Drawer
     * */
    String url_onlines = "https://api.tibiadata.com/";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    ImageView imgRashid, imgBossCreature, imgBossBosstable;
    TextView lbRashid, textBossCreature,lbBossBosstable;
    //para las new latest
    TextView textViewFecha,textViewCategoria, textViewNoticia,textViewTipo;
    TextView textViewDate, textViewCategory, textViewNew, textViewtype, textViewPlayesOnline;
    //recyclerview
    RecyclerView recyclerViewNoticas;
    AdapterRecyclerViewNews adapterRecyclerViewNews;
    LinearProgressIndicator linearProgressIndicator;
    List<ItemsRecyclerViewNews> itemsRecyclerViewNewsList;
    MaterialCardView cardCreatureBooss,cardBoostedBoos,cardNews, cardNews2;
    //RedValidator redValidator = new RedValidator();
    //retrofit
    InstanciaRetrofit servicio = new InstanciaRetrofit();
    ViewModelProvider viewModelProvider;
    ViewModelHome viewModelHome;
    //Asincronia asincronia = new Asincronia();
    @RequiresApi(api = Build.VERSION_CODES.N)
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
        navigationView.getMenu().findItem(R.id.nd_maps).setOnMenuItemClickListener(this);

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
        textViewPlayesOnline = findViewById(R.id.players_online_main);
        Timer timer = new Timer();
        //recyclerview
        recyclerViewNoticas = findViewById(R.id.recyclerNews);
        itemsRecyclerViewNewsList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewNoticas.setLayoutManager(layoutManager);
        adapterRecyclerViewNews = new AdapterRecyclerViewNews(itemsRecyclerViewNewsList);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewNoticas.setHasFixedSize(true);
        recyclerViewNoticas.setAdapter(adapterRecyclerViewNews);

        viewModelProvider = new ViewModelProvider(this);
        viewModelHome = viewModelProvider.get(ViewModelHome.class);
        viewModelHome.getRashidLocation().observe(this, location -> {
            if (location != null){
                String url_rashid_image = "https://raw.githubusercontent.com/MiguelJeronimo/TtoolsDesktop/main/src/img/rashid.gif";
                lbRashid.setText(location);
                Glide.with(getApplicationContext()).load(url_rashid_image).into(imgRashid);
                imgRashid.setVisibility(View.VISIBLE);
                lbRashid.setVisibility(View.VISIBLE);
                System.out.println("Se pinto la imagen de rashid");
            } else {
                imgRashid.setVisibility(View.GONE);
                lbRashid.setVisibility(View.GONE);
                System.out.println("NO SE MOSTRO NADA REFERENTE A RASHID");
            }
        });

        viewModelHome.playersOnline().observe(this, playersOnline -> {
           if (playersOnline != null){
               String onlines = "Players Online: "+playersOnline.getPlayers_online();
               textViewPlayesOnline.setVisibility(View.VISIBLE);
               textViewPlayesOnline.setText(onlines);
           } else{
               textViewPlayesOnline.setVisibility(View.GONE);
               textViewPlayesOnline.setText("");
           }
        });

        viewModelHome.creatureBoss().observe(this, creatureBoss -> {
            if (creatureBoss != null){
                Glide.with(getApplicationContext()).load(creatureBoss.getBoosted().getImage_url()).into(imgBossCreature);
                textBossCreature.setText(creatureBoss.getBoosted().getName());
                cardCreatureBooss.setVisibility(View.VISIBLE);
            }
            else{
                linearProgressIndicator.setVisibility(View.GONE);
                cardCreatureBooss.setVisibility(View.GONE);
            }
        });

        viewModelHome.bostedBoss().observe(this, bostedBoss->{
            if (bostedBoss != null){
                Boosted boosted = bostedBoss.getBoosted();
                Glide.with(getApplicationContext()).load(boosted.getImage_url()).into(imgBossBosstable);
                lbBossBosstable.setText(boosted.getName());
                cardBoostedBoos.setVisibility(View.VISIBLE);
            } else {
                cardBoostedBoos.setVisibility(View.GONE);
            }
        });

        viewModelHome.news().observe(this, news ->{
            if (news != null){
                int sizeNews = news.getNews().size();
                int rangeNews = Math.min(sizeNews, 2);
                if (sizeNews >0){
                    for (int i = 0; i < rangeNews; i++) {
                        textViewFecha.setText(news.getNews().get(i).getDate());
                        textViewCategoria.setText(news.getNews().get(i).getCategory());
                        textViewNoticia.setText(news.getNews().get(i).getNews());
                        textViewTipo.setText(news.getNews().get(i).getType());
                        //Segundo Card
                        textViewDate.setText(news.getNews().get(i+1).getDate());
                        textViewCategory.setText(news.getNews().get(i+1).getCategory());
                        textViewNew.setText(news.getNews().get(i+1).getNews());
                        textViewtype.setText(news.getNews().get(i+1).getType());
                        cardNews.setVisibility(View.VISIBLE);
                        cardNews2.setVisibility(View.VISIBLE);
                    }
                }
            } else{
                cardNews.setVisibility(View.GONE);
                cardNews2.setVisibility(View.GONE);
            }
        });

        viewModelHome.newTicker().observe(this, newTicker->{
            if (newTicker!=null){
                newTicker.getNews().forEach(news->{
                    itemsRecyclerViewNewsList.add(new ItemsRecyclerViewNews(
                            news.getId(),
                            news.getDate(),
                            news.getNews(),
                            news.getCategory(),
                            news.getType(),
                            news.getUrl()
                    ));
                });
                adapterRecyclerViewNews.notifyDataSetChanged();
                linearProgressIndicator.setVisibility(View.GONE);
            }else{
                linearProgressIndicator.setVisibility(View.GONE);
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> viewModelHome.setPlayersOnline());
            }
        }, 0, 3000);

        //Validamos si tenenemos conexion a internet
        if (RedValidator.ValidarInternet(getApplication())) {
            //ejecutando los multiple hilos para el consumo de api
            threads();
        } else{
           Toast.makeText(this,"Revisa tu conexion a internet :)",Toast.LENGTH_SHORT).show();
           linearProgressIndicator.setVisibility(View.GONE);
        }

    }

    public void threads(){
        //creatureBoss(url_creature_boss);
        //crear el pool de hilos
        Executor executor = Executors.newFixedThreadPool(5);
        // Submit tasks to the thread pool
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                viewModelHome.setCreatureBoss();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                viewModelHome.setBostedBoss();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                viewModelHome.setNews();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                viewModelHome.setNewTicker();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
                viewModelHome.setRashirLocation();
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
            Intent characters = new Intent(this, com.example.TibiaTools.View.characters.class);
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
        else if(menuItem.getItemId() == R.id.nd_maps){
            Intent tibiamaps = new Intent(this, TibiaMaps.class);
            startActivity(tibiamaps);
            drawerLayout.close();
        }
        return false;
    }
}

