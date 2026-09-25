package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.ApiNews;
import com.example.TibiaTools.APISERVER.models.ApiNewsTicker;
import com.example.TibiaTools.APISERVER.models.Worlds.Worlds;
import com.example.TibiaTools.APISERVER.models.criatures.BoostableBosses;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.Repository.RepositoryHome;

public class ViewModelHome extends ViewModel{
    RepositoryHome repositoryHome = new RepositoryHome();

    private final MutableLiveData<Worlds> _worlds = new MutableLiveData<>();

    public MutableLiveData<Worlds> worlds() {return _worlds;}

    public void setWorlds() {
        repositoryHome.worlds(_worlds);
    }
    private final MutableLiveData<String> _rashidLocation = new MutableLiveData<>();
    public LiveData<String> getRashidLocation() {
        return _rashidLocation;
    }

    private final MutableLiveData<Worlds> _playersOnline = new MutableLiveData<>();
    public LiveData<Worlds> playersOnline(){return _playersOnline;}

    private final MutableLiveData<Criatures> _creatureBoss = new MutableLiveData<>();
    public LiveData<Criatures> creatureBoss(){return _creatureBoss;}
    private final MutableLiveData<BoostableBosses> _bostedBoss = new MutableLiveData<>();
    public LiveData<BoostableBosses> bostedBoss(){return _bostedBoss;}

    public final MutableLiveData<ApiNews> _news = new MutableLiveData<>();
    public LiveData<ApiNews> news() { return _news; }

    public final MutableLiveData<ApiNewsTicker> _newTicker = new MutableLiveData<>();
    public LiveData<ApiNewsTicker> newTicker() { return _newTicker; }

    public void setRashirLocation() {
        repositoryHome.getRashidLocation(_rashidLocation);
    }

    public void setPlayersOnline() {
        repositoryHome.playersOnline(_playersOnline);
    }

    public void setCreatureBoss() {
        repositoryHome.creatureBoss(_creatureBoss);
    }
    public void setBostedBoss(){
        repositoryHome.bostedBoss(_bostedBoss);
    }

    public void setNews(){
        repositoryHome.news(_news);
    }

    public void setNewTicker(){
        repositoryHome.newsTickers(_newTicker);
    }

}
