package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.Houses.House;
import com.example.TibiaTools.APISERVER.models.Houses.Houses;
import com.example.TibiaTools.Repository.RepositoryHouses;

import java.util.ArrayList;
import java.util.List;

public class ViewModelHouses extends ViewModel {
    RepositoryHouses repositoryHouses = new RepositoryHouses();
    private final MutableLiveData<Houses> _houses = new MutableLiveData<Houses>();
    public LiveData<Houses> houses() { return _houses; }
    private final MutableLiveData<ArrayList<String>> _worlds = new MutableLiveData<>();
    public MutableLiveData<ArrayList<String>> Worlds() {return _worlds;}

    public ViewModelHouses(){
        repositoryHouses.worlds(_worlds);
    }
    public void setHouses(String world, String town) {
        repositoryHouses.houses(world, town,_houses);
    }
}
