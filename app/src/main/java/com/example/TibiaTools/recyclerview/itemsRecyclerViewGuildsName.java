package com.example.TibiaTools.recyclerview;

public class itemsRecyclerViewGuildsName {
    String name, title, rank, vocation, joined, status;
    int level;

    public itemsRecyclerViewGuildsName(
            String name,
            String title,
            String rank,
            String vocation,
            String level,
            String joined,
            String status){
        this.name = name;
        this.title = title;
        this.rank = rank;
        this.vocation = vocation;
        this.level = Integer.parseInt(level);
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

    public String getLevel() {
        return String.valueOf(level);
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
