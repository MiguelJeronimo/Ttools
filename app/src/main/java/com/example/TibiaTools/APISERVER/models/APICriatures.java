package com.example.TibiaTools.APISERVER.models;

import com.example.TibiaTools.APISERVER.models.criatures.BoostableBosses;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.APISERVER.models.criatures.CriaturesList;

import java.util.ArrayList;

public class APICriatures {
    Criatures creatures;
    BoostableBosses boostable_bosses;

    public BoostableBosses getBoostable_bosses() {
        return boostable_bosses;
    }

    ArrayList<CriaturesList> creatures_List;

    public ArrayList<CriaturesList> getCreatures_List() {
        return creatures_List;
    }

    public void setCreatures_List(ArrayList<CriaturesList> creatures_List) {
        this.creatures_List = creatures_List;
    }

    public Criatures getCreatures() {
        return creatures;
    }

    public void setCreatures(Criatures creatures) {
        this.creatures = creatures;
    }

}
