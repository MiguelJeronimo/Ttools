package com.example.ttools.recyclerview;

public class itemsRecyclerViewGuildsName {
    String name, title, rank, vocation, joined, status;
    int level;

    public itemsRecyclerViewGuildsName(
            String name,
            String title,
            String vocation,
            int level,
            String joined,
            String status){
        this.name = name;
        this.title = title;
        this.vocation = vocation;
        this.level = level;
        this.joined = joined;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getVocation() {
        return vocation;
    }

    public int getLevel() {
        return level;
    }

    public String getJoined() {
        return joined;
    }

    public String getStatus() {
        return status;
    }

    public String getRank() {
        return rank;
    }
}
