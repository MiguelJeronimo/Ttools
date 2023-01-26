package com.example.TibiaTools.recyclerview;

public class ItemsRecyclerViewSpells {
    private String nombre;
    private String formula;
    private String mana;
    private String precio;
    private String tipo;
    private String grupo;
    private String spellid;
    private String premium;
    private String level;

    public ItemsRecyclerViewSpells(
            String nombre,
            String formula,
            String mana,
            String precio,
            String tipo,
            String grupo,
            String spellid,
            String premium,
            String level){
        this.nombre=nombre;
        this.formula=formula;
        this.mana=mana;
        this.precio=precio;
        this.tipo=tipo;
        this.grupo=grupo;
        this.spellid=spellid;
        this.premium=premium;
        this.level=level;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSpellId() {
        return spellid;
    }

    public void setSpellId(String spellid) {
        this.spellid = spellid;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
