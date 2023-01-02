package com.example.ttools.APISERVER.models.Houses.house_list;

public class HouseList {
    String name;
    int house_id, size, rent;
    boolean rented;

    public boolean isRented(){
        return rented;
    }

    public String getName() {
        return name;
    }

    public int getHouse_id() {
        return house_id;
    }

    public int getRent() {
        return rent;
    }

    public int getSize() {
        return size;
    }

}
