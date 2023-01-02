package com.example.ttools.APISERVER.models.Houses;

import com.example.ttools.APISERVER.models.Houses.house_list.HouseList;

import java.util.ArrayList;

public class Houses {
    String world, town;
    ArrayList<HouseList> house_list;

    public ArrayList<HouseList> getHouse_list() {
        return house_list;
    }

    public String getTown() {
        return town;
    }

    public String getWorld() {
        return world;
    }
}
