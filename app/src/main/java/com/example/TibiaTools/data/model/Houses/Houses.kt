package com.example.TibiaTools.data.model.Houses

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.Houses.house_list.GuildhallList
import com.example.TibiaTools.data.model.Houses.house_list.HouseList

@Keep
data class Houses(
    var world: String? = null,
    var town: String? = null,
    var house_list: ArrayList<HouseList> = ArrayList(),
    var guildhall_list: ArrayList<GuildhallList>? = null
)
