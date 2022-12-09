package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.Rashid;
import com.example.ttools.Operaciones.InstanciaRetrofit;
import com.example.ttools.Operaciones.calcularBlessings;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

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

    ImageView imgRashid;
    TextView lbRashid;
    String url = "https://api.tibialabs.com/v2/";
    String url_rashid_image = "https://raw.githubusercontent.com/MiguelJeronimo/TtoolsDesktop/main/src/img/rashid.gif";
    InstanciaRetrofit servicio = new InstanciaRetrofit();
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
        navigationView = (NavigationView) findViewById(R.id.barraNavegacion);
        //a cada item del menuo agregamos su evento MenuItemClickListener
        navigationView.getMenu().findItem(R.id.nd_character).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_criaturas).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_stamina).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_experiencia_compartida).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_mundos).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_guilds).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_spells).setOnMenuItemClickListener(this);
        navigationView.getMenu().findItem(R.id.nd_bless).setOnMenuItemClickListener(this);

        imgRashid = findViewById(R.id.imageViewRashid);
        lbRashid = findViewById(R.id.textViewRashid);
        llenarRashid(url);
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
        /**if (id == R.id.menu_rashid) {
            return true;
        } **/
        if (id == R.id.menu_experiencia){
            Intent expcompartida = new Intent(this, experiencia_compartida.class);
            startActivity(expcompartida);
        } else if (id == R.id.menu_characters){
            Intent characters = new Intent(this, characters.class);
            startActivity(characters);
        } else if (id == R.id.menu_stamina){
            Intent stamina = new Intent(this, Stamina.class);
            startActivity(stamina);
        }else if (id == R.id.menu_mundos){
            Intent mundos = new Intent(this, Mundos.class);
            startActivity(mundos);
        }else if(id == R.id.menu_criaturas){
            Intent criaturas = new Intent(this, Criaturas.class);
            startActivity(criaturas);
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
        }
        return false;
    }
}

