package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.APIBoostableBosses;
import com.example.TibiaTools.APISERVER.models.APICriatures;
import com.example.TibiaTools.APISERVER.models.ApiNews;
import com.example.TibiaTools.APISERVER.models.ApiNewsTicker;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.APISERVER.models.criatures.BoostableBosses;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryHome extends Repository{
    public void getRashidLocation(MutableLiveData<String> _rashidLocation){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit("").create(TibiaAPIServer.class);
        Call<String> call = tibiaAPIServer.getRashidLocalitation();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                System.out.println(response.body());
                if(response.isSuccessful()){
                    _rashidLocation.setValue(response.body());
                } else{
                    _rashidLocation.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                System.out.println("ERROR RASHID"+t.getMessage());
                _rashidLocation.setValue(null);
            }
        });
    }

    public void playersOnline(MutableLiveData<Worlds> _playersOnline){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call <DataWords> call = tibiaAPIServer.getWorlds();
        call.enqueue(new Callback<DataWords>() {
            @Override
            public void onResponse(@NonNull Call<DataWords> call, @NonNull Response<DataWords> response) {
                if (response.isSuccessful()){
                    DataWords dataWords = response.body();
                    Worlds worlds = dataWords.getWorlds();
                    _playersOnline.setValue(worlds);
                }else{
                    _playersOnline.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataWords> call, @NonNull Throwable t) {
                System.out.println("Error players onlines "+t.getMessage());
                _playersOnline.setValue(null);
            }
        });
    }

    public void creatureBoss(MutableLiveData<Criatures> _creatureBoss){
        TibiaAPIServer tibiaAPIServer =  services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<APICriatures> call = tibiaAPIServer.getCreature();
        call.enqueue(new Callback<APICriatures>() {
            @Override
            public void onResponse(@NonNull Call<APICriatures> call, @NonNull Response<APICriatures> response) {
                if (response.isSuccessful()){
                    APICriatures apiCriatures = response.body();
                    Criatures criatures = apiCriatures.getCreatures();
                    _creatureBoss.setValue(criatures);
                } else {
                    _creatureBoss.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APICriatures> call, @NonNull Throwable t) {
                _creatureBoss.setValue(null);
            }
        });
    }

    public void bostedBoss(MutableLiveData<BoostableBosses> _bostedBoss){
        TibiaAPIServer apiServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<APIBoostableBosses> call = apiServer.getBoostableBosses();
        call.enqueue(new Callback<APIBoostableBosses>() {
            @Override
            public void onResponse(@NonNull Call<APIBoostableBosses> call, @NonNull Response<APIBoostableBosses> response) {
                if (response.isSuccessful()){
                    APIBoostableBosses apiCriatures = response.body();
                    assert apiCriatures != null;
                    BoostableBosses boostableBosses = apiCriatures.getBoostable_bosses();
                    _bostedBoss.setValue(boostableBosses);
                } else {
                    _bostedBoss.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIBoostableBosses> call, @NonNull Throwable t) {
                _bostedBoss.setValue(null);
            }
        });
    }

    public void news(MutableLiveData<ApiNews> _news){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiNews> call = tibiaAPIServer.getNewsLatest();
        call.enqueue(new Callback<ApiNews>() {
            @Override
            public void onResponse(@NonNull Call<ApiNews> call, @NonNull Response<ApiNews> response) {
                if (response.isSuccessful()){
                    ApiNews apiNews = response.body();
                    assert apiNews != null;
                    _news.setValue(apiNews);
                } else {
                    _news.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiNews> call, @NonNull Throwable t) {
                _news.setValue(null);
            }
        });
    }

    public void newsTickers(MutableLiveData<ApiNewsTicker> _newTicker){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiNewsTicker> call = tibiaAPIServer.getNewsTickers();
        call.enqueue(new Callback<ApiNewsTicker>() {
            @Override
            public void onResponse(@NonNull Call<ApiNewsTicker> call, @NonNull Response<ApiNewsTicker> response) {
                if (response.isSuccessful()){
                    ApiNewsTicker newsTicker = response.body();
                    _newTicker.setValue(newsTicker);
                } else {
                    _newTicker.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiNewsTicker> call, @NonNull Throwable t) {
                _newTicker.setValue(null);
            }
        });
    }

}
