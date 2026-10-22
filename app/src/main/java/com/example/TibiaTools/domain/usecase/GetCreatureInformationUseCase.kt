package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.APICriaturesInformation
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetCreatureInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(race: String): APICriaturesInformation = repository.getCreatureInformation(race)
}
