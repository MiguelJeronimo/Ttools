package com.example.TibiaTools.data.model.SpellsInformation.spell_information

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.SpellsInformation.SpellList.SpellsList

@Keep
open class Spell_Information(
    var vocation: ArrayList<String>? = null,
    var city: ArrayList<String>? = null,
    var damage_type: String? = null,
    var soul_points: Int = 0,
    var amount: Int = 0,
    var cooldown_alone: Int = 0,
    var cooldown_group: Int = 0
) : SpellsList()
