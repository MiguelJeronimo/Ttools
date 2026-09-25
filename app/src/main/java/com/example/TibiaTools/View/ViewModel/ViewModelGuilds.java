package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Guilds;
import com.example.TibiaTools.Repository.RepositoryGuilds;

import java.util.ArrayList;

public class ViewModelGuilds extends ViewModel {
    RepositoryGuilds repository = new RepositoryGuilds();
    private final MutableLiveData<ArrayList<String>> _worlds = new MutableLiveData<>();
    public MutableLiveData<ArrayList<String>> Worlds() {return _worlds;}
    private final MutableLiveData<Guilds> _guild = new MutableLiveData<>();
    public MutableLiveData<Guilds> Guild() {return _guild;}

    //init
    public ViewModelGuilds(){
        repository.worlds(_worlds, null);
    }

    public void setGuild(String guildName){
        repository.guilds(guildName, _guild);
    }

}
