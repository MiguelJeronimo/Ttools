package com.example.TibiaTools.data.model

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.criatures.BoostableBosses

@Keep
data class APIBoostableBosses(
    var boostable_bosses: BoostableBosses? = null
)
