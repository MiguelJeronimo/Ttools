package com.example.ttools.APISERVER.models.GuilsInformation;

import com.example.ttools.APISERVER.models.GuilsInformation.Activate.Activate;

import java.util.ArrayList;

public class Guilds {
    private ArrayList<Activate> active;

    public ArrayList<Activate> getActive() {
        return active;
    }

    public void setActive(ArrayList<Activate> active) {
        this.active = active;
    }
}
