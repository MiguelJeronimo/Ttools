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
        String data = null;
            try {
                while ((data = bufferedReader.readLine()) != null){
                    arrayVocations.add(data);
                }
                documento.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return arrayVocations;
    }
    /**
     * @return categorias del tipo de skill y algunos bonos
     * */
    public ArrayList<String> spinnerCategory(InputStream documento){
        //Al acceder a los datos del array, hay que quitarle el espacio
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(documento));
        String data = null;
        ArrayList<String> categories = new ArrayList<>();
            try {
                while ((data = bufferedReader.readLine()) != null){
                    categories.add(data);
                }
                documento.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return categories;
    }

    /**
     *
     * @return Devuelve un ArrayList con la data de las ciudades leidas desde un archivo de texto
     * */
    public ArrayList<String> LeerDataCitys(InputStream documento) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(documento));
        String data = null;
        ArrayList<String> citys = new ArrayList<>();
        try{
            while ((data  = bufferedReader.readLine())!=null){
                citys.add(data);
            }
            documento.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return citys;
    }
}
