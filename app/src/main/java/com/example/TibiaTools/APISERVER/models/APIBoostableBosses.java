package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.criatures.BoostableBosses;

@Keep
public class APIBoostableBosses {
    BoostableBosses boostable_bosses;

    public BoostableBosses getBoostable_bosses() {
        return boostable_bosses;
    }
}
