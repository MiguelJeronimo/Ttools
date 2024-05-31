package com.example.TibiaTools.View.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.TibiaTools.APISERVER.models.HighScores.HighScore;
import com.example.TibiaTools.Repository.RepositoryHighScore;

import java.util.ArrayList;
import java.util.List;

public class ViewModelHighScore extends ViewModel {
    RepositoryHighScore repositoryHighScore = new RepositoryHighScore();
    private final MutableLiveData<ArrayList<String>> _worlds = new MutableLiveData<>();
    public MutableLiveData<ArrayList<String>> Worlds() {return _worlds;}

    private final MutableLiveData<HighScore> _highScoreList = new MutableLiveData<>();
    public MutableLiveData<HighScore> highScoreList() {return _highScoreList;}

    public ViewModelHighScore() {
        repositoryHighScore.worlds(_worlds);
    }

    public void setHighScores(String world,String category,String vocation) {
        repositoryHighScore.highScore(world,category,vocation,_highScoreList);
    }

}
