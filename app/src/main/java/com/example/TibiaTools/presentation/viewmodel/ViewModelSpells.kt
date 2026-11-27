package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.SpellsData
import com.example.TibiaTools.domain.usecase.GetSpellsUseCase
import com.example.TibiaTools.presentation.routes.FilterOptionSpells
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewModelSpells(
    private val getSpellsUseCase: GetSpellsUseCase
) : ViewModel() {
    private val _spells = MutableStateFlow<UIState>(UIState.Idle)

    private val typeSpell = MutableStateFlow("")

    val spells = combine(
        typeSpell,
        _spells
    ) { type, state ->
        when (state) {
            is UIState.Success -> {
                val spellsData = state.data
                if (spellsData != null) {
                    val filteredList = when (type) {
                        FilterOptionSpells.ATTACK.type -> spellsData.spellsList.filter { it.isGroupAttack }
                        FilterOptionSpells.HEALING.type -> spellsData.spellsList.filter { it.isGroupHealing }
                        FilterOptionSpells.HEALING.type-> spellsData.spellsList.filter { it.isGroupSupport }
                        FilterOptionSpells.INSTANT.type -> spellsData.spellsList.filter { it.isTypeInstant }
                        FilterOptionSpells.RUNE.type -> spellsData.spellsList.filter { it.isTypeRune }
                        else -> spellsData.spellsList
                    }
                    UIState.Success(
                        data = spellsData.copy(spellsList = filteredList)
                    )
                } else {
                    state
                }
            }
            else -> state
        }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UIState.Idle
    )

    init {
        loadSpells()
    }

    fun setFilterType(type: String) {
        typeSpell.value = type
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
