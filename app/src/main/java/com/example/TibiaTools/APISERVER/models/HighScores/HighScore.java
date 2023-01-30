package com.example.TibiaTools.APISERVER.models.HighScores;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.HighScores.HighscoreList.HighscoreList;

import java.util.ArrayList;
@Keep
public class HighScore {
    String world, category, vocation;
    int highscore_age;
    ArrayList<HighscoreList> highscore_list = new ArrayList<>();

    public ArrayList<HighscoreList> getHighscore_list() {
        return highscore_list;
    }

    public String getWorld() {
        return world;
    }

    public String getCategory() {
        return category;
    }

    public String getVocation() {
        return vocation;
    }

    public int getHighscore_age() {
        return highscore_age;
    }

}
