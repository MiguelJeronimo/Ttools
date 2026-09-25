package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.CriatureInformation.Creature;
import com.example.TibiaTools.Repository.RepositoryCreatures;

public class ViewModelCreature extends ViewModel {
    RepositoryCreatures repository = new RepositoryCreatures();
    public MutableLiveData<Creature> _creature = new MutableLiveData<>();

    public MutableLiveData<Creature> creature() {
        return _creature;
    }

    public void setCreature(String creatureRace){
        repository.creature(creatureRace, _creature);
    }
}
