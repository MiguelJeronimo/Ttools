package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.APIServicesTibia;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Information.Information;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Information.Status;
import com.example.TibiaTools.Operaciones.InstanciaRetrofit;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryCharacters extends Repository{
    public void characters(String characterName, MutableLiveData<APIServicesTibia> _characters){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<APIServicesTibia> call = tibiaAPIServer.getPersonajes(characterName);
        call.enqueue(new Callback<APIServicesTibia>() {
            @Override
            public void onResponse(@NonNull Call<APIServicesTibia> call, @NonNull Response<APIServicesTibia> response) {
                APIServicesTibia character = response.body();
                if (response.isSuccessful()){
                    _characters.setValue(character);
                } else {
                    APIServicesTibia apiServicesTibia = new APIServicesTibia();
                    Status status = new Status();
                    status.setHttpCode(response.code());
                    status.setMessage("could not find character");
                    Information information = new Information();
                    information.setStatus(status);
                    apiServicesTibia.setInformation(information);
                    _characters.setValue(apiServicesTibia);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIServicesTibia> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                _characters.setValue(null);
            }
        });
    }
}
