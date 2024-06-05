package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.RegularWorlds;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements Methods{
    InstanciaRetrofit services = new InstanciaRetrofit();
    String url = "https://api.tibiadata.com/";

    public void worlds(MutableLiveData<Worlds> _worlds){
        ArrayList<String> arrayWorlds = new ArrayList<>();
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<DataWords> call = tibiaAPIServer.getWorlds();
        call.enqueue(new Callback<DataWords>() {
            @Override
            public void onResponse(@NonNull Call<DataWords> call, @NonNull Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    assert dataWords != null;
                    Worlds worlds = dataWords.getWorlds();
                    _worlds.setValue(worlds);
                } else {
                    _worlds.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataWords> call, @NonNull Throwable t) {
                _worlds.setValue(null);
            }
        });
    }

    public void worlds(MutableLiveData<ArrayList<String>> _worlds, ArrayList<String> worlds){
        ArrayList<String> arrayWorlds = new ArrayList<>();
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<DataWords> call = tibiaAPIServer.getWorlds();
        call.enqueue(new Callback<DataWords>() {
            @Override
            public void onResponse(@NonNull Call<DataWords> call, @NonNull Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    assert dataWords != null;
                    Worlds worlds = dataWords.getWorlds();
                    //foreach en java
                    arrayWorlds.add("Seleccione");
                    for (RegularWorlds mundos: worlds.getRegular_worlds()) {
                        arrayWorlds.add(mundos.getName());
                    }
                    _worlds.setValue(arrayWorlds);
                } else {
                    _worlds.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataWords> call, @NonNull Throwable t) {
                _worlds.setValue(null);
            }
        });
    }
}
