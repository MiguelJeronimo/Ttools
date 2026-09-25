package com.example.TibiaTools.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;

import java.util.ArrayList;

public interface Methods {
   void worlds(MutableLiveData<Worlds> _worlds);
   void worlds(MutableLiveData<ArrayList<String>> _worlds, ArrayList<String> worlds);
}
