package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.repository.TibiaRepository

class GetRashidLocationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): String = repository.getRashidLocation()
}
