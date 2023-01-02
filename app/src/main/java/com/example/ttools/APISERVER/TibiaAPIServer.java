package com.example.ttools.APISERVER;

import com.example.ttools.APISERVER.models.APICriatures;
import com.example.ttools.APISERVER.models.APICriaturesInformation;
import com.example.ttools.APISERVER.models.ApiHighScores;
import com.example.ttools.APISERVER.models.ApiHouses;
import com.example.ttools.APISERVER.models.ApiHousesInformation;
import com.example.ttools.APISERVER.models.ApiNews;
import com.example.ttools.APISERVER.models.ApiNewsTicker;
import com.example.ttools.APISERVER.models.ApiSpells;
import com.example.ttools.APISERVER.models.ApiSpellsInformation;
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

    @GET("boostablebosses")
    Call<APICriatures> getBooted();

    @GET("{race}")
    Call<APICriaturesInformation> getCriatureInformation(@Path("race") String race);

    @GET("{world}")
    Call<ApiGuilds> getGuildsInformation(@Path("world") String world);

    @GET("{name}")
    Call<ApiGuildsName> getGuildsInformationName(@Path("name") String name);

    @GET("spells")
    Call<ApiSpells> getSpells();

    @GET("{spell}")
    Call<ApiSpellsInformation> getSpellInformation(@Path("spell") String spell);

    @GET("rashid")
    Call<String> getRashidLocalitation();

    @GET("news/latest")
    Call<ApiNews> getNewsLatest();

    @GET("news/newsticker")
    Call<ApiNewsTicker> getNewsTickers();

    @GET("highscores/{world}/{category}/{vocation}")
    Call<ApiHighScores> getHighScoreInformation(@Path("world") String world, @Path("category") String category, @Path("vocation") String vocation);

    @GET("houses/{world}/{town}")
    Call<ApiHouses> getHousesInformation(@Path("world") String world, @Path("town") String town);
    @GET("house/{world}/{house_id}")
    Call<ApiHousesInformation> getHouseInformation(@Path("world") String world, @Path("id") String id);
}
