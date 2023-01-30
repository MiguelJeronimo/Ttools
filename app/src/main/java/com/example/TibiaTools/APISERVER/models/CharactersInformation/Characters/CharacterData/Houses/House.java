package com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Houses;

import androidx.annotation.Keep;

@Keep
public class House {
    private String name;
    private String town;
    private String paid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
