package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.NewsTickerResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetNewsTickersUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): NewsTickerResponse = repository.getNewsTickers()
}
