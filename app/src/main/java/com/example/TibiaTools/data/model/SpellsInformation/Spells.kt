package com.example.TibiaTools.data.model.SpellsInformation

import androidx.annotation.Keep
import com.example.TibiaTools.data.model.SpellsInformation.SpellList.Spell
import com.example.TibiaTools.data.model.SpellsInformation.SpellList.SpellsList

@Keep
data class Spells(
    var spells_list: ArrayList<SpellsList>? = null,
    var spell: Spell? = null
)
