package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiHouses
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetHousesInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String, town: String): ApiHouses =
        repository.getHousesInformation(world, town)
}
