package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.HouseDetail
import com.example.TibiaTools.domain.usecase.GetHouseInformationUseCase
import kotlinx.coroutines.launch

class ViewModelHouseInformation(
    private val getHouseInformationUseCase: GetHouseInformationUseCase
) : ViewModel() {
    private val _house = MutableLiveData<HouseDetail?>()
    fun getHouse(): LiveData<HouseDetail?> = _house

    fun setHouse(world: String, idHouse: String) {
        viewModelScope.launch {
            runCatching { getHouseInformationUseCase(world, idHouse) }
                .onSuccess { _house.value = it.house }
                .onFailure { _house.value = null }
        }
    }
}
