package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.usecase.GetHighScoreInformationUseCase
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelHighScores(
    private val getHighScoreInformationUseCase: GetHighScoreInformationUseCase,
    private val getWorldsUseCase: GetWorldsUseCase
): ViewModel() {

    private val _worlds = MutableStateFlow<List<String>?>(null)
    val worlds = _worlds.asStateFlow()

    private fun loadWorlds() {
        viewModelScope.launch {
            runCatching { getWorldsUseCase() }
                .onSuccess { dataWords ->
                    val arrayWorlds = arrayListOf("Seleccione")
                    dataWords.worlds?.regularWorlds?.forEach { world ->
                        world.name.let { arrayWorlds.add(it) }
                    }
                    _worlds.value = arrayWorlds
                }
                .onFailure { _worlds.value = null }
        }
    }

//    fun setHighScores(world: String, category: String, vocation: String) {
//        viewModelScope.launch {
//            runCatching { getHighScoreInformationUseCase(world, category, vocation) }
//                .onSuccess { _highScoreList.value = it.highscores }
//                .onFailure { _highScoreList.value = null }
//        }
//    }

}