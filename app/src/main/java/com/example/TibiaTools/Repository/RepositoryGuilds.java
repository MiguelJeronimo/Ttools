package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuilds;
import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Guilds;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryGuilds extends Repository {

    public void guilds(String worldName, MutableLiveData<Guilds> _guild) {
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiGuilds> call = tibiaAPIServer.getGuildsInformation(worldName);
        call.enqueue(new Callback<ApiGuilds>() {
            @Override
            public void onResponse(@NonNull Call<ApiGuilds> call, @NonNull Response<ApiGuilds> response) {
                if (response.isSuccessful()){
                    ApiGuilds apiGuilds = response.body();
                    assert apiGuilds != null;
                    Guilds guilds = apiGuilds.getGuilds();
                    _guild.setValue(guilds);
                } else {
                    _guild.setValue(null);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ApiGuilds> call, @NonNull Throwable t) {
                _guild.setValue(null);
            }
        });
    }
}
