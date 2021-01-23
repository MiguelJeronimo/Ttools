package com.example.ttools;

import android.os.Bundle;

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



        }




    }
}