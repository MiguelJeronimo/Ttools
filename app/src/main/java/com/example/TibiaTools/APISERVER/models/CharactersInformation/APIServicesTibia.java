package com.example.TibiaTools.APISERVER.models.CharactersInformation;
import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.Characters;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Information.Information;

@Keep
    public class APIServicesTibia {

    Characters character;
    Information information;

    public void setCharacters( Characters character ) {
            this.character = character;
        }

    public Characters getCharacters() {
            return character;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Information getInformation() {
        return information;
    }

    public Characters getCharacter() {
        return character;
    }
}

