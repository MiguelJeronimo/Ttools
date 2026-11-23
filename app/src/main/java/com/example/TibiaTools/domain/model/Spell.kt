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
)

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
)

data class SpellDetail(
    val name: String = "",
    val spellId: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val spellInformation: SpellInfoData? = null,
    val runeInformation: RuneInfoData? = null
)

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
)

data class RuneInfoData(
    val vocation: List<String> = emptyList()
)
