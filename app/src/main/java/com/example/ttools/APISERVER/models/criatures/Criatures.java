package com.example.ttools.APISERVER.models.criatures;

import java.util.ArrayList;

public class Criatures {
    Boosted boosted;
    private ArrayList<CriaturesList> creature_list = new ArrayList<CriaturesList>();

    public Boosted getBoosted() {
        return boosted;
    }

    public void setBoosted(Boosted boosted) {
        this.boosted = boosted;
    }

    public ArrayList<CriaturesList> getCriatures_list() {
        return creature_list;
    }

    public void setCriatures_list(ArrayList<CriaturesList> criatures_list) {
        this.creature_list = criatures_list;
    }
}
