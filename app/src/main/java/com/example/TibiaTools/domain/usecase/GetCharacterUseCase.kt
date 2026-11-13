package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.CharacterInformation
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetCharacterUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(name: String): CharacterInformation {
        return repository.getCharacter(name)
    }
}
