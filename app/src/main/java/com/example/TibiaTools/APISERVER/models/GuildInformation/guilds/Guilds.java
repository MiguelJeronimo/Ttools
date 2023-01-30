package com.example.TibiaTools.APISERVER.models.GuildInformation.guilds;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Active.Active;

import java.util.ArrayList;
@Keep
public class Guilds {

    ArrayList<Active> active;

    public ArrayList<Active> getActive() {
        return active;
    }

    public void setActive(ArrayList<Active> active) {
        this.active = active;
    }
}
