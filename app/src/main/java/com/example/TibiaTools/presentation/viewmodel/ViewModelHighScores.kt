package com.example.TibiaTools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.TibiaTools.domain.model.HighScoreItem
import com.example.TibiaTools.domain.model.WorldsResponse
import com.example.TibiaTools.domain.usecase.GetHighScoreInformationUseCase
import com.example.TibiaTools.domain.usecase.GetHighScoreInformationUseCasePager
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ViewModelHighScores(
    private val getHighScoreInformationUseCase: GetHighScoreInformationUseCase,
    private val getHighScoreInformationUseCasePager: GetHighScoreInformationUseCasePager,
    private val getWorldsUseCase: GetWorldsUseCase
): ViewModel() {

    private val _worlds = MutableStateFlow<UIStateWords>(UIStateWords.Idle)
    val worlds = _worlds.asStateFlow()

    private val world = MutableStateFlow("")
    private val category = MutableStateFlow("")
    private val vocation = MutableStateFlow("")

    val ranks: Flow<PagingData<HighScoreItem>> = combine(
        world,
        category,
        vocation
    ) { world, category, vocation ->
        Triple(world, category, vocation)
    }.flatMapLatest { (world, category, vocation) ->
        getHighScoreInformationUseCasePager(world, category, vocation, 20)
    }.cachedIn(viewModelScope)

    init {
        loadWorlds()
    }


    fun setWorld(text: String){
        world.value = text
    }
    fun setCategory(text: String){
        category.value = text
    }
    fun setVocation(text: String){
        vocation.value = text
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