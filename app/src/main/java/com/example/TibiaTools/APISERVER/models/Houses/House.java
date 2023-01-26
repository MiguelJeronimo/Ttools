package com.example.TibiaTools.APISERVER.models.Houses;

import com.example.TibiaTools.APISERVER.models.Houses.Status.Status;

public class House {
    String world, town, img;
    String name, type;
    int beds, size, rent;
    Status status;

    public Status getStatus() {
        return status;
    }

    public int getBeds() {
        return beds;
    }

    public String getType() {
        return type;
    }

    public String getWorld() {
        return world;
    }

    public String getTown() {
        return town;
    }

    public String getName() {
        return name;
    }

    public int getRent() {
        return rent;
    }

    public int getSize() {
        return size;
    }

    public String getImg() {
        return img;
    }
}
