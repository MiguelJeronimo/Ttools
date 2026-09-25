package com.example.TibiaTools.data.retrofit

import com.example.TibiaTools.APISERVER.models.APIBoostableBosses
import com.example.TibiaTools.APISERVER.models.APICriatures
import com.example.TibiaTools.APISERVER.models.APICriaturesInformation
import com.example.TibiaTools.APISERVER.models.ApiHighScores
import com.example.TibiaTools.APISERVER.models.ApiHouses
import com.example.TibiaTools.APISERVER.models.ApiHousesInformation
import com.example.TibiaTools.APISERVER.models.ApiNews
import com.example.TibiaTools.APISERVER.models.ApiNewsTicker
import com.example.TibiaTools.APISERVER.models.ApiSpells
import com.example.TibiaTools.APISERVER.models.ApiSpellsInformation
import com.example.TibiaTools.APISERVER.models.CharactersInformation.APIServicesTibia
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuilds
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuildsName
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface TibiaAPIServer {
    @GET("/v4/character/{name}")
    suspend fun getPersonajes(@Path("name") name: String): APIServicesTibia

    @GET("/v4/worlds")
    suspend fun getWorlds(): DataWords

    @GET("/v4/creatures")
    suspend fun getCreature(): APICriatures

    @GET("/v4/boostablebosses")
    suspend fun getBoostableBosses(): APIBoostableBosses

    @GET("/v4/creature/{race}")
    suspend fun getCriatureInformation(@Path("race") race: String): APICriaturesInformation

    @GET("/v4/guilds/{world}")
    suspend fun getGuildsInformation(@Path("world") world: String): ApiGuilds

    @GET("/v4/guild/{name}")
    suspend fun getGuildsInformationName(@Path("name") name: String): ApiGuildsName

    @GET("/v4/spells")
    suspend fun getSpells(): ApiSpells

    @GET("/v4/spell/{spell}")
    suspend fun getSpellInformation(@Path("spell") spell: String): ApiSpellsInformation

    @GET("https://api.tibialabs.com/v2/rashid")
    suspend fun getRashidLocalitation(): String

    @GET("/v4/news/latest")
    suspend fun getNewsLatest(): ApiNews

    @GET("/v4/news/newsticker")
    suspend fun getNewsTickers(): ApiNewsTicker

    @GET("/v4/highscores/{world}/{category}/{vocation}")
    suspend fun getHighScoreInformation(
        @Path("world") world: String,
        @Path("category") category: String,
        @Path("vocation") vocation: String
    ): ApiHighScores

    @GET("/v4/houses/{world}/{town}")
    suspend fun getHousesInformation(
        @Path("world") world: String,
        @Path("town") town: String
    ): ApiHouses

    @GET("/v4/house/{world}/{house_id}")
    suspend fun getHouseInformation(
        @Path("world") world: String,
        @Path("house_id") houseId: String
    ): ApiHousesInformation
}
