package com.example.ttools.APISERVER.models.GuildInformation.guilds;

import com.example.ttools.APISERVER.models.GuildInformation.guilds.Active.Active;

import java.util.ArrayList;

public class Guilds {

    ArrayList<Active> active;

    public ArrayList<Active> getActive() {
        return active;
    }

    public void setActive(ArrayList<Active> active) {
        this.active = active;
    }
}
