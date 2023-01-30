package com.example.TibiaTools.APISERVER.models.criatures;

import androidx.annotation.Keep;

@Keep
public class BoostableBosses extends Criatures {

    public BoostableBosses(Boosted boosted) {
        super(boosted);
    }
}
