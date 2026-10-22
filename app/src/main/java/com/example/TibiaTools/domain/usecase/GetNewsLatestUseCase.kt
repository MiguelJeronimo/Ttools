package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiNews
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetNewsLatestUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): ApiNews {
        return repository.getNewsLatest()
    }
}
