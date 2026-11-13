package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.BoostableBossesResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetBoostableBossesUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): BoostableBossesResponse = repository.getBoostableBosses()
}
