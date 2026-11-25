package com.example.TibiaTools.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.SpellsData
import com.example.TibiaTools.domain.usecase.GetSpellsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelSpells(
    private val getSpellsUseCase: GetSpellsUseCase
) : ViewModel() {
    private val _spells = MutableStateFlow<UIState>(UIState.Idle)
    val spells = _spells.asStateFlow()

    init {
        loadSpells()
    }

    fun loadSpells() {
        _spells.value = UIState.Loader
        viewModelScope.launch {
            runCatching { getSpellsUseCase() }
                .onSuccess { _spells.value = UIState.Success(it.spells) }
                .onFailure {
                    _spells.value = UIState.Error(e = it)
                }
        }
    }

    sealed interface UIState {
        object Idle: UIState
        object Loader: UIState
        data class Success(val data: SpellsData?): UIState
        data class Error(val e: Throwable): UIState
    }
}
