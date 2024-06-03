package com.example.TibiaTools.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.TibiaTools.APISERVER.TibiaAPIServer;
import com.example.TibiaTools.APISERVER.models.ApiHighScores;
import com.example.TibiaTools.APISERVER.models.HighScores.HighScore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryHighScore extends Repository{

    public void highScore(String world, String category, String vocation, MutableLiveData<HighScore> _highScoreList){
        TibiaAPIServer apiServer = services.getRetrofit(url).create(TibiaAPIServer.class);
        Call<ApiHighScores> call = apiServer.getHighScoreInformation(world,category,vocation);
        call.enqueue(new Callback<ApiHighScores>() {
            @Override
            public void onResponse(@NonNull Call<ApiHighScores> call, @NonNull Response<ApiHighScores> response) {
                if (response.isSuccessful()){
                    ApiHighScores apiHighScores = response.body();
                    assert apiHighScores != null;
                    HighScore highScore = apiHighScores.getHighScores();
                    _highScoreList.setValue(highScore);
                }else{
                    _highScoreList.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiHighScores> call, @NonNull Throwable t) {
                _highScoreList.setValue(null);
            }
        });
    }
}
