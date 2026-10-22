package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.Guilds
import com.example.TibiaTools.domain.usecase.GetGuildsInformationUseCase
import com.example.TibiaTools.domain.usecase.GetWorldsUseCase
import kotlinx.coroutines.launch

class ViewModelGuilds(
    private val getGuildsInformationUseCase: GetGuildsInformationUseCase,
    private val getWorldsUseCase: GetWorldsUseCase
) : ViewModel() {
    private val _worlds = MutableLiveData<ArrayList<String>?>()
    fun Worlds(): LiveData<ArrayList<String>?> = _worlds

    private val _guild = MutableLiveData<Guilds?>()
    fun Guild(): LiveData<Guilds?> = _guild

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

    fun setGuild(guildName: String) {
        viewModelScope.launch {
            runCatching { getGuildsInformationUseCase(guildName) }
                .onSuccess { _guild.value = it.guilds }
                .onFailure { _guild.value = null }
        }
    }
}
