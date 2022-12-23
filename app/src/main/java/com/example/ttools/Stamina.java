package com.example.ttools;

import android.os.Bundle;

import com.example.ttools.Operaciones.staminaTibia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.ttools.Operaciones.staminaTibia;

public class Stamina extends AppCompatActivity implements View.OnClickListener {

    EditText tiempo;
    TextView lbHora, lbMinutos,lbmedio;
    Button btnalcular;
    //Objeto de la clase Stamina
    staminaTibia Stamina = new staminaTibia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamina);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        tiempo = findViewById(R.id.Tiempo);
        btnalcular = findViewById(R.id.calcular);
        btnalcular.setOnClickListener(this);
        lbHora = findViewById(R.id.labelHora);
        lbmedio = findViewById(R.id.medio);
        lbMinutos = findViewById(R.id.lableMinutos);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Evento para darle accion al boton regresar de nuestra interfaz
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
        if (v.getId() == R.id.calcular){
            String txtTiempo =  tiempo.getText().toString();
            //objetos para la expresiones regulares
            Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-9]|3[0-9]|4[0-2]):[0-5][0-9]$"); //Patron para que acepte tiempo hasta 42:00
            Matcher matcher = pattern.matcher(txtTiempo);
            //pattern.matcher(txtTiempo);
            if (matcher.matches()){//si la expresión entra dentro del patrón
            //Vamos a partir la cadena para optener las horas y los minutos
                String  particion [] = txtTiempo.split(":");
                int horas, minutos, horaReal, minutosReales;
                horas = Integer.parseInt(particion[0]);
                minutos = Integer.parseInt(particion[1]);
                if (horas > 0 && horas <= 42){ //42 significa que la stimana esta llena
                   Stamina.convertirHoraMinutosStamina(horas,minutos);
                   int minutoStamina = Stamina.getConvertirHoraMinutosStamina();
                   Stamina.minutoStamina(minutoStamina);
                   int tiempoStamina = Stamina.getminutoStamina();
                   Stamina.convertirMinutosHora(tiempoStamina);
                   horaReal = Stamina.getHorasReales();
                   minutosReales = Stamina.getMinutosReales();
                   lbHora.setText(String.valueOf(horaReal));
                   lbmedio.setText(":");
                   lbMinutos.setText(String.valueOf(minutosReales));
                }
            } else {
                Snackbar.make(v, "El dato ingresado no es valido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }
}