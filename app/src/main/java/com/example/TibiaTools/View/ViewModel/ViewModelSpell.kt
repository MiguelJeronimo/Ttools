package com.example.TibiaTools.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TibiaTools.data.model.Spell
import com.example.TibiaTools.domain.usecase.GetSpellInformationUseCase
import kotlinx.coroutines.launch

class ViewModelSpell(
    private val getSpellInformationUseCase: GetSpellInformationUseCase
) : ViewModel() {
    private val _spell = MutableLiveData<Spell?>()
    fun spell(): LiveData<Spell?> = _spell

    fun setSpell(id: String) {
        viewModelScope.launch {
            runCatching { getSpellInformationUseCase(id) }
                .onSuccess { _spell.value = it.spell }
                .onFailure { _spell.value = null }
        }
    }
}
