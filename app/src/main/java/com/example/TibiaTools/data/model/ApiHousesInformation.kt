package com.example.TibiaTools.data.model

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.houses.House

@Keep
data class ApiHousesInformation(
    var house: House? = null
)
