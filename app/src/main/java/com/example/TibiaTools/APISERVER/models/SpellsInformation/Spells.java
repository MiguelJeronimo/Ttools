package com.example.TibiaTools.APISERVER.models.SpellsInformation;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.Spell;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.SpellsList;

import java.util.ArrayList;

@Keep
public class Spells {
    ArrayList<SpellsList> spell_list;
    public ArrayList<SpellsList> getSpells_list() {
        return spell_list;
    }
    //para la info de las spells
    Spell spell;
    public Spell getSpell() {
        return spell;
    }
}
