package com.example.TibiaTools.domain.model

/**
 * Modelo de Dominio limpio para la información de personajes.
 */
data class CharacterInformation(
    val characterData: CharactersData? = null,
    val information: InformationInfo? = null
) {
    val character: CharactersData? get() = characterData
}

data class CharactersData(
    val character: CharacterDetail? = null,
    val otherCharacters: List<OtherCharacter> = emptyList(),
    val deaths: List<Death> = emptyList(),
    val achievements: List<Achievement> = emptyList(),
    val accountInformation: AccountInformation? = null
)

data class CharacterDetail(
    val name: String = "",
    val sex: String = "",
    val title: String = "",
    val unlockedTitles: Int = 0,
    val vocation: String = "",
    val level: Int = 0,
    val achievementPoints: Int = 0,
    val world: String = "",
    val residence: String = "",
    val guild: CharacterGuild? = null,
    val lastLogin: String = "",
    val accountStatus: String = "",
    val comment: String = "",
    val houses: List<CharacterHouse> = emptyList(),
    val marriedTo: String = ""
)

data class CharacterGuild(
    val name: String = "",
    val rank: String = ""
)

data class CharacterHouse(
    val name: String = "",
    val town: String = "",
    val paid: String = "",
    val houseId: Int = 0
)

data class AccountInformation(
    val loyaltyTitle: String = "",
    val created: String = ""
)

data class Achievement(
    val name: String = "",
    val grade: String = "",
    val secret: Boolean = false
)

data class Death(
    val time: String = "",
    val reason: String = "",
    val level: Int = 0
)

data class OtherCharacter(
    val name: String = "",
    val world: String = "",
    val status: String = "",
    val isDeleted: Boolean = false,
    val isMain: Boolean = false,
    val isTraded: Boolean = false
)

data class InformationInfo(
    val status: StatusInfo? = null
)

data class StatusInfo(
    val httpCode: Int = 0,
    val message: String = ""
)
