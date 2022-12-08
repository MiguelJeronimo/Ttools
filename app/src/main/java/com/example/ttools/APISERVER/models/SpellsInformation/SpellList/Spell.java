package com.example.ttools.APISERVER.models.SpellsInformation.SpellList;

import com.example.ttools.APISERVER.models.SpellsInformation.spell_information.Spell_Information;

public class Spell extends SpellsList{
    String image_url;
    String description;
    Spell_Information spell_information;

    public Spell_Information getSpell_information() {
        return spell_information;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }
    //aplicando herencia
    public Spell(){
        super();
    }
    public Spell(String name, String spell_id){
        super(name,spell_id);
    }

    public String getName() {
        return super.getName();
    }

    public String getSpell_id() {
        return super.getSpell_id();
    }
}
