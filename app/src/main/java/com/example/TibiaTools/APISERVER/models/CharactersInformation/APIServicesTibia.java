package com.example.TibiaTools.APISERVER.models.CharactersInformation;
import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.Characters;

@Keep
public class APIServicesTibia {

        Characters characters;

        public void setCharacters( Characters characters ) {
            this.characters = characters;
        }

    public Characters getCharacters() {
            return characters;
    }

}

