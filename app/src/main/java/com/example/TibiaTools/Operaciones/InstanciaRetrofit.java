package com.example.TibiaTools.Operaciones;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InstanciaRetrofit {
    public Retrofit retrofit = null;
    public Retrofit getRetrofit(String url){
       if(retrofit==null){
           retrofit = new Retrofit.Builder().
                   baseUrl(url)
                   //para traerme data de la api en texto plano
                   .addConverterFactory(ScalarsConverterFactory.create())
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit;
    }
}
