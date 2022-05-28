package com.example.ttools.APISERVER;

import com.example.ttools.APISERVER.models.APICriatures;
import com.example.ttools.APISERVER.models.APICriaturesInformation;
import com.example.ttools.Operaciones.APIServicesTibia;
import com.example.ttools.Operaciones.information.DataWords;
import com.example.ttools.Operaciones.information.Worlds;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TibiaAPIServer {
    @GET("{name}")
    Call<APIServicesTibia> getPersonajes(@Path("name") String name);

    @GET ("worlds")
    Call<DataWords> getWorlds();

    @GET ("creatures")
    Call<APICriatures> getCreatures();

    @GET("{race}")
    Call<APICriaturesInformation> getCriatureInformation(@Path("race") String race);
}
