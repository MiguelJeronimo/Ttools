package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.Guild
import com.example.TibiaTools.domain.usecase.GetGuildsInformationNameUseCase
import kotlinx.coroutines.launch

class ViewModelGuildInformation(
    private val getGuildsInformationNameUseCase: GetGuildsInformationNameUseCase
) : ViewModel() {
    private val _guild = MutableLiveData<Guild?>()
    fun guild(): LiveData<Guild?> = _guild

    fun setGuild(guildName: String) {
        viewModelScope.launch {
            runCatching { getGuildsInformationNameUseCase(guildName) }
                .onSuccess { _guild.value = it.guild }
                .onFailure { _guild.value = null }
        }
    }
}
