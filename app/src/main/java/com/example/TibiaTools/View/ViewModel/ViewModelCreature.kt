package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.Creature
import com.example.TibiaTools.domain.usecase.GetCreatureInformationUseCase
import kotlinx.coroutines.launch

class ViewModelCreature(
    private val getCreatureInformationUseCase: GetCreatureInformationUseCase
) : ViewModel() {
    val _creature = MutableLiveData<Creature?>()
    fun creature(): LiveData<Creature?> = _creature

    fun setCreature(creatureRace: String) {
        viewModelScope.launch {
            runCatching { getCreatureInformationUseCase(creatureRace) }
                .onSuccess { _creature.value = it.creature }
                .onFailure { _creature.value = null }
        }
    }
}
