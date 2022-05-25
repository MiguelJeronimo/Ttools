package com.example.ttools.APISERVER.models;

import com.example.ttools.APISERVER.models.criatures.Criatures;
import com.example.ttools.APISERVER.models.criatures.CriaturesList;

import java.util.ArrayList;

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
