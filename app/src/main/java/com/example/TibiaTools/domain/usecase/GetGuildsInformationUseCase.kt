package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiGuilds
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetGuildsInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String): ApiGuilds = repository.getGuildsInformation(world)
}
