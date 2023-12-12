package com.example.TibiaTools.APISERVER.models.GuildInformation;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.Guild;
@Keep
public class ApiGuildsName {
    Guild guild;

    public Guild getGuilds() {
        return guild;
    }
}
