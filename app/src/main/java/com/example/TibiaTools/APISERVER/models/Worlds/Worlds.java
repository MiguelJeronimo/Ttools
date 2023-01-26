package com.example.TibiaTools.APISERVER.models.Worlds;

import java.util.ArrayList;

public class Worlds {
    private int players_online;
    private String record_date;
    private ArrayList<RegularWorlds> regular_worlds = new ArrayList<RegularWorlds>();

    public int getPlayers_online() {
        return players_online;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }

    public String getRecord_date() {
        return record_date;
    }

    public void setPlayers_online(int players_online) {
        this.players_online = players_online;
    }

    public ArrayList<RegularWorlds> getRegular_worlds() {
        return regular_worlds;
    }

    public void setRegular_worlds(ArrayList<RegularWorlds> regular_worlds) {
        this.regular_worlds = regular_worlds;
    }
}
