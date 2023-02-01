package com.example.TibiaTools.APISERVER.models.GuildInformation.GuildName.members;

import androidx.annotation.Keep;

@Keep
public class MembersGuild {
    String name, title, rank, vocation, level, joined, status;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getRank() {
        return rank;
    }

    public String getVocation() {
        return vocation;
    }

    public String getLevel() {
        return level;
    }

    public String getJoined() {
        return joined;
    }

    public String getStatus() {
        return status;
    }
}
