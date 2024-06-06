package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.CriatureInformation.Creature;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.Repository.RepositoryCreatures;

public class ViewModelCreatures extends ViewModel {
    RepositoryCreatures repository = new RepositoryCreatures();
    public MutableLiveData<Criatures> _creatures = new MutableLiveData<>();
    public MutableLiveData<Criatures> creature() { return _creatures; }
    public ViewModelCreatures() {
        repository.creatures(_creatures);
    }

}
