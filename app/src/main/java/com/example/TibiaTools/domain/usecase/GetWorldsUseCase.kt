package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.WorldsResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetWorldsUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): WorldsResponse = repository.getWorlds()
}
