package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.CharacterInformation
import com.example.TibiaTools.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelPlayers(private val getCharacterUseCase: GetCharacterUseCase): ViewModel() {
    private val _name = MutableStateFlow<String?>(null)
    private val _player = MutableStateFlow<UiState>(UiState.Idle)
    val player: StateFlow<UiState> = _player

    fun searchPlayer(name: String) {
        _player.value = UiState.Loading
        viewModelScope.launch {
            getCharacterUseCase(name = name)
                .onSuccess {
                    _player.value = UiState.Success(it)
                }.onFailure {
                    _player.value = UiState.Error
                }
        }
    }


    sealed interface UiState {
        data object Idle: UiState
        data object Loading : UiState
        data class Success(val player: CharacterInformation) : UiState
        data object Error : UiState
    }
}