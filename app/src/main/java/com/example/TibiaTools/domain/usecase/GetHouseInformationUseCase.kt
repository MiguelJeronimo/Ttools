package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiHousesInformation
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetHouseInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String, houseId: String): ApiHousesInformation =
        repository.getHouseInformation(world, houseId)
}
