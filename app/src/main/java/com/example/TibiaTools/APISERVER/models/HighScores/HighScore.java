package com.example.TibiaTools.APISERVER.models.HighScores;

import com.example.TibiaTools.APISERVER.models.HighScores.HighscoreList.HighscoreList;

import java.util.ArrayList;

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
