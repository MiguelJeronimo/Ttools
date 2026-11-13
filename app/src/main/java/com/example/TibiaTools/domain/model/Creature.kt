package com.example.TibiaTools.domain.model

data class CreaturesResponse(
    val creatures: CreaturesData? = null,
    val creaturesList: List<CreatureListItem>? = null
)

data class CreatureInformationResponse(
    val creature: CreatureDetail? = null
)

data class BoostableBossesResponse(
    val boostableBosses: BoostableBossesData? = null
) {
    val boostable_bosses: BoostableBossesData? get() = boostableBosses
}

data class CreaturesData(
    val boosted: BoostedData? = null,
    val creatureList: List<CreatureListItem> = emptyList()
) {
    val creature_list: List<CreatureListItem> get() = creatureList
}

data class CreatureListItem(
    val name: String = "",
    val race: String = "",
    val imageUrl: String = ""
) {
    val image_url: String get() = imageUrl
}

data class CreatureDetail(
    val name: String = "",
    val race: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val behaviour: String = "",
    val hitpoints: String = "",
    val experiencePoints: String = "",
    val lootList: List<String> = emptyList(),
    val immune: List<String> = emptyList(),
    val strong: List<String> = emptyList(),
    val weakness: List<String> = emptyList()
) {
    val image_url: String get() = imageUrl
    val experience_points: String get() = experiencePoints
    val loot_list: List<String> get() = lootList
}

data class BoostableBossesData(
    val boosted: BoostedData? = null
)

data class BoostedData(
    val name: String = "",
    val race: String = "",
    val imageUrl: String = ""
) {
    val image_url: String get() = imageUrl
}
