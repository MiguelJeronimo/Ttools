package com.example.TibiaTools.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.Spell;

public class ViewModelSpell extends ViewModel {
    RepositorySpells repository = new RepositorySpells();
    private MutableLiveData<Spell> _spell = new MutableLiveData<>();
    public MutableLiveData<Spell> spell() {
        return _spell;
    }
    public void setSpell(String id) {
        repository.spell(id, _spell);
    }


}
