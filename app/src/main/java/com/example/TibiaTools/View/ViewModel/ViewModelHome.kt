package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.BoostableBossesData
import com.example.TibiaTools.domain.model.CreaturesData
import com.example.TibiaTools.domain.model.NewsItem
import com.example.TibiaTools.domain.model.NewsResponse
import com.example.TibiaTools.domain.model.NewsTickerResponse
import com.example.TibiaTools.domain.model.WorldsData
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

    private val _worlds = MutableLiveData<WorldsData?>()
    val worlds: LiveData<WorldsData?> get() = _worlds
    fun worlds(): LiveData<WorldsData?> = _worlds

    private val _rashidLocation = MutableLiveData<String?>()
    val rashidLocation: LiveData<String?> get() = _rashidLocation

    private val _playersOnline = MutableLiveData<WorldsData?>()
    val playersOnline: LiveData<WorldsData?> get() = _playersOnline
    fun playersOnline(): LiveData<WorldsData?> = _playersOnline

    private val _creatureBoss = MutableLiveData<CreaturesData?>()
    val creatureBoss: LiveData<CreaturesData?> get() = _creatureBoss
    fun creatureBoss(): LiveData<CreaturesData?> = _creatureBoss

    private val _bostedBoss = MutableLiveData<BoostableBossesData?>()
    val bostedBoss: LiveData<BoostableBossesData?> get() = _bostedBoss
    fun bostedBoss(): LiveData<BoostableBossesData?> = _bostedBoss

    private val _news = MutableLiveData<NewsResponse?>()
    val news: LiveData<NewsResponse?> get() = _news
    fun news(): LiveData<NewsResponse?> = _news

    private val _newTicker = MutableLiveData<NewsTickerResponse?>()
    val newTicker: LiveData<NewsTickerResponse?> get() = _newTicker
    fun newTicker(): LiveData<NewsTickerResponse?> = _newTicker

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
                .onSuccess { _bostedBoss.value = it.boostableBosses }
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
