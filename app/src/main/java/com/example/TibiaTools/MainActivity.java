package com.example.TibiaTools;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.TibiaTools.APISERVER.models.Worlds.RegularWorlds;
import com.example.TibiaTools.APISERVER.models.criatures.Boosted;
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
import com.example.TibiaTools.View.characters;
import com.example.TibiaTools.View.experiencia_compartida;
import com.example.TibiaTools.recyclerview.Adapters.AdapterRecyclerViewNews;
import com.example.TibiaTools.recyclerview.ItemsRecyclerViewNews;
import com.example.TibiaTools.utilidades.RedValidator;

import com.example.ttools.R;
import com.example.TibiaTools.View.TibiaMaps;
import com.github.AAChartModel.AAChartCore.AAChartCreator.AAChartModel;
import com.github.AAChartModel.AAChartCore.AAChartCreator.AAChartView;
import com.github.AAChartModel.AAChartCore.AAChartCreator.AASeriesElement;
import com.github.AAChartModel.AAChartCore.AAChartEnum.AAChartType;
import com.github.AAChartModel.AAChartCore.AAOptionsModel.AAChart;
import com.github.AAChartModel.AAChartCore.AAOptionsModel.AAScrollablePlotArea;
import com.github.AAChartModel.AAChartCore.AAOptionsModel.AAStyle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;

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
    TextView textViewDate, textViewCategory, textViewNew, textViewtype, textViewPlayesOnline;
    //recyclerview
    RecyclerView recyclerViewNoticas;
    AdapterRecyclerViewNews adapterRecyclerViewNews;
    LinearProgressIndicator linearProgressIndicator;
    List<ItemsRecyclerViewNews> itemsRecyclerViewNewsList;
    MaterialCardView cardCreatureBooss,cardBoostedBoos,cardNews, cardNews2;
    //RedValidator redValidator = new RedValidator();
    ViewModelProvider viewModelProvider;
    ViewModelHome viewModelHome;
    AAChartView aaChartView, aaChartView2;
    ArrayList<Double> data = new ArrayList<>();
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
        aaChartView = findViewById(R.id.AAChartView);
        aaChartView2 = findViewById(R.id.AAChartView2);
        AAChartModel aaChartModel = new AAChartModel();
        AAChartModel aaChartModel2 = new AAChartModel();
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
        ArrayList<AASeriesElement> dataElement = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> categories2 = new ArrayList<>();
        ArrayList<Double> data2 = new ArrayList<>();
        AtomicBoolean isDrawChart2 = new AtomicBoolean(false);
        viewModelHome.worlds().observe(this, worlds -> {
            if (worlds != null){
                categories.clear();
                dataElement.clear();
                data2.clear();
                ArrayList<RegularWorlds> worldsList = new ArrayList<>(worlds.getRegular_worlds());
                Collections.sort(worldsList,Collections.reverseOrder());
                for (RegularWorlds world: worldsList){
                    categories.add(world.getName());
                    data2.add((double) world.getPlayers_online());
                }
                dataElement.add(
                        new AASeriesElement()
                                .name("Onlines")
                                .data(data2.toArray())
                );
                AASeriesElement [] serieelement = new AASeriesElement[dataElement.size()];
                serieelement = dataElement.toArray(serieelement);
                String [] categoriesArray = new String[categories.size()];
                categoriesArray = categories.toArray(categoriesArray);
                if (isDrawChart2.get()){
                    aaChartView2.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(serieelement);
                } else {
                    aaChartModel2.chartType(AAChartType.Bar)
                            .animationType("Bounce")
                            .title("Worlds").titleStyle(new AAStyle().color("#DCE4E9"))
                            .subtitle("Players online to worlds in Tibia").subtitleStyle(new AAStyle().color("#70787D"))
                            .backgroundColor("#191C1E")
                            .dataLabelsEnabled(false)
                            .categories(categoriesArray)
                            .yAxisGridLineWidth(0f).colorsTheme(new String[]{"#67D3FF"})
                            .series(dataElement.toArray());
//                            .scrollablePlotArea(new AAScrollablePlotArea()
//                                    .minWidth(1000)
//                                    .scrollPositionX(1.0f)
//                            );
                    aaChartView2.aa_drawChartWithChartModel(aaChartModel2);
                    isDrawChart2.set(true);
                }
            }
        });

        AtomicBoolean isDrawChart = new AtomicBoolean(false);
        viewModelHome.playersOnline().observe(this, playersOnline -> {
           if (playersOnline != null){
               long timestamp = System.currentTimeMillis();
               Date date = new Date(timestamp);
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale.getDefault());
               String formattedDate = dateFormat.format(date);
               String onlines = "Players Online: "+playersOnline.getPlayers_online();
               textViewPlayesOnline.setVisibility(View.VISIBLE);
               textViewPlayesOnline.setText(onlines);
               Double players = Double.parseDouble(String.valueOf(playersOnline.getPlayers_online()));
                if (!data.isEmpty()){
                    int position = data.size() - 1;
                    if (!data.get(position).equals(players)){
                        data.add(players);
                    }
                } else {
                    data.add(players);
                }
              //categories.add(formattedDate);
               aaChartModel.chartType(AAChartType.Spline)
                       .animationType("Bounce")
                       .title("PLAYERS ONLINE").titleStyle(new AAStyle().color("#DCE4E9"))
                       .subtitle("Players online in Tibia").subtitleStyle(new AAStyle().color("#70787D"))
                       .backgroundColor("#191C1E")
                       .dataLabelsEnabled(false)
                       .yAxisGridLineWidth(0f);

                if (isDrawChart.get()){
                    AASeriesElement[] DataElementChart = new AASeriesElement[]{
                            new AASeriesElement()
                                    .name("Onlines")
                                    .data(data.toArray()),
                    };
                    aaChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(DataElementChart);
                }else {
                    aaChartModel.series(new AASeriesElement[]{
                                    new AASeriesElement()
                                            .name("Onlines")
                                            .data(data.toArray()),
                            });
                    aaChartView.aa_drawChartWithChartModel(aaChartModel);
                    isDrawChart.set(true);
                }
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

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(()-> viewModelHome.setWorlds());
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

