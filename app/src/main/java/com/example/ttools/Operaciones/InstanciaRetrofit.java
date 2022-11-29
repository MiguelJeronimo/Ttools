package com.example.ttools.Operaciones;

import retrofit2.Retrofit;

public class InstanciaRetrofit {
    private Retrofit.Builder retrofit;
    public InstanciaRetrofit(){
        retrofit = new Retrofit.Builder();
    }


    public Retrofit.Builder getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit.Builder retrofit) {
        this.retrofit = retrofit;
    }

}
