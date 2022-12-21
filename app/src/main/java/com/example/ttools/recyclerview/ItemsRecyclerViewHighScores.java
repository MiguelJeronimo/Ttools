package com.example.ttools.recyclerview;

public class ItemsRecyclerViewHighScores {
    String rank, name, vocation, world, level, value, title;
    public ItemsRecyclerViewHighScores(
        String rank,
        String name,
        String vocation,
        String world,
        String level,
        String value,
        String tittle
    ){
        this.rank = rank;
        this.name = name;
        this.vocation = vocation;
        this.world = world;
        this.level = level;
        this.value = value;
        this.title = tittle;
    }

    public String getRank() {
        return rank;
    }

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

    public String getLevel() {
        return level;
    }

    public String getValue() {
        return value;
    }
}
