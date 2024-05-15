package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.Worlds.DataWords;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryHome {
    InstanciaRetrofit services = new InstanciaRetrofit();

    public void getRashidLocation(MutableLiveData<String> _rashidLocation){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit("https://api.tibialabs.com").create(TibiaAPIServer.class);
        Call<String> call = tibiaAPIServer.getRashidLocalitation();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    _rashidLocation.setValue(response.body());
                } else{
                    _rashidLocation.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                _rashidLocation.setValue(null);
            }
        });
    }

    public void playersOnline(MutableLiveData<Worlds> _playersOnline){
        String url = "https://api.tibiadata.com/";
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
            public void onFailure(Call<DataWords> call, Throwable t) {
                System.out.println("Error players onlines "+t.getMessage());
                _playersOnline.setValue(null);
            }
        });
    }

}
