package com.example.TibiaTools.APISERVER.models.Worlds;

import androidx.annotation.Keep;

@Keep
public class DataWords {
    private Worlds worlds;

    public void setWorlds(Worlds worlds) {
        this.worlds = worlds;
    }

    public Worlds getWorlds() {
        return worlds;
    }
}
