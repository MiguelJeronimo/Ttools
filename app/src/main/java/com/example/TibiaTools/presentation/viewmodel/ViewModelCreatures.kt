package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.CreaturesData
import com.example.TibiaTools.domain.usecase.GetCreaturesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewModelCreatures(
    private val getCreaturesUseCase: GetCreaturesUseCase
) : ViewModel() {

    private val _nameCreature = MutableStateFlow("")
    private val _creatures = MutableStateFlow<UIState>(UIState.Idle)

    val creature = combine(
        _nameCreature,
        _creatures
    ) { name, state ->
        if (name.isBlank()) {
            state
        } else {
            when (state) {
                is UIState.Success -> {
                    val originalData = state.data
                    if (originalData != null) {
                        val filteredList = originalData.creatureList.filter { creatureItem ->
                            creatureItem.name.contains(name, ignoreCase = true)
                        }
                        UIState.Success(originalData.copy(creatureList = filteredList))
                    } else {
                        state
                    }
                }
                else -> state
            }
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UIState.Idle
    )

    init {
        loadCreatures()
    }

    fun finCreature(name: String) {
        _nameCreature.value = name
    }

    fun loadCreatures() {
        _creatures.value = UIState.Loader
        viewModelScope.launch {
            runCatching { getCreaturesUseCase() }
                .onSuccess { _creatures.value = UIState.Success(it.creatures) }
                .onFailure { _creatures.value = UIState.Error(e = it) }
        }
    }


    sealed interface UIState {
        object Idle: UIState
        object Loader: UIState
        data class Success(val data: CreaturesData?): UIState
        data class Error(val e: Throwable): UIState
    }
}
