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

public class Repository {
    InstanciaRetrofit services = new InstanciaRetrofit();
    String url = "https://api.tibiadata.com/";
    public void worlds(MutableLiveData<ArrayList<String>> _guilds){
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
                    _guilds.setValue(arrayWorlds);
                } else {
                    _guilds.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataWords> call, @NonNull Throwable t) {
                _guilds.setValue(null);
            }
        });
    }
}
