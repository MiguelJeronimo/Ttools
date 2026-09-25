package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiNewsTicker
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetNewsTickersUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): ApiNewsTicker = repository.getNewsTickers()
}
