package com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData;

import androidx.annotation.Keep;

@Keep
public class AccountInformation {
    private String created, loyalty_title;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLoyalty_title() {
        return loyalty_title;
    }

    public void setLoyalty_title(String loyalty_title) {
        this.loyalty_title = loyalty_title;
    }
}
