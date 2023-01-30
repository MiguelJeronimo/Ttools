package com.example.TibiaTools.APISERVER.models.SpellsInformation.SpellList;

import androidx.annotation.Keep;

@Keep
public class SpellsList {
    private String name;
    private String spell_id;
    private String formula;
    private String level;
    private String mana;
    private String price;
    private boolean group_attack;
    private boolean group_healing;
    private boolean group_support;
    private boolean type_instant;
    private boolean type_rune;
    private boolean premium_only;

    public SpellsList() {}

    public SpellsList(
            String formula,
            String level,
            String mana,
            String price,
            boolean group_attack,
            boolean group_healing,
            boolean group_support,
            boolean type_instant,
            boolean type_rune,
            boolean premium_only) {

        this.formula=formula;
        this.level=level;
        this.mana=mana;
        this.price=price;
        this.group_attack=group_attack;
        this.group_healing=group_healing;
        this.group_support=group_support;
        this.type_instant=type_instant;
        this.type_rune=type_rune;
        this.premium_only=premium_only;
    }

    public SpellsList(String name, String spell_id) {
        this.name=name;
        this.spell_id=spell_id;
    }

    public String getName() {
        return name;
    }

    public String getSpell_id() {
        return spell_id;
    }

    public String getFormula() {
        return formula;
    }

    public String getLevel() {
        return level;
    }

    public String getMana() {
        return mana;
    }

    public String getPrice() {
        return price;
    }

    public boolean isGroup_attack() {
        return group_attack;
    }

    public boolean isGroup_healing() {
        return group_healing;
    }

    public boolean isGroup_support() {
        return group_support;
    }

    public boolean isType_instant() {
        return type_instant;
    }

    public boolean isType_rune() {
        return type_rune;
    }

    public boolean isPremium_only() {
        return premium_only;
    }
}
