package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.APIBoostableBosses
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetBoostableBossesUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): APIBoostableBosses = repository.getBoostableBosses()
}
