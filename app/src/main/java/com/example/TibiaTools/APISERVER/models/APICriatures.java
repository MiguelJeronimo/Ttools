package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.criatures.BoostableBosses;
import com.example.TibiaTools.APISERVER.models.criatures.Criatures;
import com.example.TibiaTools.APISERVER.models.criatures.CriaturesList;

import java.util.ArrayList;

@Keep
public class APICriatures {
    Criatures creatures;

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
