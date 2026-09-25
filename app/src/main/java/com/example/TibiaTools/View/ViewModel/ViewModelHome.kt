package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.ApiNews
import com.example.TibiaTools.data.model.ApiNewsTicker
import com.example.TibiaTools.data.model.BoostableBosses
import com.example.TibiaTools.data.model.Criatures
import com.example.TibiaTools.data.model.Worlds
import com.example.TibiaTools.domain.usecase.GetBoostableBossesUseCase
import com.example.TibiaTools.domain.usecase.GetCreaturesUseCase
import com.example.TibiaTools.domain.usecase.GetNewsLatestUseCase
import com.example.TibiaTools.domain.usecase.GetNewsTickersUseCase
import com.example.TibiaTools.domain.usecase.GetRashidLocationUseCase
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
import kotlinx.coroutines.launch

class ViewModelHome(
    private val getWorldsUseCase: GetWorldsUseCase,
    private val getRashidLocationUseCase: GetRashidLocationUseCase,
    private val getCreaturesUseCase: GetCreaturesUseCase,
    private val getBoostableBossesUseCase: GetBoostableBossesUseCase,
    private val getNewsLatestUseCase: GetNewsLatestUseCase,
    private val getNewsTickersUseCase: GetNewsTickersUseCase
) : ViewModel() {

    private val _worlds = MutableLiveData<Worlds?>()
    fun worlds(): LiveData<Worlds?> = _worlds

    private val _rashidLocation = MutableLiveData<String?>()
    val rashidLocation: LiveData<String?> get() = _rashidLocation

    private val _playersOnline = MutableLiveData<Worlds?>()
    val playersOnline: LiveData<Worlds?> = _playersOnline

    private val _creatureBoss = MutableLiveData<Criatures?>()
    val creatureBoss: LiveData<Criatures?> = _creatureBoss

    private val _bostedBoss = MutableLiveData<BoostableBosses?>()
    val boostedBoss: LiveData<BoostableBosses?> = _bostedBoss

    val _news = MutableLiveData<ApiNews?>()
    val news: LiveData<ApiNews?> = _news

    val _newTicker = MutableLiveData<ApiNewsTicker?>()
    val newTicker: LiveData<ApiNewsTicker?> = _newTicker

    fun setWorlds() {
        viewModelScope.launch {
            runCatching { getWorldsUseCase() }
                .onSuccess { _worlds.value = it.worlds }
                .onFailure { _worlds.value = null }
        }
    }

    fun setRashirLocation() {
        viewModelScope.launch {
            runCatching { getRashidLocationUseCase() }
                .onSuccess { _rashidLocation.value = it }
                .onFailure { _rashidLocation.value = null }
        }
    }

    fun setPlayersOnline() {
        viewModelScope.launch {
            runCatching { getWorldsUseCase() }
                .onSuccess { _playersOnline.value = it.worlds }
                .onFailure { _playersOnline.value = null }
        }
    }

    fun setCreatureBoss() {
        viewModelScope.launch {
            runCatching { getCreaturesUseCase() }
                .onSuccess { _creatureBoss.value = it.creatures }
                .onFailure { _creatureBoss.value = null }
        }
    }

    fun setBostedBoss() {
        viewModelScope.launch {
            runCatching { getBoostableBossesUseCase() }
                .onSuccess { _bostedBoss.value = it.boostable_bosses }
                .onFailure { _bostedBoss.value = null }
        }
    }

    fun setNews() {
        viewModelScope.launch {
            runCatching { getNewsLatestUseCase() }
                .onSuccess { _news.value = it }
                .onFailure { _news.value = null }
        }
    }

    fun setNewTicker() {
        viewModelScope.launch {
            runCatching { getNewsTickersUseCase() }
                .onSuccess { _newTicker.value = it }
                .onFailure { _newTicker.value = null }
        }
    }
}
