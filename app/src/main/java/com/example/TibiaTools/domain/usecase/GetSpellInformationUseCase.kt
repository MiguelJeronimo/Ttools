package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiSpellsInformation
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetSpellInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(spell: String): ApiSpellsInformation = repository.getSpellInformation(spell)
}
