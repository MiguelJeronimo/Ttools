package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.SpellDetail
import com.example.TibiaTools.domain.usecase.GetSpellInformationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelSpell(
    private val getSpellInformationUseCase: GetSpellInformationUseCase
) : ViewModel() {
    private val _spell = MutableStateFlow<UIState>(UIState.Idle)
    val spell = _spell.asStateFlow()

    fun setSpell(id: String) {
        _spell.value = UIState.Loader
        viewModelScope.launch {
            runCatching { getSpellInformationUseCase(id) }
                .onSuccess { _spell.value = UIState.Success(data = it.spell) }
                .onFailure { _spell.value = UIState.Error(e = it) }
        }
    }


    sealed interface UIState {
        object Idle: UIState
        object Loader: UIState
        data class Success(val data: SpellDetail?): UIState
        data class Error(val e: Throwable): UIState
    }
}
