package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.NewsResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetNewsLatestUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): NewsResponse = repository.getNewsLatest()
}
