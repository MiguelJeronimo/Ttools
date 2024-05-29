package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.Guild;
import com.example.TibiaTools.Repository.RepositoryGuildsInformation;

public class ViewModelGuildInformation extends ViewModel {
    RepositoryGuildsInformation repositoryGuildsInformation = new RepositoryGuildsInformation();
    private final MutableLiveData<Guild> _guild = new MutableLiveData<>();
    public MutableLiveData<Guild> guild(){ return _guild; }

    public void setGuild(String guildName){
        repositoryGuildsInformation.guildInformation(guildName, _guild);
    }
}
