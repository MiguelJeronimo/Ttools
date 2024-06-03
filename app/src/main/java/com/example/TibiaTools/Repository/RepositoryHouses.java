package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiHouses;
import com.example.TibiaTools.APISERVER.models.Houses.Houses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryHouses extends Repository{

    public void houses(String World, String Town, MutableLiveData<Houses> _houses){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiHouses> call = tibiaAPIServer.getHousesInformation(World, Town);
        call.enqueue(new Callback<ApiHouses>() {
            @Override
            public void onResponse(@NonNull Call<ApiHouses> call, @NonNull Response<ApiHouses> response) {
                if (response.isSuccessful()){
                    ApiHouses apiHouses = response.body();
                    assert apiHouses != null;
                    Houses houses = apiHouses.getHouses();
                    _houses.setValue(houses);
                } else {
                    _houses.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiHouses> call, @NonNull Throwable t) {
                _houses.setValue(null);
            }
        });
    }

}
