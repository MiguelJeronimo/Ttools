package com.example.TibiaTools.domain.repository

import androidx.paging.PagingData
import com.example.TibiaTools.domain.model.*
import kotlinx.coroutines.flow.Flow

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
    fun getHighScoreInformationPaging(world: String, category: String,vocation: String, paging: Int = 1): Flow<PagingData<HighScoreItem>>
    suspend fun getHousesInformation(world: String, town: String): HousesResponse
    suspend fun getHouseInformation(world: String, houseId: String): HouseInformationResponse
}
