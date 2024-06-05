package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Repository.Repository;

import java.util.ArrayList;

public class ViewModelWorlds extends ViewModel {
    Repository repository = new Repository();
    private final MutableLiveData<Worlds> _worlds = new MutableLiveData<>();

    public MutableLiveData<Worlds> worlds() {
        return _worlds;
    }

    public void setWorlds() {
        repository.worlds(_worlds);
    }

}
