package com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Guild;

import androidx.annotation.Keep;

@Keep
public class Guild {
    private String name;
    private String rank;

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }
}
