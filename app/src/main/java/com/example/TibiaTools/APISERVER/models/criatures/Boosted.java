package com.example.TibiaTools.APISERVER.models.criatures;

public class Boosted {
    String name, race, image_url;

    public Boosted(String name, String image_url){
        this.name=name;
        this.image_url=image_url;
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
}
