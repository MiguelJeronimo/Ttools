package com.example.TibiaTools.domain.usecase

import com.example.TibiaTools.domain.model.CharacterInformation
import com.example.TibiaTools.domain.repository.TibiaRepository

class GetCharacterUseCase(private val repository: TibiaRepository) {
    suspend operator fun invoke(name: String): Result<CharacterInformation> {
        return try {
             Result.success(repository.getCharacter(name))
        } catch (e: Exception){
            Result.failure(exception = e)
        }
    }
}
