package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;

import com.example.ttools.Operaciones.ExperienciaCompartida;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class experiencia_compartida extends AppCompatActivity implements View.OnClickListener {

    EditText txtnivel;
    Button calcular;
    TextView rangoMenor, rangoMayor;

    ExperienciaCompartida exp = new ExperienciaCompartida();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencia_compartida);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar

        txtnivel = (EditText)findViewById(R.id.Nivel);
        calcular = (Button) findViewById(R.id.calcular);
        calcular.setOnClickListener(this);
        rangoMenor = (TextView)findViewById(R.id.Menor);
        rangoMayor = (TextView)findViewById(R.id.Mayor);


      /**  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });**/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.calcular){

            String nivel = txtnivel.getText().toString();
            if (nivel.equals("")){
                Toast.makeText(this, "Debe ingresar el nivel", Toast.LENGTH_SHORT).show();
                rangoMayor.setText("-");
                rangoMenor.setText("-");

            } else{
                double n = Double.parseDouble(nivel);
                exp.CalculoRangoMayor(n);
                exp.CalculoRangoMenor(n);
                String rmayor = Integer.toString(exp.getRangoMayor());
                String rmenor = Integer.toString(exp.getRangoMenor());
                rangoMayor.setText(rmayor);
                rangoMenor.setText(rmenor);

                //txtnivel.setText("");

            }


        }



    }
}