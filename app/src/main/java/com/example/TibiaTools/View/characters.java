package com.example.TibiaTools.View;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.APIServicesTibia;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Achievements;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Houses.House;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.OtherCharacters;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.Characters;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;
import com.example.TibiaTools.recyclerview.Adapters.AdapterArchievementsCharacter;
import com.example.TibiaTools.recyclerview.Adapters.AdapterHouseCharacters;
import com.example.TibiaTools.recyclerview.Adapters.AdapterOtherCharacters;
import com.example.TibiaTools.recyclerview.ItemsArchievementsCharacter;
import com.example.TibiaTools.recyclerview.ItemsCharacters;
import com.example.TibiaTools.recyclerview.ItemsHousesCharacters;
import com.example.TibiaTools.utilidades.ConvertidorFecha;
import com.example.TibiaTools.utilidades.IsVisibillityCharacters;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityCharactersBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class characters extends AppCompatActivity implements View.OnClickListener {
    ActivityCharactersBinding binding;
    private EditText nombre_persona;
    private TextView nombre,titulo, sexo, vocacion, nivel, archiviement, mundo, residencia, guild, lastlogin, comentario, textViewPremium,textViewMirried, textViewLoyalty,textViewCreated;
    Button btnenviar;
    ConvertidorFecha convertidorFecha = new ConvertidorFecha();
    LinearLayout linearLayoutDeaths;
    RecyclerView linearLayoutHouses, linearLayoutOtherCharacters, linearLayoutAchievements;
    AdapterHouseCharacters adapterHouseCharacters;
    List<ItemsHousesCharacters> itemsHousesCharacters;
    AdapterOtherCharacters adapterOtherCharacters;
    List<ItemsCharacters> itemsCharacters;
    AdapterArchievementsCharacter adapterArchievementsCharacter;
    List<ItemsArchievementsCharacter> itemsArchievementsCharacters;
    InstanciaRetrofit services = new InstanciaRetrofit();
    IsVisibillityCharacters isVisibillityCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCharactersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        //inicializando los componentes de la interfaz
        nombre_persona = findViewById(R.id.editTextTextPersonName);
        nombre = findViewById(R.id.nombre);
        titulo = findViewById(R.id.titulo);
        sexo = findViewById(R.id.sexo);
        vocacion = findViewById(R.id.vocacion);
        nivel = findViewById(R.id.nivel);
        archiviement = findViewById(R.id.achivement);
        mundo = findViewById(R.id.mundo);
        residencia = findViewById(R.id.residencia);
        guild = findViewById(R.id.guild);
        lastlogin = findViewById(R.id.last_loguin);
        comentario= findViewById(R.id.comentario);
        textViewPremium = findViewById(R.id.textViewPremium);
        textViewMirried = findViewById(R.id.textViewMirried);
        textViewLoyalty = findViewById(R.id.textViewLoyalty);
        textViewCreated = findViewById(R.id.textViewCreated);
        btnenviar = findViewById(R.id.btnenviar);
        btnenviar.setOnClickListener(this);
        linearLayoutHouses = findViewById(R.id.linearLayoutHouses);
        linearLayoutOtherCharacters = findViewById(R.id.linearLayoutOtherCharacters);
        linearLayoutAchievements = findViewById(R.id.linearLayoutAchievements);
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


    /**Logica del programa para hacer la conexion a la API de tibia*/
    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.btnenviar) {
            if (nombre_persona.getText().toString().equals("")) {
                Snackbar.make(v, "Ingrese el nombre del personaje", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                infoCharacters(nombre_persona.getText().toString());
            }

        }
    }

    public void infoCharacters(String nombre_persona){
        isVisibillityCharacters = new IsVisibillityCharacters();
        linearLayoutDeaths = findViewById(R.id.linearLayoutDeaths);
        linearLayoutOtherCharacters.removeAllViews();
        linearLayoutDeaths.removeAllViews();
        linearLayoutHouses.removeAllViews();
        textViewMirried.setText("");
        textViewLoyalty.setText("");
        textViewCreated.setText("");
        isVisibillityCharacters.setVisibility(false);
        isVisibillityCharacters.VisibilityDataGeneral(binding);
        binding.getRoot().findViewById(R.id.carga_characters).setVisibility(View.VISIBLE);
        binding.getRoot().findViewById(R.id.CardStatus).setVisibility(View.GONE);
        linearLayoutDeaths.setVisibility(View.GONE);
        linearLayoutHouses.setVisibility(View.GONE);
        linearLayoutAchievements.setVisibility(View.GONE);
        linearLayoutOtherCharacters.setVisibility(View.GONE);
        String urlAPI = "https://api.tibiadata.com/v3/character/";
            TibiaAPIServer tibiaAPIServer = services.getRetrofit(urlAPI).create(TibiaAPIServer.class);
            Call <APIServicesTibia> repo = tibiaAPIServer.getPersonajes(nombre_persona);
            repo.enqueue(new Callback<APIServicesTibia>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<APIServicesTibia> call, Response<APIServicesTibia> response) {
                    if (response.isSuccessful()) {
                        APIServicesTibia apiServicesTibia = response.body();
                        Characters characters = apiServicesTibia.getCharacters();
                        if (characters.getCharacter().getName().equals("")) {
                            Snackbar.make(findViewById(R.id.btnenviar), "No se encontro el personaje", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        } else {
                            nombre.setText(characters.getCharacter().getName());
                            nombre.setVisibility(View.VISIBLE);
                            titulo.setText(characters.getCharacter().getTitle());
                            sexo.setText(characters.getCharacter().getSex());
                            vocacion.setText(characters.getCharacter().getVocation());
                            nivel.setText(String.valueOf(characters.getCharacter().getLevel()));
                            archiviement.setText(String.valueOf(characters.getCharacter().getAchievement_points()));
                            mundo.setText(characters.getCharacter().getWorld());
                            residencia.setText(characters.getCharacter().getResidence());
                            String guildRank = characters.getCharacter().getGuild().getRank();
                            String nameGuild = characters.getCharacter().getGuild().getName();
                            if (guildRank != null && nameGuild != null){
                                guild.setText(characters.getCharacter().getGuild().getRank() + " of the " + characters.getCharacter().getGuild().getName());
                            } else{
                                guild.setText("No pertenece a una guild");
                            }
                            convertidorFecha.setExpiryDateString(characters.getCharacter().getLast_login());
                            convertidorFecha.convertirFecha();
                            lastlogin.setText(convertidorFecha.getFechaConvertida());
                            comentario.setText(characters.getCharacter().getComment());
                            textViewPremium.setText(characters.getCharacter().getAccount_status());
                            isVisibillityCharacters.setVisibility(true);
                            isVisibillityCharacters.VisibilityDataGeneral(binding);
                            binding.getRoot().findViewById(R.id.CardStatus).setVisibility(View.VISIBLE);
                            if (characters.getCharacter().getMarried_to() != null) {
                                textViewMirried.setText("\uD83D\uDC8D️\u200D\uD83D\uDD25: " + characters.getCharacter().getMarried_to());
                            }
                            if (characters.getCharacter().getHouses() != null) {
                                RecyclerHouse(characters.getCharacter().getHouses());
                            }

                            if (characters.getDeaths() != null) {
                                for (int i = 0; i < characters.getDeaths().size(); i++) {
                                    // System.out.println(characters.getDeaths().get(i).getReason());
                                    TextView textViewWeakness = new TextView(characters.this);
                                    convertidorFecha.setExpiryDateString(characters.getDeaths().get(i).getTime());
                                    convertidorFecha.convertirFecha();
                                    textViewWeakness.setText("☠️️" + " " + convertidorFecha.getFechaConvertida() + " - " + characters.getDeaths().get(i).getReason());
                                    textViewWeakness.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.md_theme_light_primary));
                                    textViewWeakness.setTextSize(15);
                                    textViewWeakness.setTextColor(Color.parseColor("#CE93D8"));
                                    textViewWeakness.setTypeface(null, Typeface.ITALIC);
                                    textViewWeakness.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    linearLayoutDeaths.addView(textViewWeakness);
                                    linearLayoutDeaths.setVisibility(View.VISIBLE);
                                }
                            }
                            if (characters.getOther_characters() != null) {
                                RecyclerCharacters(characters.getOther_characters());
                            }

                            if (characters.getAchievements() != null) {
                                RecyclerArchievements(characters.getAchievements());
                            }
                            /*
                             * Llenar los campos de AccountInformation
                             * */
                            if (characters.getAccount_information() != null) {
                                if (characters.getAccount_information().getLoyalty_title() != null){
                                    textViewLoyalty.setText("Loyalty Title: "+characters.getAccount_information().getLoyalty_title());
                                }

                                if (characters.getAccount_information().getCreated() != null){
                                    convertidorFecha.setExpiryDateString(characters.getAccount_information().getCreated());
                                    convertidorFecha.convertirFecha();
                                    textViewCreated.setText("Created: "+convertidorFecha.getFechaConvertida());
                                }
                            }
                        }
                    } else{
                        Snackbar.make(findViewById(R.id.btnenviar), "Error al conectar con la API", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    binding.getRoot().findViewById(R.id.carga_characters).setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<APIServicesTibia> call, Throwable t) {
                    Log.i("Error en la conexion", t.getMessage());
                    Toast.makeText(characters.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    binding.getRoot().findViewById(R.id.carga_characters).setVisibility(View.GONE);
                }
            });
    }
    //llenado de recyclerviews
    public void RecyclerHouse(ArrayList<House> houses){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        itemsHousesCharacters = new ArrayList<>();
        for (int i = 0; i < houses.size(); i++) {
            itemsHousesCharacters.add(new ItemsHousesCharacters(
                houses.get(i).getName(),
                houses.get(i).getTown(),
                houses.get(i).getPaid(),
                houses.get(i).getHouseid()
            ));
        }
        linearLayoutHouses.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutHouses.setLayoutManager(linearLayoutManager);
        adapterHouseCharacters = new AdapterHouseCharacters(itemsHousesCharacters);
        linearLayoutHouses.setAdapter(adapterHouseCharacters);
        linearLayoutHouses.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void RecyclerCharacters(ArrayList<OtherCharacters> otherCharacters){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        itemsCharacters = new ArrayList<>();
        otherCharacters.forEach(characters ->{
            itemsCharacters.add(new ItemsCharacters(
                    characters.getName(),
                    characters.getWorld(),
                    characters.getStatus(),
                    characters.getDeleted(),
                    characters.getMain(),
                    characters.getTraded()
            ));
        });
        linearLayoutOtherCharacters.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutOtherCharacters.setLayoutManager(linearLayoutManager);
        adapterOtherCharacters = new AdapterOtherCharacters(itemsCharacters);
        linearLayoutOtherCharacters.setAdapter(adapterOtherCharacters);
        linearLayoutOtherCharacters.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void RecyclerArchievements(ArrayList<Achievements> achievements){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        itemsArchievementsCharacters = new ArrayList<>();
        achievements.forEach(achievement ->{
            itemsArchievementsCharacters.add(new ItemsArchievementsCharacter(
                    achievement.getName(),
                    achievement.getGrade(),
                    achievement.isSecret()
            ));
        });
        linearLayoutAchievements.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutAchievements.setLayoutManager(linearLayoutManager);
        adapterArchievementsCharacter = new AdapterArchievementsCharacter(itemsArchievementsCharacters);
        linearLayoutAchievements.setAdapter(adapterArchievementsCharacter);
        linearLayoutAchievements.setVisibility(View.VISIBLE);
    }
}