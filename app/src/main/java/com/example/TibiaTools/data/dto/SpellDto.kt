package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SpellsResponseDto(
    @SerializedName("spells")
    @SerialName("spells")
    val spells: SpellsDataDto? = null,

    @SerializedName("information")
    @SerialName("information")
    val information: InformationDto? = null
)

@Keep
@Serializable
data class SpellInformationResponseDto(
    @SerializedName("spell")
    @SerialName("spell")
    val spell: SpellDetailDto? = null,

    @SerializedName("information")
    @SerialName("information")
    val information: InformationDto? = null
)

@Keep
@Serializable
data class SpellsDataDto(
    @SerializedName("spell_list")
    @SerialName("spell_list")
    val spellsList: List<SpellListItemDto>? = null,

    @SerializedName("spells_filter")
    @SerialName("spells_filter")
    val spellFilter: String? = null
)

@Keep
@Serializable
data class SpellListItemDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("spell_id")
    @SerialName("spell_id")
    val spellId: String? = null,

    @SerializedName("formula")
    @SerialName("formula")
    val formula: String? = null,

    @SerializedName("level")
    @SerialName("level")
    val level: Int = 0,

    @SerializedName("mana")
    @SerialName("mana")
    val mana: Int = 0,

    @SerializedName("price")
    @SerialName("price")
    val price: Int = 0,

    @SerializedName("group_attack")
    @SerialName("group_attack")
    val groupAttack: Boolean = false,

    @SerializedName("group_healing")
    @SerialName("group_healing")
    val groupHealing: Boolean = false,

    @SerializedName("group_support")
    @SerialName("group_support")
    val groupSupport: Boolean = false,

    @SerializedName("type_instant")
    @SerialName("type_instant")
    val typeInstant: Boolean = false,

    @SerializedName("type_rune")
    @SerialName("type_rune")
    val typeRune: Boolean = false,

    @SerializedName("premium_only")
    @SerialName("premium_only")
    val premiumOnly: Boolean = false
)

@Keep
@Serializable
data class SpellDetailDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("spell_id")
    @SerialName("spell_id")
    val spellId: String? = null,

    @SerializedName("image_url")
    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerializedName("description")
    @SerialName("description")
    val description: String? = null,

    @SerializedName("has_spell_information")
    @SerialName("has_spell_information")
    val hasSpellInformation: Boolean = false,

    @SerializedName("spell_information")
    @SerialName("spell_information")
    val spellInformation: SpellInformationDataDto? = null,

    @SerializedName("has_rune_information")
    @SerialName("has_rune_information")
    val hasRuneInformation: Boolean = false,

    @SerializedName("rune_information")
    @SerialName("rune_information")
    val runeInformation: RuneInformationDataDto? = null
)

@Keep
@Serializable
data class SpellInformationDataDto(
    @SerializedName("formula")
    @SerialName("formula")
    val formula: String? = null,

    @SerializedName("vocation")
    @SerialName("vocation")
    val vocation: List<String>? = null,

    @SerializedName("city")
    @SerialName("city")
    val city: List<String>? = null,

    @SerializedName("damage_type")
    @SerialName("damage_type")
    val damageType: String? = null,

    @SerializedName("group_attack")
    @SerialName("group_attack")
    val groupAttack: Boolean = false,

    @SerializedName("group_healing")
    @SerialName("group_healing")
    val groupHealing: Boolean = false,

    @SerializedName("group_support")
    @SerialName("group_support")
    val groupSupport: Boolean = false,

    @SerializedName("type_instant")
    @SerialName("type_instant")
    val typeInstant: Boolean = false,

    @SerializedName("type_rune")
    @SerialName("type_rune")
    val typeRune: Boolean = false,

    @SerializedName("premium_only")
    @SerialName("premium_only")
    val premiumOnly: Boolean = false,

    @SerializedName("soul_points")
    @SerialName("soul_points")
    val soulPoints: Int = 0,

    @SerializedName("amount")
    @SerialName("amount")
    val amount: Int = 0,

    @SerializedName("cooldown_alone")
    @SerialName("cooldown_alone")
    val cooldownAlone: Int = 0,

    @SerializedName("cooldown_group")
    @SerialName("cooldown_group")
    val cooldownGroup: Int = 0,

    @SerializedName("level")
    @SerialName("level")
    val level: Int = 0,

    @SerializedName("mana")
    @SerialName("mana")
    val mana: Int = 0,

    @SerializedName("price")
    @SerialName("price")
    val price: Int = 0
)

@Keep
@Serializable
data class RuneInformationDataDto(
    @SerializedName("vocation")
    @SerialName("vocation")
    val vocation: List<String>? = null,

    @SerializedName("group_attack")
    @SerialName("group_attack")
    val groupAttack: Boolean = false,

    @SerializedName("group_healing")
    @SerialName("group_healing")
    val groupHealing: Boolean = false,

    @SerializedName("group_support")
    @SerialName("group_support")
    val groupSupport: Boolean = false,

    @SerializedName("damage_type")
    @SerialName("damage_type")
    val damageType: String? = null,

    @SerializedName("level")
    @SerialName("level")
    val level: Int = 0,

    @SerializedName("magic_level")
    @SerialName("magic_level")
    val magicLevel: Int = 0
)
