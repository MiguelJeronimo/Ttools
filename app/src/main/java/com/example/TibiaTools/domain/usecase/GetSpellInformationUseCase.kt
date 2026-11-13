package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.SpellInformationResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetSpellInformationUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(spell: String): SpellInformationResponse = repository.getSpellInformation(spell)
}
