package com.example.TibiaTools.APISERVER.models.Worlds;

import androidx.annotation.Keep;

@Keep
public class RegularWorlds implements Comparable<RegularWorlds>{
    private String name;
    private String status;
    private int players_online;
    private String location;
    private String pvp_type;
    private boolean premium_only;
    private String transfer_type;
    private boolean battleye_protected;
    private String battleye_date;
    private String game_world_type;
    private String tournament_world_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPlayers_online() {
        return players_online;
    }

    public void setPlayers_online(int players_online) {
        this.players_online = players_online;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPvp_type() {
        return pvp_type;
    }

    public void setPvp_type(String pvp_type) {
        this.pvp_type = pvp_type;
    }

    public boolean getPremium_only() {
        return premium_only;
    }

    public void setPremium_only(boolean premium_only) {
        this.premium_only = premium_only;
    }

    public String getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(String transfer_type) {
        this.transfer_type = transfer_type;
    }

    public boolean getBattleye_protected() {
        return battleye_protected;
    }

    public void setBattleye_protected(boolean battleye_protected) {
        this.battleye_protected = battleye_protected;
    }

    public String getBattleye_date() {
        return battleye_date;
    }

    public void setBattleye_date(String battleye_date) {
        this.battleye_date = battleye_date;
    }

    public String getGame_world_type() {
        return game_world_type;
    }

    public void setGame_world_type(String game_world_type) {
        this.game_world_type = game_world_type;
    }

    public String getTournament_world_type() {
        return tournament_world_type;
    }

    public void setTournament_world_type(String tournament_world_type) {
        this.tournament_world_type = tournament_world_type;
    }

    @Override
    public int compareTo(RegularWorlds o) {
        return Integer.compare(o.getPlayers_online(), this.getPlayers_online());
    }
}
