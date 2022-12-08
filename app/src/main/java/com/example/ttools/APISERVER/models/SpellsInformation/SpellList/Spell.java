package com.example.ttools.APISERVER.models.SpellsInformation.SpellList;

public class Spell extends SpellsList{
    String image_url;
    String description;

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
