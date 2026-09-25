package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.APISERVER.models.CharactersInformation.APIServicesTibia
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetCharacterUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(name: String): APIServicesTibia {
        return repository.getCharacter(name)
    }
}
