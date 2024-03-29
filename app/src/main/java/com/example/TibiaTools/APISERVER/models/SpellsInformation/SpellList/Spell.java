package com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.SpellsInformation.spell_information.Spell_Information;
import com.example.TibiaTools.APISERVER.models.SpellsInformation.spell_information.rune_information.rune_information;
@Keep
public class Spell extends SpellsList{
    String image_url;
    String description;
    Spell_Information spell_information;
    rune_information rune_information;

    public rune_information getRune_information() {
        return rune_information;
    }

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
