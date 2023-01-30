package com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData;

import androidx.annotation.Keep;

@Keep
public class Deaths {
   String reason, time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
