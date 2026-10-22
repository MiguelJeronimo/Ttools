package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.APIServicesTibia
import com.example.TibiaTools.domain.usecase.GetCharacterUseCase
import kotlinx.coroutines.launch

class ViewModelCharacters(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {
    private val _characters = MutableLiveData<APIServicesTibia?>()
    fun characters(): LiveData<APIServicesTibia?> = _characters

    fun setCharacters(charName: String) {
        viewModelScope.launch {
            runCatching { getCharacterUseCase(charName) }
                .onSuccess { _characters.value = it }
                .onFailure { _characters.value = null }
        }
    }
}
