package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.HighScores.HighScore;

@Keep
public class ApiHighScores {
    HighScore highscores;

    public HighScore getHighScores() {
        return highscores;
    }
}
