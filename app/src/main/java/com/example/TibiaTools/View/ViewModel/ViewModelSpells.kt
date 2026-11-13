package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.SpellsData
import com.example.TibiaTools.domain.usecase.GetSpellsUseCase
import kotlinx.coroutines.launch

class ViewModelSpells(
    private val getSpellsUseCase: GetSpellsUseCase
) : ViewModel() {
    private val _spells = MutableLiveData<SpellsData?>()
    fun spells(): LiveData<SpellsData?> = _spells

    init {
        loadSpells()
    }

    fun loadSpells() {
        viewModelScope.launch {
            runCatching { getSpellsUseCase() }
                .onSuccess { _spells.value = it.spells }
                .onFailure { _spells.value = null }
        }
    }
}
