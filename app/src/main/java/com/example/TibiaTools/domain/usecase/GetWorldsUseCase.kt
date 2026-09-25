package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.DataWords
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetWorldsUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): DataWords {
        return repository.getWorlds()
    }
}
