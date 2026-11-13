package com.example.TibiaTools.domain.repository

import com.example.TibiaTools.domain.model.*

interface TibiaRepository {
    suspend fun getCharacter(name: String): CharacterInformation
    suspend fun getWorlds(): WorldsResponse
    suspend fun getCreatures(): CreaturesResponse
    suspend fun getBoostableBosses(): BoostableBossesResponse
    suspend fun getCreatureInformation(race: String): CreatureInformationResponse
    suspend fun getGuildsInformation(world: String): GuildsResponse
    suspend fun getGuildsInformationName(name: String): GuildNameResponse
    suspend fun getSpells(): SpellsResponse
    suspend fun getSpellInformation(spell: String): SpellInformationResponse
    suspend fun getRashidLocation(): String
    suspend fun getNewsLatest(): NewsResponse
    suspend fun getNewsTickers(): NewsTickerResponse
    suspend fun getHighScoreInformation(world: String, category: String, vocation: String): HighScoresResponse
    suspend fun getHousesInformation(world: String, town: String): HousesResponse
    suspend fun getHouseInformation(world: String, houseId: String): HouseInformationResponse
}
