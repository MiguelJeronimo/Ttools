package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.GuildNameResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetGuildsInformationNameUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(name: String): GuildNameResponse = repository.getGuildsInformationName(name)
}
