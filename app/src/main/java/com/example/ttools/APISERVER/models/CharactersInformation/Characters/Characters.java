package com.example.ttools.APISERVER.models.CharactersInformation.Characters;

import com.example.ttools.APISERVER.models.CharactersInformation.Characters.CharacterData.Character;
import com.example.ttools.APISERVER.models.CharactersInformation.Characters.CharacterData.Deaths;

import java.util.ArrayList;

public class Characters {
    Character character;
    ArrayList<Object> other_characters = new ArrayList<Object>();
    ArrayList<Deaths> deaths = new ArrayList<Deaths>();

    public ArrayList<Deaths> getDeaths() {
        return deaths;
    }

    public void setDeaths(ArrayList<Deaths> deaths) {
        this.deaths = deaths;
    }

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
