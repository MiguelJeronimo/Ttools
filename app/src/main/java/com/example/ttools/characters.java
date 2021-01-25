package com.example.ttools;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        if (id == android.R.id.home) { //aqui daremos el evento al boton regresar de nuestro action bar, haciendo uso de los ids del sistema android
            finish();// finalizamos la actividad
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**Logica del programa para hacer la conexion a la API de tibia*/
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnenviar){
            final String nombre_personaje = nombre_persona.getText().toString();
            String a [] = nombre_personaje.split(" ");
            // System.out.println(a[0]+"%20"+a[1]);
            String nombrep = a[0];
            //String apellido = a[1];


            final RequestQueue requestQueue = Volley.newRequestQueue(characters.this);
            //Validaremos el campo del nombre
            String url = null;


            if (nombre_personaje.equalsIgnoreCase("")){
                // Toast.makeText(this, "Ingrese el nombre del personaje", Toast.LENGTH_SHORT).show();

                Snackbar.make(null,"Ingrese el nombre de personaje", Snackbar.LENGTH_LONG).setAction("Action", null).show();


            } else if(a.length == 1) {
                url = "https://api.tibiadata.com/v2/characters/"+nombre_personaje+"%20.json";}
            else if(a.length==3){
                url = "https://api.tibiadata.com/v2/characters/"+nombrep+"%20"+a[1]+"%20"+a[2]+".json";
            }

            else {

                url = "https://api.tibiadata.com/v2/characters/"+nombrep+"%20"+a[1]+".json";
            }


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            try {
                                // text.setText();

                                String snombre_persona;
                                String stitulo;
                                String ssexo;
                                String svocacion;
                                String snivel;
                                String sarchiviement;
                                String smundo;
                                String sresidencia;
                                String sguild;
                                String slastlogin;
                                String scomentario;
                                String zonahoraria;
                                String rango;
                                boolean serror;
                                snombre_persona = response.getJSONObject("characters").getJSONObject("data").getString("name").toString();
                                stitulo = response.getJSONObject("characters").getJSONObject("data").getString("title").toString();
                                ssexo = response.getJSONObject("characters").getJSONObject("data").getString("sex").toString();
                                svocacion = response.getJSONObject("characters").getJSONObject("data").getString("vocation").toString();
                                snivel = response.getJSONObject("characters").getJSONObject("data").getString("level").toString();
                                sarchiviement = response.getJSONObject("characters").getJSONObject("data").getString("achievement_points").toString();
                                smundo = response.getJSONObject("characters").getJSONObject("data").getString("world").toString();
                                sresidencia = response.getJSONObject("characters").getJSONObject("data").getString("residence").toString();
                                slastlogin = response.getJSONObject("characters").getJSONObject("data").getJSONArray("last_login").getJSONObject(0).get("date").toString();

                                // serror = response.getJSONObject("characters").getJSONObject("data").has("guild");
                                if (response.getJSONObject("characters").getJSONObject("data").optString("comment") == "" && response.getJSONObject("characters").getJSONObject("data").optString("guild") == "") {//en caso de que no tenga guild ni comentarios
                                    guild.setText("No tiene guild");
                                    comentario.setText("No tiene comentario");
                                    nombre.setText(snombre_persona);
                                    titulo.setText(stitulo);
                                    sexo.setText(ssexo);
                                    vocacion.setText(svocacion);
                                    nivel.setText(snivel);
                                    archiviement.setText(sarchiviement);
                                    mundo.setText(smundo);
                                    residencia.setText(sresidencia);
                                    lastlogin.setText(slastlogin);

                                } else if (response.getJSONObject("characters").getJSONObject("data").optString("comment") == ""){//en caso de que no tenga comentario
                                    guild.setText(response.getJSONObject("characters").getJSONObject("data").getJSONObject("guild").getString("rank")+" of the "+response.getJSONObject("characters").getJSONObject("data").getJSONObject("guild").getString("name"));

                                    comentario.setText("");
                                    nombre.setText(snombre_persona);
                                    titulo.setText(stitulo);
                                    sexo.setText(ssexo);
                                    vocacion.setText(svocacion);
                                    nivel.setText(snivel);
                                    archiviement.setText(sarchiviement);
                                    mundo.setText(smundo);
                                    residencia.setText(sresidencia);
                                    lastlogin.setText(slastlogin);
                                } else if (response.getJSONObject("characters").getJSONObject("data").optString("guild") == ""){// EN CASO DE QUE NO TENGA COMENTARIO NI GUILD
                                    guild.setText("No tiene guild");
                                    comentario.setText(response.getJSONObject("characters").getJSONObject("data").getString("comment"));
                                    nombre.setText(snombre_persona);
                                    titulo.setText(stitulo);
                                    sexo.setText(ssexo);
                                    vocacion.setText(svocacion);
                                    nivel.setText(snivel);
                                    archiviement.setText(sarchiviement);
                                    mundo.setText(smundo);
                                    residencia.setText(sresidencia);
                                    lastlogin.setText(slastlogin);
                                }

                                else{


                                    guild.setText(response.getJSONObject("characters").getJSONObject("data").getJSONObject("guild").getString("rank")+" of the "+response.getJSONObject("characters").getJSONObject("data").getJSONObject("guild").getString("name"));
                                    // sguild = response.getJSONObject("characters").getJSONObject("data").getJSONObject("guild").getString("name");
                                    //guild.setText(sguild);
                                    comentario.setText(response.getJSONObject("characters").getJSONObject("data").getString("comment"));
                                    nombre.setText(snombre_persona);
                                    titulo.setText(stitulo);
                                    sexo.setText(ssexo);
                                    vocacion.setText(svocacion);
                                    nivel.setText(snivel);
                                    archiviement.setText(sarchiviement);
                                    mundo.setText(smundo);
                                    residencia.setText(sresidencia);
                                    lastlogin.setText(slastlogin);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                //e.getMessage();

                                Snackbar.make(null, "El personaje no existe.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                guild.setText("");
                                // sguild = response.getJSONObject("characters").getJSONObject("data").getJSONObject("guild").getString("name");
                                //guild.setText(sguild);
                                comentario.setText("");
                                nombre.setText("");
                                titulo.setText("");
                                sexo.setText("");
                                vocacion.setText("");
                                nivel.setText("");
                                archiviement.setText("");
                                mundo.setText("");
                                residencia.setText("");
                                lastlogin.setText("");
                            }


                        }
                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // text.setText(error.toString());
                        }
                    });

            requestQueue.add(jsonObjectRequest);


        }




    }
}