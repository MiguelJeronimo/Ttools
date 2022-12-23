package com.example.ttools.utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Spinners {
    /**
     * @return regresa un array con las vocaciones
     * */
    public ArrayList<String> spinnerVocations(){
        ArrayList<String> arrayVocations = new ArrayList<>();
        arrayVocations.add("all");
        arrayVocations.add("Knights");
        arrayVocations.add("Paladins");
        arrayVocations.add("Sorcerers");
        arrayVocations.add("Druids");
        return arrayVocations;
    }
    /**
     * @return categorias del tipo de skill y algunos bonos
     * */
    public ArrayList<String> spinnerCategory(){
        /*
        *  achievements ┃ axefighting ┃ charmpoints ┃ clubfighting
        * ┃ distancefighting ┃ experience ┃ fishing ┃ fistfighting
        * ┃ goshnarstaint ┃ loyaltypoints ┃ magiclevel ┃ shielding
        * ┃ swordfighting ┃ dromescore ┃ bosspoints
        * */
        //Al acceder a los datos del array, hay que quitarle el espacio
        ArrayList<String> arrayCategory = new ArrayList<>();
        arrayCategory.add("Experience");
        arrayCategory.add("Axe fighting");
        arrayCategory.add("Club fighting");
        arrayCategory.add("Sword fighting");
        arrayCategory.add("Distance fighting");
        arrayCategory.add("Magic level");
        arrayCategory.add("shielding");
        arrayCategory.add("Fist fighting");
        arrayCategory.add("Fishing");
        arrayCategory.add("Achievements");
        arrayCategory.add("Charm points");
        arrayCategory.add("Goshnars taint");
        arrayCategory.add("Loyalty points");
        arrayCategory.add("Drome score");
        arrayCategory.add("Boss points");
        return arrayCategory;
    }

    /**
     *
     * @return Devuelve un ArrayList con la data de las ciudades leidas desde un archivo de texto
     * */
    public ArrayList<String> LeerDataCitys(InputStream documento) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(documento));
        StringBuilder stringBuilder = new StringBuilder();
        String data;
        ArrayList<String> citys = new ArrayList<>();
        while ((data  = bufferedReader.readLine())!=null){
            citys.add(data);
        }
        return citys;
    }
}
