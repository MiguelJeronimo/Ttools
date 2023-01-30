package com.example.TibiaTools.APISERVER.models.GuildInformation;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.GuildInformation.guilds.Guilds;
@Keep
public class ApiGuilds {
    Guilds guilds;

    public Guilds getGuilds() {
        return guilds;
    }

}
