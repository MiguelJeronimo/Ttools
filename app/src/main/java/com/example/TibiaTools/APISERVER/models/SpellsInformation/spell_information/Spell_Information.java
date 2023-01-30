package com.example.TibiaTools.APISERVER.models.SpellsInformation.spell_information;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList.SpellsList;

import java.util.ArrayList;
@Keep
public class Spell_Information extends SpellsList {
    ArrayList<String> vocation,city;
    String damage_type;
    int soul_points,amount,cooldown_alone, cooldown_group;

    public Spell_Information(ArrayList<String> vocation){
        this.vocation=vocation;
    }

    public String getDamage_type() {
        return damage_type;
    }

    public int getSoul_points() {
        return soul_points;
    }

    public int getAmount() {
        return amount;
    }

    public int getCooldown_alone() {
        return cooldown_alone;
    }

    public int getCooldown_group() {
        return cooldown_group;
    }

    public ArrayList<String> getCity() {
        return city;
    }

    public ArrayList<String> getVocation() {
        return vocation;
    }

    //herencia
    public Spell_Information(){
        super();
    }
    public Spell_Information(
            String formula,
            String level,
            String mana,
            String price,
            boolean group_attack,
            boolean group_healing,
            boolean group_support,
            boolean type_instant,
            boolean type_rune,
            boolean premium_only){
        super(formula,level,mana,price,group_attack,group_healing,group_support,type_instant,type_rune,premium_only);
    }

    @Override
    public String getFormula() {
        return super.getFormula();
    }

    @Override
    public String getLevel() {
        return super.getLevel();
    }

    @Override
    public String getMana() {
        return super.getMana();
    }

    @Override
    public String getPrice() {
        return super.getPrice();
    }

    @Override
    public boolean isGroup_attack() {
        return super.isGroup_attack();
    }

    @Override
    public boolean isGroup_healing() {
        return super.isGroup_healing();
    }

    @Override
    public boolean isGroup_support() {
        return super.isGroup_support();
    }

    @Override
    public boolean isType_instant() {
        return super.isType_instant();
    }

    @Override
    public boolean isType_rune() {
        return super.isType_rune();
    }

    @Override
    public boolean isPremium_only() {
        return super.isPremium_only();
    }
}
