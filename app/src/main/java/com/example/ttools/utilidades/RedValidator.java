package com.example.ttools.utilidades;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class RedValidator {
    /**
     * @param context
     * @return Retorna verdadero si hay conexion y falso si no hay internet
     * */
    public static boolean ValidarInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
