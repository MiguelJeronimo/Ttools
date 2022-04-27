package com.example.ttools.Operaciones.information;

import java.util.ArrayList;

public class Characters {
    Character character;
    ArrayList<Object> other_characters = new ArrayList<Object>();

    public void setCharacter(Character characterObject) {
        character = characterObject;
    }

    public void setOther_characters(ArrayList<Object> other_characters) {
        this.other_characters = other_characters;
    }

    public Character getCharacter() {
        return character;
    }

    public ArrayList<Object> getOther_characters() {
        return other_characters;
    }
}
