package com.example.TibiaTools.presentation.routes


enum class Options(string: String) {
    HOME("Home"),
    PLAYERS("Players"),
    RANKS("Ranks"),
    LIBRARY("Library"),
    TOOLS("Tools")
}



enum class OptionsTab(override val nameDestination: String) : TabDestination {
    CREATURES("Creatures"),
    SPELLS("Spells"),
}



enum class FilterOptionSpells(val type: String){
    ALL("All"),
    ATTACK("Attack"),
    HEALING("Healing"),
    INSTANT("Instant"),
    RUNE("Rune")
}