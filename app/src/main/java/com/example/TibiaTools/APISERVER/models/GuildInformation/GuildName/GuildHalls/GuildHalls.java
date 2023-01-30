package com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.GuildHalls;

import androidx.annotation.Keep;

@Keep
public class GuildHalls {
    String name, world, paid_until;

    public String getWorld() {
        return world;
    }

    public String getName() {
        return name;
    }

    public String getPaid_until() {
        return paid_until;
    }
}
