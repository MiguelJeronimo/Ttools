package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiHighScores
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetHighScoreInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String, category: String, vocation: String): ApiHighScores =
        repository.getHighScoreInformation(world, category, vocation)
}
