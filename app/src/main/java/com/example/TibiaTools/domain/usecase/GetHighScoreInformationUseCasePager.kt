package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.repository.TibiaRepository

class GetHighScoreInformationUseCasePager(private val repository: TibiaRepository) {
    operator fun invoke(world: String, category: String, vocation: String, paging: Int) =
        repository.getHighScoreInformationPaging(world, category, vocation, paging)
}