package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class ApiSpells(
    var spells: Spells? = null
)

@Keep
data class ApiSpellsInformation(
    var spell: Spell? = null
)

@Keep
data class Spells(
    var spells_list: ArrayList<SpellsList>? = null,
    var spell: Spell? = null
)

@Keep
open class SpellsList(
    var name: String? = null,
    var spell_id: String? = null,
    var formula: String? = null,
    var level: String? = null,
    var mana: String? = null,
    var price: String? = null,
    var group_attack: Boolean = false,
    var group_healing: Boolean = false,
    var group_support: Boolean = false,
    var type_instant: Boolean = false,
    var type_rune: Boolean = false,
    var premium_only: Boolean = false
) {
    fun isGroup_attack(): Boolean = group_attack
    fun isGroup_healing(): Boolean = group_healing
    fun isGroup_support(): Boolean = group_support
    fun isType_instant(): Boolean = type_instant
    fun isType_rune(): Boolean = type_rune
    fun isPremium_only(): Boolean = premium_only
}

@Keep
class Spell(
    name: String? = null,
    spell_id: String? = null,
    var image_url: String? = null,
    var description: String? = null,
    var spell_information: Spell_Information? = null,
    var rune_information: rune_information? = null
) : SpellsList(name = name, spell_id = spell_id)

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

@Keep
data class SpellVocation(
    var name: String? = null
)

@Keep
class rune_information(
    vocation: ArrayList<String>? = null
) : Spell_Information(vocation = vocation)
