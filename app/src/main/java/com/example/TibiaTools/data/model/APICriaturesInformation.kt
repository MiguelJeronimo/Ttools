package com.example.TibiaTools.data.model

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.criatureinformation.Creature

@Keep
data class APICriaturesInformation(
    var creature: Creature? = null
)
