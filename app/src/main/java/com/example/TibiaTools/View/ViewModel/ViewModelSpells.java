package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.SpellsInformation.Spells;
import com.example.TibiaTools.Repository.RepositorySpells;

public class ViewModelSpells extends ViewModel {
    RepositorySpells repository = new RepositorySpells();
    private MutableLiveData<Spells> _spells = new MutableLiveData<>();

    public MutableLiveData<Spells> spells() {
        return _spells;
    }

    public ViewModelSpells(){
        repository.spells(_spells);
    }
}
