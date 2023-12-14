package com.example.TibiaTools.APISERVER.models.criatures;

import androidx.annotation.Keep;

@Keep
public class BoostableBosses {
    Boosted boosted;

    public void setBoosted(Boosted boosted) {
        this.boosted = boosted;
    }

    public Boosted getBoosted() {
        return boosted;
    }
}
