package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.House
import com.example.TibiaTools.domain.usecase.GetHouseInformationUseCase
import kotlinx.coroutines.launch

class ViewModelHouseInformation(
    private val getHouseInformationUseCase: GetHouseInformationUseCase
) : ViewModel() {
    private val _house = MutableLiveData<House?>()
    fun getHouse(): LiveData<House?> = _house

    fun setHouse(world: String, idHouse: String) {
        viewModelScope.launch {
            runCatching { getHouseInformationUseCase(world, idHouse) }
                .onSuccess { _house.value = it.house }
                .onFailure { _house.value = null }
        }
    }
}
