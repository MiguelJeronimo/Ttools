package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ViewModelPlayers(private val players: GetCharacterUseCase): ViewModel() {
    private val _name = MutableStateFlow<String?>(null)
    val player = flow {
        emit(players.invoke(_name.value ?: ""))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = null
    )

    fun searchPlayer(name: String) {
        _name.value = name
    }


//    sealed interface UiState {
//        data object Loading : UiState
//        data class Success(val player: Player) : UiState
//        data object Error : UiState
//    }

}