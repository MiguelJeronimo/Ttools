package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.Spell;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.Spells;

@Keep
public class ApiSpellsInformation {
    Spell spell;

    public Spell getSpells() {
        return spell;
    }
}
