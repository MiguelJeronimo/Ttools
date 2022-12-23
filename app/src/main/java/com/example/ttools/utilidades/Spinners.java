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
    public ArrayList<String> spinnerVocations(InputStream documento){
        ArrayList<String> arrayVocations = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(documento));
        StringBuilder stringBuilder = new StringBuilder();
        String data = null;
        ArrayList<String> categories = new ArrayList<>();
        while (true){
            try {
                if ((data = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            arrayVocations.add(data);
        }

        return arrayVocations;
    }
    /**
     * @return categorias del tipo de skill y algunos bonos
     * */
    public ArrayList<String> spinnerCategory(InputStream documento){
        /*
        *  achievements ┃ axefighting ┃ charmpoints ┃ clubfighting
        * ┃ distancefighting ┃ experience ┃ fishing ┃ fistfighting
        * ┃ goshnarstaint ┃ loyaltypoints ┃ magiclevel ┃ shielding
        * ┃ swordfighting ┃ dromescore ┃ bosspoints
        * */
        //Al acceder a los datos del array, hay que quitarle el espacio
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(documento));
        StringBuilder stringBuilder = new StringBuilder();
        String data = null;
        ArrayList<String> categories = new ArrayList<>();
        while (true){
            try {
                if ((data = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            categories.add(data);
        }
        return categories;
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
