package com.example.TibiaTools.data.model

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.houses.Houses

@Keep
data class ApiHouses(
    var houses: Houses? = null
)
