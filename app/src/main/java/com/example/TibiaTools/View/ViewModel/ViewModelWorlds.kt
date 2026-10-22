package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.Worlds
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
import kotlinx.coroutines.launch

class ViewModelWorlds(
    private val getWorldsUseCase: GetWorldsUseCase
) : ViewModel() {
    private val _worlds = MutableLiveData<Worlds?>()
    fun worlds(): LiveData<Worlds?> = _worlds

    init {
        setWorlds()
    }

    fun setWorlds() {
        viewModelScope.launch {
            runCatching { getWorldsUseCase() }
                .onSuccess { _worlds.value = it.worlds }
                .onFailure { _worlds.value = null }
        }
    }
}
