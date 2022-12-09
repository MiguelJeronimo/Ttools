package com.example.ttools.Operaciones;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanciaRetrofit {
    private static Retrofit retrofit = null;
    public static Retrofit InstanciaRetrofit(String url){
       if(retrofit==null){
           retrofit = new Retrofit.Builder().
                   baseUrl(url)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit;
    }
}
