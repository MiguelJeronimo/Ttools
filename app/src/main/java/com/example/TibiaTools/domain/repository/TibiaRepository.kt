package com.example.TibiaTools.domain.repository

import com.example.TibiaTools.data.model.*

interface TibiaRepository {
    suspend fun getCharacter(name: String): APIServicesTibia
    suspend fun getWorlds(): DataWords
    suspend fun getCreatures(): APICriatures
    suspend fun getBoostableBosses(): APIBoostableBosses
    suspend fun getCreatureInformation(race: String): APICriaturesInformation
    suspend fun getGuildsInformation(world: String): ApiGuilds
    suspend fun getGuildsInformationName(name: String): ApiGuildsName
    suspend fun getSpells(): ApiSpells
    suspend fun getSpellInformation(spell: String): ApiSpellsInformation
    suspend fun getRashidLocation(): String
    suspend fun getNewsLatest(): ApiNews
    suspend fun getNewsTickers(): ApiNewsTicker
    suspend fun getHighScoreInformation(world: String, category: String, vocation: String): ApiHighScores
    suspend fun getHousesInformation(world: String, town: String): ApiHouses
    suspend fun getHouseInformation(world: String, houseId: String): ApiHousesInformation
}
