package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.GuildsResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetGuildsInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String): GuildsResponse = repository.getGuildsInformation(world)
}
