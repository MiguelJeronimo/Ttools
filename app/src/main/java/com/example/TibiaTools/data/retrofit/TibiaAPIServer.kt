package com.example.TibiaTools.data.retrofit

import com.example.TibiaTools.data.dto.*
import retrofit2.http.GET
import retrofit2.http.Path

interface TibiaAPIServer {
    @GET("/v4/character/{name}")
    suspend fun getPersonajes(@Path("name") name: String): CharacterInformationDto

    @GET("/v4/worlds")
    suspend fun getWorlds(): WorldsResponseDto

    @GET("/v4/creatures")
    suspend fun getCreature(): CreaturesResponseDto

    @GET("/v4/boostablebosses")
    suspend fun getBoostableBosses(): BoostableBossesResponseDto

    @GET("/v4/creature/{race}")
    suspend fun getCriatureInformation(@Path("race") race: String): CreatureInformationResponseDto

    @GET("/v4/guilds/{world}")
    suspend fun getGuildsInformation(@Path("world") world: String): GuildsResponseDto

    @GET("/v4/guild/{name}")
    suspend fun getGuildsInformationName(@Path("name") name: String): GuildNameResponseDto

    @GET("/v4/spells")
    suspend fun getSpells(): SpellsResponseDto

    @GET("/v4/spell/{spell}")
    suspend fun getSpellInformation(@Path("spell") spell: String): SpellInformationResponseDto

    @GET("https://api.tibialabs.com/v2/rashid")
    suspend fun getRashidLocalitation(): String

    @GET("/v4/news/latest")
    suspend fun getNewsLatest(): NewsResponseDto

    @GET("/v4/news/newsticker")
    suspend fun getNewsTickers(): NewsTickerResponseDto

    @GET("/v4/highscores/{world}/{category}/{vocation}")
    suspend fun getHighScoreInformation(
        @Path("world") world: String,
        @Path("category") category: String,
        @Path("vocation") vocation: String
    ): HighScoresResponseDto

    @GET("/v4/houses/{world}/{town}")
    suspend fun getHousesInformation(
        @Path("world") world: String,
        @Path("town") town: String
    ): HousesResponseDto

    @GET("/v4/house/{world}/{house_id}")
    suspend fun getHouseInformation(
        @Path("world") world: String,
        @Path("house_id") houseId: String
    ): HouseInformationResponseDto
}
