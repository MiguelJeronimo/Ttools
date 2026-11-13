package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.HighScoresResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetHighScoreInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String, category: String, vocation: String): HighScoresResponse =
        repository.getHighScoreInformation(world, category, vocation)
}
