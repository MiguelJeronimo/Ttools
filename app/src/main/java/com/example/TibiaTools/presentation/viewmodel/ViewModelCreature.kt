package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.CreatureDetail
import com.example.TibiaTools.domain.usecase.GetCreatureInformationUseCase
import kotlinx.coroutines.launch

class ViewModelCreature(
    private val getCreatureInformationUseCase: GetCreatureInformationUseCase
) : ViewModel() {
    val _creature = MutableLiveData<CreatureDetail?>()
    fun creature(): LiveData<CreatureDetail?> = _creature

    fun setCreature(creatureRace: String) {
        viewModelScope.launch {
            runCatching { getCreatureInformationUseCase(creatureRace) }
                .onSuccess { _creature.value = it.creature }
                .onFailure { _creature.value = null }
        }
    }


    sealed interface UIState {
        object Idle: UIState
        object Loader: UIState
        data class Success(val data: CreatureDetail): UIState
        data class Error(val e: Exception): UIState
    }
}
