package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.Houses.House;
import com.example.TibiaTools.Repository.RepositoryHouseInformation;

public class ViewModelHouseInformation extends ViewModel {
    private final MutableLiveData<House> _house = new MutableLiveData<>();
    private final RepositoryHouseInformation repository = new RepositoryHouseInformation();
    public MutableLiveData<House> getHouse() {
        return _house;
    }
    public void setHouse(String world, String idHouse) {
        repository.houseInformation(world, idHouse, _house);
    }
}
