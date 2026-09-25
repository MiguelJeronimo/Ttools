package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiHousesInformation;
import com.example.TibiaTools.APISERVER.models.Houses.House;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryHouseInformation extends Repository{

    public void houseInformation(String world, String id_house, MutableLiveData<House> _house){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiHousesInformation> call = tibiaAPIServer.getHouseInformation(world, id_house);
        call.enqueue(new Callback<ApiHousesInformation>() {
            @Override
            public void onResponse(@NonNull Call<ApiHousesInformation> call, @NonNull Response<ApiHousesInformation> response) {
                if (response.isSuccessful()){
                    ApiHousesInformation apiHousesInformation = response.body();
                    assert apiHousesInformation != null;
                    House house = apiHousesInformation.getHouse();
                    _house.setValue(house);

                } else {
                    _house.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiHousesInformation> call, @NonNull Throwable t) {
                _house.setValue(null);
            }
        });
    }
}
