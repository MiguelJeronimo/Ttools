package com.example.TibiaTools.domain.model

data class SpellsResponse(
    val spells: SpellsData? = null
)

data class SpellInformationResponse(
    val spell: SpellDetail? = null
)

data class SpellsData(
    val spellsList: List<SpellListItem> = emptyList(),
    val spell: SpellDetail? = null
) {
    val spells_list: List<SpellListItem> get() = spellsList
}

data class SpellListItem(
    val name: String = "",
    val spellId: String = "",
    val formula: String = "",
    val level: String = "",
    val mana: String = "",
    val price: String = "",
    val isGroupAttack: Boolean = false,
    val isGroupHealing: Boolean = false,
    val isGroupSupport: Boolean = false,
    val isTypeInstant: Boolean = false,
    val isTypeRune: Boolean = false,
    val isPremiumOnly: Boolean = false
) {
    val spell_id: String get() = spellId
    fun isGroup_attack(): Boolean = isGroupAttack
    fun isGroup_healing(): Boolean = isGroupHealing
    fun isGroup_support(): Boolean = isGroupSupport
    fun isType_instant(): Boolean = isTypeInstant
    fun isType_rune(): Boolean = isTypeRune
    fun isPremium_only(): Boolean = isPremiumOnly
    fun formula(): String = formula
    fun level(): String = level
    fun mana(): String = mana
}

data class SpellDetail(
    val name: String = "",
    val spellId: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val spellInformation: SpellInfoData? = null,
    val runeInformation: RuneInfoData? = null
) {
    val spell_id: String get() = spellId
    val image_url: String get() = imageUrl
    val spell_information: SpellInfoData? get() = spellInformation
    val rune_information: RuneInfoData? get() = runeInformation
}

data class SpellInfoData(
    val vocation: List<String> = emptyList(),
    val city: List<String> = emptyList(),
    val damageType: String = "",
    val soulPoints: Int = 0,
    val amount: Int = 0,
    val cooldownAlone: Int = 0,
    val cooldownGroup: Int = 0,
    val formula: String = "",
    val level: String = "",
    val mana: String = "",
    val isGroupAttack: Boolean = false,
    val isGroupHealing: Boolean = false,
    val isGroupSupport: Boolean = false,
    val isTypeInstant: Boolean = false,
    val isTypeRune: Boolean = false,
    val isPremiumOnly: Boolean = false
) {
    val damage_type: String get() = damageType
    val soul_points: Int get() = soulPoints
    val cooldown_alone: Int get() = cooldownAlone
    val cooldown_group: Int get() = cooldownGroup
    fun formula(): String = formula
    fun level(): String = level
    fun mana(): String = mana
    fun isGroup_attack(): Boolean = isGroupAttack
    fun isGroup_healing(): Boolean = isGroupHealing
    fun isGroup_support(): Boolean = isGroupSupport
    fun isType_instant(): Boolean = isTypeInstant
    fun isType_rune(): Boolean = isTypeRune
    fun isPremium_only(): Boolean = isPremiumOnly
}

data class RuneInfoData(
    val vocation: List<String> = emptyList()
)
