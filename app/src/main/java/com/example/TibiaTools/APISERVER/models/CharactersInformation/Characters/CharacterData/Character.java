package com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Guild.Guild;
import com.example.TibiaTools.APISERVER.models.CharactersInformation.Characters.CharacterData.Houses.House;

import java.util.ArrayList;

@Keep
public class Character {
    private String name;
    private String sex;
    private String title;
    private int unlocked_titles;
    private String vocation;
    private int level;
    private int achievement_points;
    private String world;
    private String residence;
    Guild guild;
    private String last_login;
    private String account_status;
    private String comment;
    ArrayList<House> houses = new ArrayList<House>();
    private String married_to;

    public ArrayList<House> getHouses() {
        return houses;
    }

    public void setHouses(ArrayList<House> houses) {
        this.houses = houses;
    }

    public String getMarried_to() {
        return married_to;
    }

    public void setMarried_to(String married_to) {
        this.married_to = married_to;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUnlocked_titles(int unlocked_titles) {
        this.unlocked_titles = unlocked_titles;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAchievement_points(int achievement_points) {
        this.achievement_points = achievement_points;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }


    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getTitle() {
        return title;
    }

    public int getUnlocked_titles() {
        return unlocked_titles;
    }

    public String getVocation() {
        return vocation;
    }

    public int getLevel() {
        return level;
    }

    public float getAchievement_points() {
        return achievement_points;
    }

    public String getWorld() {
        return world;
    }

    public String getResidence() {
        return residence;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getLast_login() {
        return last_login;
    }

    public String getAccount_status() {
        return account_status;
    }
}
