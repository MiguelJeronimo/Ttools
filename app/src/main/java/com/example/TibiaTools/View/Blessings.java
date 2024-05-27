package com.example.TibiaTools.View;

import android.os.Bundle;

import com.example.TibiaTools.Operaciones.calcularBlessings;
import com.example.ttools.R;
import com.example.ttools.databinding.ActivityBlessingsBinding;
import com.google.android.material.materialswitch.MaterialSwitch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;


import java.text.DecimalFormat;

public class Blessings extends AppCompatActivity implements View.OnClickListener{

    private AppBarConfiguration appBarConfiguration;
    private ActivityBlessingsBinding binding;
    /** Declaracion de los objetos de los componentes**/
    TextView spiritual, embrace, suns, solitude, phoenix, twits_of_fate, heart, blood,total;
    EditText nivel;
    Button calcular;
    MaterialSwitch swtich_heart, swtich_blood;
    calcularBlessings c = new calcularBlessings();
    // para formatear numeros a formato de dinero.
    DecimalFormat decimalFormat = new DecimalFormat("###,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBlessingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Aparicion del boton regresar en el action bar
        /**Instanciaremos los objetos de los componentes**/
        nivel = (EditText)findViewById(R.id.Nivel);
        spiritual = (TextView)findViewById(R.id.spiritual);
        embrace = (TextView)findViewById(R.id.Embrace);
        suns = (TextView)findViewById(R.id.Suns);
        solitude = (TextView)findViewById(R.id.Solicitude);
        phoenix = (TextView)findViewById(R.id.Phoenix);
        twits_of_fate = (TextView)findViewById(R.id.Twits);
        heart = (TextView)findViewById(R.id.Heart_mountain);
        blood = (TextView)findViewById(R.id.Blood_moutain);
        total = (TextView)findViewById(R.id.Total);
        /**Boton y Switches**/
        calcular = (Button)findViewById(R.id.calcular);
        calcular.setOnClickListener(this);

        swtich_heart = findViewById(R.id.switch1);
        swtich_heart.setOnClickListener(this);

        swtich_blood = findViewById(R.id.switch2);
        swtich_blood.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.calcular){
            String nivel_personaje =  nivel.getText().toString();
            if (nivel_personaje.equalsIgnoreCase("")){
                Toast.makeText(this,"Favor de ingresar el nivel",Toast.LENGTH_SHORT).show();
            } else{
                int Nivel = Integer.parseInt(nivel_personaje);
                // Blessings individual (costo unitario de las bless principales)
                c.blessingIndividual(Nivel);
                //Calculo de la Twist of Fate
                c.blessingTwistOfFate(Nivel);
                //Suma de las 5 bless principales
                c.sumaBlessingsPrincipales(Nivel);
                //formateamos el costo a formato de moneda
                String blessing_individual = decimalFormat.format(c.getBlessingIndividual());
                String blessing_twits_of_fate = decimalFormat.format(c.getblessingTwistOfFate());
                //String total_blessings_principales = decimalFormat.format(c.getSumaBlessingsPrincipales() + c.getblessingTwistOfFate());
                /**UNA VEZ VALIDADO EL CAMPO DE TEXTO, IMPRIMIMOS LOS RESULTADOS EN LAS ETIQUETAS CORRESPONDIENTES**/
                spiritual.setText(blessing_individual);
                embrace.setText(blessing_individual);
                suns.setText(blessing_individual);
                solitude.setText(blessing_individual);
                phoenix.setText(blessing_individual);
                twits_of_fate.setText(blessing_twits_of_fate);
            }

        }

        if (swtich_blood.isChecked()){//switch de Heart of muntain esta checkeado
            String nivel_personaje =  nivel.getText().toString();
            if (nivel_personaje.equalsIgnoreCase("")){
                Toast.makeText(this,"Favor de ingresar el nivel",Toast.LENGTH_SHORT).show();
            } else {
                int Nivel = Integer.parseInt(nivel_personaje);
                c.blessingEspecial(Nivel);
                int get_especial = c.getBlessingEspecial();
                c.setHeartOfMountain(get_especial);
                //formateamos el costo a formato de moneda
                String blessing_especial = decimalFormat.format(c.getBlessingEspecial());
                blood.setText(blessing_especial);
            }
        } else {
            //hacer en caso de que no este checkiado
            blood.setText("");
            c.setHeartOfMountain(0);
        }


        if (swtich_heart.isChecked()){// swtich de Blood of mountains esta checkeado.
            String nivel_personaje =  nivel.getText().toString();
            if (nivel_personaje.equalsIgnoreCase("")){
                Toast.makeText(this,"Favor de ingresar el nivel",Toast.LENGTH_SHORT).show();
            } else {
                int Nivel = Integer.parseInt(nivel_personaje);
                c.blessingEspecial(Nivel);
                int get_especial = c.getBlessingEspecial();
                c.setBloodOfMountain(get_especial);
                //formateamos el costo a formato de moneda
                String blessing_especial = decimalFormat.format(c.getBlessingEspecial());
                heart.setText(blessing_especial);

            }
        } else{
            //hacer en caso de que no este chequiado
            heart.setText("");
            c.setBloodOfMountain(0);
        }

        String total_blessing = decimalFormat.format(c.getSumaBlessingsPrincipales() + c.getblessingTwistOfFate()+c.getHeartOfMountain()+c.getBloodOfMountain());
        // imprimiendo los totales en las etiquetas de total de blessings opcionales y el total de todas las blessings
        total.setText(total_blessing);
    }
}