package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.CreatureInformationResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetCreatureInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(race: String): CreatureInformationResponse = repository.getCreatureInformation(race)
}
