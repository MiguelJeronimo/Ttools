package com.example.ttools.APISERVER.models.CriatureInformation;

import java.util.ArrayList;

public class Creature {
    String name, race, image_url, description,behaviour,hitpoints,experience_points;
    ArrayList<String> loot_list = new ArrayList<String>();

    public String getExperience_points() {
        return experience_points;
    }

    public void setExperience_points(String experience_points) {
        this.experience_points = experience_points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(String hitpoints) {
        this.hitpoints = hitpoints;
    }

    public ArrayList<String> getLoot_list() {
        return loot_list;
    }

    public void setLoot_list(ArrayList<String> loot_list) {
        this.loot_list = loot_list;
    }
}
