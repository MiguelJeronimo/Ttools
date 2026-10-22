package com.example.TibiaTools.data.repository

import com.example.TibiaTools.data.model.*
import com.example.TibiaTools.data.retrofit.TibiaAPIServer
import com.example.TibiaTools.domain.repository.TibiaRepository

class TibiaRepositoryImpl(
    private val apiServer: TibiaAPIServer
) : TibiaRepository {
    override suspend fun getCharacter(name: String): APIServicesTibia = apiServer.getPersonajes(name)
    override suspend fun getWorlds(): DataWords = apiServer.getWorlds()
    override suspend fun getCreatures(): APICriatures = apiServer.getCreature()
    override suspend fun getBoostableBosses(): APIBoostableBosses = apiServer.getBoostableBosses()
    override suspend fun getCreatureInformation(race: String): APICriaturesInformation = apiServer.getCriatureInformation(race)
    override suspend fun getGuildsInformation(world: String): ApiGuilds = apiServer.getGuildsInformation(world)
    override suspend fun getGuildsInformationName(name: String): ApiGuildsName = apiServer.getGuildsInformationName(name)
    override suspend fun getSpells(): ApiSpells = apiServer.getSpells()
    override suspend fun getSpellInformation(spell: String): ApiSpellsInformation = apiServer.getSpellInformation(spell)
    override suspend fun getRashidLocation(): String = apiServer.getRashidLocalitation()
    override suspend fun getNewsLatest(): ApiNews = apiServer.getNewsLatest()
    override suspend fun getNewsTickers(): ApiNewsTicker = apiServer.getNewsTickers()
    override suspend fun getHighScoreInformation(world: String, category: String, vocation: String): ApiHighScores =
        apiServer.getHighScoreInformation(world, category, vocation)
    override suspend fun getHousesInformation(world: String, town: String): ApiHouses =
        apiServer.getHousesInformation(world, town)
    override suspend fun getHouseInformation(world: String, houseId: String): ApiHousesInformation =
        apiServer.getHouseInformation(world, houseId)
}
