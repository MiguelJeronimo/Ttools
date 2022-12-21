package com.example.ttools.APISERVER.models.HighScores.HighscoreList;

public class HighscoreList {
    int rank, level;
    float value;
    String name, vocation, world, title;

    public String getTitle() {
        return title;
    }

    public String getWorld() {
        return world;
    }

    public String getVocation() {
        return vocation;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public float getValue() {
        return value;
    }
}
