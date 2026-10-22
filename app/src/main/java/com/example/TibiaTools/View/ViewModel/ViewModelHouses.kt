package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.Houses
import com.example.TibiaTools.domain.usecase.GetHousesInformationUseCase
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
import kotlinx.coroutines.launch

class ViewModelHouses(
    private val getHousesInformationUseCase: GetHousesInformationUseCase,
    private val getWorldsUseCase: GetWorldsUseCase
) : ViewModel() {
    private val _houses = MutableLiveData<Houses?>()
    fun houses(): LiveData<Houses?> = _houses

    private val _worlds = MutableLiveData<ArrayList<String>?>()
    fun Worlds(): LiveData<ArrayList<String>?> = _worlds

    init {
        loadWorlds()
    }

    private fun loadWorlds() {
        viewModelScope.launch {
            runCatching { getWorldsUseCase() }
                .onSuccess { dataWords ->
                    val arrayWorlds = arrayListOf("Seleccione")
                    dataWords.worlds?.regular_worlds?.forEach { world ->
                        world.name?.let { arrayWorlds.add(it) }
                    }
                    _worlds.value = arrayWorlds
                }
                .onFailure { _worlds.value = null }
        }
    }

    fun setHouses(world: String, town: String) {
        viewModelScope.launch {
            runCatching { getHousesInformationUseCase(world, town) }
                .onSuccess { _houses.value = it.houses }
                .onFailure { _houses.value = null }
        }
    }
}
