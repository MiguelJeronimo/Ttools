package com.example.ttools;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ttools.APISERVER.TibiaAPIServer;
import com.example.ttools.Operaciones.APIServicesTibia;
import com.example.ttools.Operaciones.information.Characters;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class characters extends AppCompatActivity implements View.OnClickListener {

    private EditText nombre_persona;
    private TextView nombre,titulo, sexo, vocacion, nivel, archiviement, mundo, residencia, guild, lastlogin, comentario;
    Button btnenviar;

    Gson gson;

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
                    guild.setText(characters.getCharacter().getGuild().getName()+" : "+characters.getCharacter().getGuild().getRank());

                    //System.out.println(characters.getCharacter().getName());


                }

                @Override
                public void onFailure(Call<APIServicesTibia> call, Throwable t) {

                }
            });

        }

    }
}