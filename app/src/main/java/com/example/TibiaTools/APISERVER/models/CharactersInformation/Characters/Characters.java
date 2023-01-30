package com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.AccountInformation;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Achievements;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Character;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Deaths;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.OtherCharacters;

import java.util.ArrayList;

@Keep
public class Characters {
    Character character;
    ArrayList<OtherCharacters> other_characters = new ArrayList<OtherCharacters>();
    ArrayList<Deaths> deaths = new ArrayList<Deaths>();
    ArrayList<Achievements> achievements = new ArrayList<Achievements>();
    AccountInformation account_information;

    public AccountInformation getAccount_information() {
        return account_information;
    }

    public void setAccount_information(AccountInformation account_information) {
        this.account_information = account_information;
    }

    public ArrayList<Deaths> getDeaths() {
        return deaths;
    }

    public ArrayList<Achievements> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<Achievements> achievements) {
        this.achievements = achievements;
    }

    public void setDeaths(ArrayList<Deaths> deaths) {
        this.deaths = deaths;
    }

    public void setCharacter(Character characterObject) {
        character = characterObject;
    }

    public void setOther_characters(ArrayList<OtherCharacters> other_characters) {
        this.other_characters = other_characters;
    }

    public Character getCharacter() {
        return character;
    }

    public ArrayList<OtherCharacters> getOther_characters() {
        return other_characters;
    }
}
