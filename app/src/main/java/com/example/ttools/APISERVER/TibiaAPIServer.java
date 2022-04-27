package com.example.ttools.APISERVER;

import com.example.ttools.Operaciones.APIServicesTibia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TibiaAPIServer {
    @GET("{name}")
    Call<APIServicesTibia> getPersonajes(@Path("name") String name);

}
