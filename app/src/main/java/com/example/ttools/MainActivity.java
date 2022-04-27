package com.example.ttools;

import android.content.Intent;
import android.os.Bundle;

import com.example.ttools.Operaciones.calcularBlessings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
/** Declaracion de los objetos de los componentes**/

    TextView spiritual, embrace, suns, solitude, phoenix, twits_of_fate, heart, blood,total;

    EditText nivel;

    Button calcular;

    Switch swtich_heart, swtich_blood;

    
    
    
    
    calcularBlessings c = new calcularBlessings();

    // para formatear numeros a formato de dinero.
    DecimalFormat decimalFormat = new DecimalFormat("###,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**Instanciaremos los objetos de los componentes**/
        nivel = (EditText)findViewById(R.id.Nivel);
  /**      spiritual = (TextView)findViewById(R.id.spiritual);




        embrace = (TextView)findViewById(R.id.Embrace);
        suns = (TextView)findViewById(R.id.Suns);
        solitude = (TextView)findViewById(R.id.Solicitude);
        phoenix = (TextView)findViewById(R.id.Phoenix);
        twits_of_fate = (TextView)findViewById(R.id.Twits);
        heart = (TextView)findViewById(R.id.Heart_mountain);
        blood = (TextView)findViewById(R.id.Blood_moutain);
        total = (TextView)findViewById(R.id.Total);**/
        /**Boton y Switches**/
        calcular = (Button)findViewById(R.id.calcular);
        calcular.setOnClickListener(this);

        swtich_heart = (Switch)findViewById(R.id.switch1);
        swtich_heart.setOnClickListener(this);

        swtich_blood = (Switch)findViewById(R.id.switch2);
        swtich_blood.setOnClickListener(this);
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
        }/**else if (id == R.id.menu_mundos){
            Intent mundos = new Intent(this, Mundos.class);
            startActivity(mundos);
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.calcular){

            String nivel_personaje =  nivel.getText().toString();

            if (nivel_personaje.equalsIgnoreCase("")){

                Toast.makeText(this,"Favor de ingresar el nivel",Toast.LENGTH_SHORT).show();

            } else{
                int Nivel = Integer.parseInt(nivel_personaje);

                    // Blessings individual (costo unitario de las bless principales y twits of fate)
                    c.blessingIndividual(Nivel);

                    //Suma de las 5 bless principales
                    c.sumaBlessingsPrincipales(Nivel);

                    //formateamos el costo a formato de moneda
                    String blessing_individual = decimalFormat.format(c.getBlessingIndividual());
                    String total_blessings_principales = decimalFormat.format(c.getSumaBlessingsPrincipales() + c.getBlessingIndividual());

                    /**UNA VEZ VALIDADO EL CAMPO DE TEXTO, IMPRIMIMOS LOS RESULTADOS EN LAS ETIQUETAS CORRESPONDIENTES**/
                    spiritual.setText(blessing_individual);
                    embrace.setText(blessing_individual);
                    suns.setText(blessing_individual);
                    solitude.setText(blessing_individual);
                    phoenix.setText(blessing_individual);
                    twits_of_fate.setText(blessing_individual);
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

        String total_blessing = decimalFormat.format(c.getSumaBlessingsPrincipales() + c.getBlessingIndividual()+c.getHeartOfMountain()+c.getBloodOfMountain());

        // imprimiendo los totales en las etiquetas de total de blessings opcionales y el total de todas las blessings
        total.setText(total_blessing);

    }

    }

