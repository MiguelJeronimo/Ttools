package com.example.ttools.APISERVER.models.HighScores;

import com.example.ttools.APISERVER.models.HighScores.HighscoreList.HighscoreList;

import java.util.ArrayList;

public class HighScores {
    String world, category, vocation;
    int highscore_age;
    ArrayList<HighscoreList> highscore_list;

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
