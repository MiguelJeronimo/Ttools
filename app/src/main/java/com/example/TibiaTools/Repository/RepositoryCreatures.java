package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.APICriatures;
import com.example.TibiaTools.APISERVER.models.CriatureInformation.Creature;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryCreatures extends Repository{
    public void creatures(MutableLiveData<Criatures> _creatures){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<APICriatures> call = tibiaAPIServer.getCreature();
        call.enqueue(new Callback<APICriatures>() {
            @Override
            public void onResponse(@NonNull Call<APICriatures> call, @NonNull Response<APICriatures> response) {
                if(response.isSuccessful()){
                    APICriatures apiCriatures = response.body();
                    Criatures creatures = apiCriatures.getCreatures();
                    _creatures.setValue(creatures);
                } else{
                    _creatures.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APICriatures> call, @NonNull Throwable t) {
                _creatures.setValue(null);
            }
        });
    }
}
