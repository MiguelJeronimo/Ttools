package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.SpellsResponse
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetSpellsUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): SpellsResponse = repository.getSpells()
}
