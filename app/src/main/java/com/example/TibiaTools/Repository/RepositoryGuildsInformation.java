package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.GuildInformation.ApiGuildsName;
import com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.Guild;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryGuildsInformation extends Repository{

    public void guildInformation(String name, MutableLiveData<Guild> _guild){
        TibiaAPIServer tibiaAPIServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiGuildsName> call = tibiaAPIServer.getGuildsInformationName(name);
        call.enqueue(new Callback<ApiGuildsName>() {
            @Override
            public void onResponse(@NonNull Call<ApiGuildsName> call, @NonNull Response<ApiGuildsName> response) {
                if (response.isSuccessful()) {
                    ApiGuildsName apiGuildsName = response.body();
                    assert apiGuildsName != null;
                    Guild guild = apiGuildsName.getGuilds();
                    _guild.setValue(guild);
                } else{
                    _guild.setValue(null);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ApiGuildsName> call, @NonNull Throwable t) {
                _guild.setValue(null);
            }
        });
    }
}
