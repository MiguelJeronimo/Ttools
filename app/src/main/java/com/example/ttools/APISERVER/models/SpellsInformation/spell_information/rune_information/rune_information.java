package com.example.ttools.APISERVER.models.SpellsInformation.spell_information.rune_information;

import com.example.ttools.APISERVER.models.SpellsInformation.spell_information.Spell_Information;

import java.util.ArrayList;

public class rune_information extends Spell_Information {
    //herencia
    public rune_information(ArrayList<String> vocation){
        super(vocation);
    }

    @Override
    public ArrayList<String> getVocation() {
        return super.getVocation();
    }
}
