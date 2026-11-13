package com.example.TibiaTools.data.repository

import com.example.TibiaTools.data.mapper.toDomain
import com.example.TibiaTools.data.retrofit.TibiaAPIServer
import com.example.TibiaTools.domain.model.*
import com.example.TibiaTools.domain.repository.TibiaRepository

class TibiaRepositoryImpl(
    private val apiServer: TibiaAPIServer
) : TibiaRepository {
    override suspend fun getCharacter(name: String): CharacterInformation =
        apiServer.getPersonajes(name).toDomain()

    override suspend fun getWorlds(): WorldsResponse =
        apiServer.getWorlds().toDomain()

    override suspend fun getCreatures(): CreaturesResponse =
        apiServer.getCreature().toDomain()

    override suspend fun getBoostableBosses(): BoostableBossesResponse =
        apiServer.getBoostableBosses().toDomain()

    override suspend fun getCreatureInformation(race: String): CreatureInformationResponse =
        apiServer.getCriatureInformation(race).toDomain()

    override suspend fun getGuildsInformation(world: String): GuildsResponse =
        apiServer.getGuildsInformation(world).toDomain()

    override suspend fun getGuildsInformationName(name: String): GuildNameResponse =
        apiServer.getGuildsInformationName(name).toDomain()

    override suspend fun getSpells(): SpellsResponse =
        apiServer.getSpells().toDomain()

    override suspend fun getSpellInformation(spell: String): SpellInformationResponse =
        apiServer.getSpellInformation(spell).toDomain()

    override suspend fun getRashidLocation(): String =
        apiServer.getRashidLocalitation()

    override suspend fun getNewsLatest(): NewsResponse =
        apiServer.getNewsLatest().toDomain()

    override suspend fun getNewsTickers(): NewsTickerResponse =
        apiServer.getNewsTickers().toDomain()

    override suspend fun getHighScoreInformation(world: String, category: String, vocation: String): HighScoresResponse =
        apiServer.getHighScoreInformation(world, category, vocation).toDomain()

    override suspend fun getHousesInformation(world: String, town: String): HousesResponse =
        apiServer.getHousesInformation(world, town).toDomain()

    override suspend fun getHouseInformation(world: String, houseId: String): HouseInformationResponse =
        apiServer.getHouseInformation(world, houseId).toDomain()
}
