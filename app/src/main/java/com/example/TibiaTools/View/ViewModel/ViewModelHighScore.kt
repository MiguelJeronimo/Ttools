package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.HighScore
import com.example.TibiaTools.domain.usecase.GetHighScoreInformationUseCase
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
import kotlinx.coroutines.launch

class ViewModelHighScore(
    private val getHighScoreInformationUseCase: GetHighScoreInformationUseCase,
    private val getWorldsUseCase: GetWorldsUseCase
) : ViewModel() {
    private val _worlds = MutableLiveData<ArrayList<String>?>()
    fun Worlds(): LiveData<ArrayList<String>?> = _worlds

    private val _highScoreList = MutableLiveData<HighScore?>()
    fun highScoreList(): LiveData<HighScore?> = _highScoreList

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

    fun setHighScores(world: String, category: String, vocation: String) {
        viewModelScope.launch {
            runCatching { getHighScoreInformationUseCase(world, category, vocation) }
                .onSuccess { _highScoreList.value = it.highscores }
                .onFailure { _highScoreList.value = null }
        }
    }
}
