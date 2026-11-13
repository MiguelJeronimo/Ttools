package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.HouseInformationResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetHouseInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(world: String, houseId: String): HouseInformationResponse =
        repository.getHouseInformation(world, houseId)
}
