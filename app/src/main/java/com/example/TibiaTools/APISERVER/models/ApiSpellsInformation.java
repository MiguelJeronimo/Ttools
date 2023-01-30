package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.SpellsInformation.Spells;

@Keep
public class ApiSpellsInformation {
    Spells spells;

    public Spells getSpells() {
        return spells;
    }
}
