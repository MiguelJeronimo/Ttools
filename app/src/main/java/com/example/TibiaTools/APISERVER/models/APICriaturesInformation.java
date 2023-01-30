package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.CriatureInformation.Creature;

@Keep
public class APICriaturesInformation {
    Creature creature;

    public Creature getCreature() {
        return creature;
    }
}
