package com.example.ttools.APISERVER.models.SpellsInformation;

import com.example.ttools.APISERVER.models.SpellsInformation.SpellList.Spell;
import com.example.ttools.APISERVER.models.SpellsInformation.SpellList.SpellsList;

import java.util.ArrayList;

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
