package com.example.ttools.Operaciones.information;

import java.util.ArrayList;

public class Worlds {
    private int players_online;
    private ArrayList<RegularWorlds> regular_worlds = new ArrayList<RegularWorlds>();

    public int getPlayers_online() {
        return players_online;
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
