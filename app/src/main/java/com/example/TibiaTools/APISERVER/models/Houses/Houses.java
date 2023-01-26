package com.example.TibiaTools.APISERVER.models.Houses;

import com.example.TibiaTools.APISERVER.models.Houses.house_list.GuildhallList;
import com.example.TibiaTools.APISERVER.models.Houses.house_list.HouseList;

import java.util.ArrayList;

public class Houses {
    String world, town;
    ArrayList<HouseList> house_list;
    ArrayList<GuildhallList> guildhall_list;

    public ArrayList<GuildhallList> getGuildhall_list() {
        return guildhall_list;
    }

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
