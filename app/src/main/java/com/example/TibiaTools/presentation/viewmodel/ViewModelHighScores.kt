package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.domain.model.WorldsResponse
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

    private val _worlds = MutableStateFlow<UIStateWords>(UIStateWords.Idle)
    val worlds = _worlds.asStateFlow()

    init {
        loadWorlds()
    }


    private fun loadWorlds() {
        viewModelScope.launch {
            runCatching { getWorldsUseCase() }
                .onSuccess { dataWords ->
                    val arrayWorlds = arrayListOf("Seleccione")
                    dataWords.worlds?.regularWorlds?.forEach { world ->
                        world.name.let { arrayWorlds.add(it) }
                    }
                    _worlds.value = UIStateWords.Success(dataWords)
                }
                .onFailure { _worlds.value = UIStateWords.Error(it.message) }
        }
    }

//    fun setHighScores(world: String, category: String, vocation: String) {
//        viewModelScope.launch {
//            runCatching { getHighScoreInformationUseCase(world, category, vocation) }
//                .onSuccess { _highScoreList.value = it.highscores }
//                .onFailure { _highScoreList.value = null }
//        }
//    }

    sealed interface UIStateWords {
        object Idle: UIStateWords
        object Loading: UIStateWords
        data class Success(val worlds: WorldsResponse? = null): UIStateWords
        data class Error(val message: String? = null) : UIStateWords
    }

    sealed interface UIStateHighScores {
        object Idle: UIStateHighScores
        object Loading: UIStateHighScores
        data class Success(val highscores: List<Any>? = null): UIStateHighScores
        data class Error(val message: String? = null) : UIStateHighScores
    }

}