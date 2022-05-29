package com.example.ttools;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.APISERVER.models.CharactersInformation.APIServicesTibia;
import com.example.ttools.APISERVER.models.CharactersInformation.Characters.Characters;
import com.example.ttools.utilidades.ConvertidorFecha;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class characters extends AppCompatActivity implements View.OnClickListener {

    private EditText nombre_persona;
    private TextView nombre,titulo, sexo, vocacion, nivel, archiviement, mundo, residencia, guild, lastlogin, comentario, textViewPremium,textViewMirried;
    Button btnenviar;
    ConvertidorFecha convertidorFecha = new ConvertidorFecha();
    LinearLayout linearLayoutDeaths, linearLayoutOtherCharacters, linearLayoutHouses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar

        //inicializando los componentes de la interfaz
        nombre_persona = (EditText) findViewById(R.id.editTextTextPersonName);
        nombre = (TextView)findViewById(R.id.nombre);
        titulo = (TextView) findViewById(R.id.titulo);
        sexo = (TextView) findViewById(R.id.sexo);
        vocacion = (TextView) findViewById(R.id.vocacion);
        nivel = (TextView) findViewById(R.id.nivel);
        archiviement = (TextView) findViewById(R.id.achivement);
        mundo = (TextView) findViewById(R.id.mundo);
        residencia = (TextView) findViewById(R.id.residencia);
        guild = (TextView) findViewById(R.id.guild);
        lastlogin = (TextView) findViewById(R.id.last_loguin);
        comentario= (TextView) findViewById(R.id.comentario);
        textViewPremium = (TextView) findViewById(R.id.textViewPremium);
        textViewMirried = (TextView) findViewById(R.id.textViewMirried);
        btnenviar = (Button)findViewById(R.id.btnenviar);
        btnenviar.setOnClickListener(this);

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

        String urlAPI = "https://api.tibiadata.com/v3/character/";
        linearLayoutDeaths = (LinearLayout) findViewById(R.id.linearLayoutDeaths);
        linearLayoutOtherCharacters = (LinearLayout) findViewById(R.id.linearLayoutOtherCharacters);
        linearLayoutHouses = (LinearLayout) findViewById(R.id.linearLayoutHouses);
        linearLayoutOtherCharacters.removeAllViews();
        linearLayoutDeaths.removeAllViews();
        linearLayoutHouses.removeAllViews();
        textViewMirried.setText("");
        if (v.getId() == R.id.btnenviar){

            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(urlAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TibiaAPIServer services = retrofit.create(TibiaAPIServer.class);
            Call <APIServicesTibia> repo = services.getPersonajes(nombre_persona.getText().toString());
            repo.enqueue(new Callback<APIServicesTibia>() {
                @Override
                public void onResponse(Call<APIServicesTibia> call, Response<APIServicesTibia> response) {
                    APIServicesTibia apiServicesTibia = response.body();
                    Characters characters = apiServicesTibia.getCharacters();
                    nombre.setText(characters.getCharacter().getName());
                    titulo.setText(characters.getCharacter().getTitle());
                    sexo.setText(characters.getCharacter().getSex());
                    vocacion.setText(characters.getCharacter().getVocation());
                    nivel.setText(String.valueOf(characters.getCharacter().getLevel()));
                    archiviement.setText(String.valueOf(characters.getCharacter().getAchievement_points()));
                    mundo.setText(characters.getCharacter().getWorld());
                    residencia.setText(characters.getCharacter().getResidence());
                    guild.setText(characters.getCharacter().getGuild().getRank()+" of the "+characters.getCharacter().getGuild().getName());
                    convertidorFecha.setExpiryDateString(characters.getCharacter().getLast_login());
                    convertidorFecha.convertirFecha();
                    lastlogin.setText(convertidorFecha.getFechaConvertida());
                    comentario.setText(characters.getCharacter().getComment());
                    textViewPremium.setText(characters.getCharacter().getAccount_status());
                    if (characters.getCharacter().getMarried_to()!=null){
                        textViewMirried.setText("\uD83D\uDC8D️\u200D\uD83D\uDD25: "+characters.getCharacter().getMarried_to());
                    }
                    if (characters.getCharacter().getHouses() != null){
                        for (int i = 0; i < characters.getCharacter().getHouses().size(); i++) {
                            // System.out.println(characters.getDeaths().get(i).getReason());
                            TextView textViewHouses = new TextView(characters.this);
                            textViewHouses.setText("\uD83C\uDFD8️"+" Name:"
                                    +characters.getCharacter().getHouses().get(i).getName()+
                                    "\n Town: "+characters.getCharacter().getHouses().get(i).getTown()+
                                    "\n Paid: "+characters.getCharacter().getHouses().get(i).getPaid());
                            textViewHouses.setTextColor(getResources().getColor(R.color.leyenda));
                            textViewHouses.setTextSize(15);
                            textViewHouses.setTextColor(Color.parseColor("#CE93D8"));
                            textViewHouses.setTypeface(null, Typeface.ITALIC);
                            textViewHouses.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            linearLayoutHouses.addView(textViewHouses);
                        }
                    }

                   if (characters.getDeaths() != null){
                       for (int i = 0; i < characters.getDeaths().size(); i++) {
                           // System.out.println(characters.getDeaths().get(i).getReason());
                           TextView textViewWeakness = new TextView(characters.this);
                           textViewWeakness.setText("☠️️"+" "+characters.getDeaths().get(i).getReason());
                           textViewWeakness.setTextColor(getResources().getColor(R.color.leyenda));
                           textViewWeakness.setTextSize(15);
                           textViewWeakness.setTextColor(Color.parseColor("#CE93D8"));
                           textViewWeakness.setTypeface(null, Typeface.ITALIC);
                           textViewWeakness.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                           linearLayoutDeaths.addView(textViewWeakness);
                       }
                   }
                   if (characters.getOther_characters()!=null){
                       for (int i = 0; i < characters.getOther_characters().size(); i++) {
                           TextView textViewOther = new TextView(characters.this);
                           // System.out.println(characters.getDeaths().get(i).getReason());
                           textViewOther.setText("\uD83D\uDC4A\uD83C\uDFFE"+" "
                                   +characters.getOther_characters().get(i).getName()+
                                   "\nMain: "+characters.getOther_characters().get(i).getMain()+
                                   "\nWorld: "+characters.getOther_characters().get(i).getWorld()+
                                   "\nStatus: "+characters.getOther_characters().get(i).getStatus()+
                                   "\nDelete: "+characters.getOther_characters().get(i).getDeleted());
                           if (characters.getOther_characters().get(i).getStatus().equals("online")){
                               textViewOther.setTextColor(Color.GREEN);
                           } else{
                               textViewOther.setTextColor(Color.parseColor("#E53935"));
                           }
                           textViewOther.setTextSize(15);
                           textViewOther.setTypeface(null, Typeface.ITALIC);
                           textViewOther.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                           linearLayoutOtherCharacters.addView(textViewOther);
                       }
                   }
                }

                @Override
                public void onFailure(Call<APIServicesTibia> call, Throwable t) {

                }
            });

        }

    }
}