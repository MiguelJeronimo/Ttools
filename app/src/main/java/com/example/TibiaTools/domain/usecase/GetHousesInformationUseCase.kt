package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.HousesResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetHousesInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String, town: String): HousesResponse =
        repository.getHousesInformation(world, town)
}
