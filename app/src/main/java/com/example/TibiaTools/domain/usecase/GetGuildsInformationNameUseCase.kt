package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiGuildsName
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetGuildsInformationNameUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(name: String): ApiGuildsName = repository.getGuildsInformationName(name)
}
