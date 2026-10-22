package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.Criatures
import com.example.TibiaTools.domain.usecase.GetCreaturesUseCase
import kotlinx.coroutines.launch

class ViewModelCreatures(
    private val getCreaturesUseCase: GetCreaturesUseCase
) : ViewModel() {
    private val _creatures = MutableLiveData<Criatures?>()
    fun creature(): LiveData<Criatures?> = _creatures

    init {
        loadCreatures()
    }

    fun loadCreatures() {
        viewModelScope.launch {
            runCatching { getCreaturesUseCase() }
                .onSuccess { _creatures.value = it.creatures }
                .onFailure { _creatures.value = null }
        }
    }
}
