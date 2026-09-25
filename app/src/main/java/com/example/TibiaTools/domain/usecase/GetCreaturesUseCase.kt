package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.APICriatures
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetCreaturesUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): APICriatures {
        return repository.getCreatures()
    }
}
