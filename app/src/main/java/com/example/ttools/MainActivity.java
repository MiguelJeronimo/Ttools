package com.example.ttools;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
/** Declaracion de los objetos de los componentes**/

    TextView spiritual, embrace, suns, solitude, phoenix, twits_of_fate, heart, blood,total;

    EditText nivel;

    Button calcular;

    Switch swtich_heart, swtich_blood;

    calcularBlessings c = new calcularBlessings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        swtich_heart = (Switch)findViewById(R.id.switch1);
        swtich_heart.setOnClickListener(this);

        swtich_blood = (Switch)findViewById(R.id.switch2);
        swtich_blood.setOnClickListener(this);

     /**   FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });**/
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
        if (id == R.id.action_settings) {
            return true;
        }

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

                if (Nivel <= 30){
                    Toast.makeText(this, "Recuerda que si tu nivel es menor o igual a 30 tus bless valen 10K en total", Toast.LENGTH_SHORT).show();
                } else if (Nivel>=31&&Nivel<120){

                    // Blessings individual (costo unitario de las bless principales y twits of fate)
                    int bless = c.blessingIndividual(Nivel);

                    //calcularemos la suma de las  blessins princiapales
                    int suma_blessings_principales = c.sumaBlessingsPrincipales(Nivel);

                    //Precio unitario de la bless especial
                    //int bless_especial = c.blessingEspecial(Nivel);

                    spiritual.setText(Integer.toString(bless));
                    embrace.setText(Integer.toString(bless));
                    suns.setText(Integer.toString(bless));
                    solitude.setText(Integer.toString(bless));
                    phoenix.setText(Integer.toString(bless));
                    twits_of_fate.setText(Integer.toString(bless));
                    total.setText(Integer.toString( suma_blessings_principales + bless));
                    nivel.setText("");
                } else{
                    Toast.makeText(this, "Recuerda que apartir del nivel 120 tus bless empiezan a valen 100K en total",Toast.LENGTH_LONG).show();
                }
            }



        } else if (swtich_blood.isChecked()){//switch de Heart of muntain esta checkeado
            String nivel_personaje =  nivel.getText().toString();

            if (nivel_personaje.equalsIgnoreCase("")){
                Toast.makeText(this,"Favor de ingresar el nivel",Toast.LENGTH_SHORT).show();
            } else{

                int Nivel = Integer.parseInt(nivel_personaje);
                int bless_especial = c.blessingEspecial(Nivel);
                blood.setText(Integer.toString(bless_especial));
            }


        } else if (swtich_heart.isChecked()){// swtich de Blood of mountains esta checkeado.

            String nivel_personaje =  nivel.getText().toString();
            if (nivel_personaje.equalsIgnoreCase("")){
                Toast.makeText(this,"Favor de ingresar el nivel",Toast.LENGTH_SHORT).show();
            } else {

                int Nivel = Integer.parseInt(nivel_personaje);
                int bless_especial = c.blessingEspecial(Nivel);
                heart.setText(Integer.toString(bless_especial));
            }



        }


    }

   /** @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (swtich_heart.isChecked()){

            String nivel_personaje =  nivel.getText().toString();
            int Nivel = Integer.parseInt(nivel_personaje);
            int bless_especial = c.blessingEspecial(Nivel);

            heart.setText(Integer.toString(bless_especial));

        } else if (swtich_blood.isChecked()){
            String nivel_personaje =  nivel.getText().toString();
            int Nivel = Integer.parseInt(nivel_personaje);
            int bless_especial = c.blessingEspecial(Nivel);

            blood.setText(Integer.toString(bless_especial));
        }


        }**/


    }

