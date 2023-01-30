package com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData;

import androidx.annotation.Keep;

@Keep
public class Achievements {
    private String name;
    private String grade;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    private boolean secret;
}
