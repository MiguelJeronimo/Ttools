package com.example.TibiaTools.APISERVER.models.Houses.house_list;

public class HouseList {
    String name;
    int house_id, size, rent;
    boolean rented;

    public HouseList(String name, int house_id, int size,int rent, boolean rented){
        this.name = name;
        this.house_id = house_id;
        this.size = size;
        this.rent = rent;
        this.rented = rented;
    }

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
