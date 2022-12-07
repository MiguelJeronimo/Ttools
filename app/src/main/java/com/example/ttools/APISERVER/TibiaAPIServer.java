package com.example.ttools.APISERVER;

import com.example.ttools.APISERVER.models.APICriatures;
import com.example.ttools.APISERVER.models.APICriaturesInformation;
import com.example.ttools.APISERVER.models.ApiSpells;
import com.example.ttools.APISERVER.models.CharactersInformation.APIServicesTibia;
import com.example.ttools.APISERVER.models.GuildInformation.ApiGuilds;
import com.example.ttools.APISERVER.models.GuildInformation.ApiGuildsName;
import com.example.ttools.APISERVER.models.Worlds.DataWords;

import retrofit2.Call;
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

    @GET("{world}")
    Call<ApiGuilds> getGuildsInformation(@Path("world") String world);

    @GET("{name}")
    Call<ApiGuildsName> getGuildsInformationName(@Path("name") String name);

    @GET("spells")
    Call<ApiSpells> getSpells();

}
