package com.example.ttools.APISERVER.models.GuildInformation.GuildName;

import com.example.ttools.APISERVER.models.GuildInformation.GuildName.GuildHalls.GuildHalls;
import com.example.ttools.APISERVER.models.GuildInformation.GuildName.members.MembersGuild;

import java.util.ArrayList;

public class GuildName {
    private String name;
    private String world;
    private String logo_url;
    private String description;
    private Boolean active;
    private Boolean in_war;
    private int players_online;
    private int members_total;
    private String founded;
    private ArrayList<MembersGuild> members;
    private ArrayList<GuildHalls> guildhalls;

    public String getName() {
        return name;
    }

    public String getWorld() {
        return world;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getIn_war() {
        return in_war;
    }

    public int getPlayers_online() {
        return players_online;
    }

    public int getMembers_total() {
        return members_total;
    }

    public ArrayList<MembersGuild> getMembers() {
        return members;
    }

    public ArrayList<GuildHalls> getGuildhalls() {
        return guildhalls;
    }

    public String getFounded() { return founded; }
}
