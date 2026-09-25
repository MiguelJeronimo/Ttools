package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.data.model.ApiSpells
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetSpellsUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(): ApiSpells = repository.getSpells()
}
