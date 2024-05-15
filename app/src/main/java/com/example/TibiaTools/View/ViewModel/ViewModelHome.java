package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Repository.RepositoryHome;

public class ViewModelHome extends ViewModel {
    RepositoryHome repositoryHome = new RepositoryHome();
    private final MutableLiveData<String> _rashidLocation = new MutableLiveData<>();
    public LiveData<String> getRashidLocation() {
        return _rashidLocation;
    }

    private final MutableLiveData<Worlds> _playersOnline = new MutableLiveData<>();
    public LiveData<Worlds> playersOnline(){
        return _playersOnline;
    }

    public void setRashirLocation() {
        repositoryHome.getRashidLocation(_rashidLocation);
    }

    public void setPlayersOnline() {
        repositoryHome.playersOnline(_playersOnline);
    }





}
