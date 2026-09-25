package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.APICriatures;
import com.example.TibiaTools.APISERVER.models.APICriaturesInformation;
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
                    assert apiCriatures != null;
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
    public void creature(String raceCreature, MutableLiveData<Creature> _creature){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<APICriaturesInformation> call = tibiaAPIServer.getCriatureInformation(raceCreature);
        call.enqueue(new Callback<APICriaturesInformation>() {
            @Override
            public void onResponse(@NonNull Call<APICriaturesInformation> call, @NonNull Response<APICriaturesInformation> response) {
                if (response.isSuccessful()){
                    APICriaturesInformation apiCriaturesInformation = response.body();
                    assert apiCriaturesInformation != null;
                    Creature creature = apiCriaturesInformation.getCreature();
                    _creature.setValue(creature);
                } else {
                    _creature.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APICriaturesInformation> call, @NonNull Throwable t) {
                _creature.setValue(null);
            }
        });
    }
}
