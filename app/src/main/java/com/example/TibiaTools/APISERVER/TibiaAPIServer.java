package com.example.TibiaTools.APISERVER;

import com.example.TibiaTools.APISERVER.models.APIBoostableBosses;
import com.example.TibiaTools.APISERVER.models.APICriatures;
import com.example.TibiaTools.APISERVER.models.APICriaturesInformation;
import com.example.TibiaTools.APISERVER.models.ApiHighScores;
import com.example.TibiaTools.APISERVER.models.ApiHouses;
import com.example.TibiaTools.APISERVER.models.ApiHousesInformation;
import com.example.TibiaTools.APISERVER.models.ApiNews;
import com.example.TibiaTools.APISERVER.models.ApiNewsTicker;
import com.example.TibiaTools.APISERVER.models.ApiSpells;
import com.example.TibiaTools.APISERVER.models.ApiSpellsInformation;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.APIServicesTibia;
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuilds;
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuildsName;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TibiaAPIServer {
    @GET("/v4/character/{name}")
    Call<APIServicesTibia> getPersonajes(@Path("name") String name);

    @GET ("/v4/worlds")
    Call<DataWords> getWorlds();

    @GET ("/v4/creatures")
    Call<APICriatures> getCreature();

    @GET("/v4/boostablebosses")
    Call<APIBoostableBosses> getBoostableBosses();

    @GET("{race}")
    Call<APICriaturesInformation> getCriatureInformation(@Path("race") String race);

    @GET("/v4/guilds/{world}")
    Call<ApiGuilds> getGuildsInformation(@Path("world") String world);

    @GET("/v4/guild/{name}")
    Call<ApiGuildsName> getGuildsInformationName(@Path("name") String name);

    @GET("spells")
    Call<ApiSpells> getSpells();

    @GET("{spell}")
    Call<ApiSpellsInformation> getSpellInformation(@Path("spell") String spell);

    @GET("https://api.tibialabs.com/v2/rashid")
    Call<String> getRashidLocalitation();

    @GET("/v4/news/latest")
    Call<ApiNews> getNewsLatest();

    @GET("/v4/news/newsticker")
    Call<ApiNewsTicker> getNewsTickers();

    @GET("/v4/highscores/{world}/{category}/{vocation}")
    Call<ApiHighScores> getHighScoreInformation(@Path("world") String world, @Path("category") String category, @Path("vocation") String vocation);

    @GET("houses/{world}/{town}")
    Call<ApiHouses> getHousesInformation(@Path("world") String world, @Path("town") String town);
    @GET("house/{world}/{house_id}")
    Call<ApiHousesInformation> getHouseInformation(@Path("world") String world, @Path("house_id") String house_id);
}
