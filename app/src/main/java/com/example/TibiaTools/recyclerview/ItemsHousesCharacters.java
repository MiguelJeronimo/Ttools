package com.example.TibiaTools.recyclerview;

public class ItemsHousesCharacters {
    private String name;
    private String town;
    private String paid;
    private int houseid;

    public ItemsHousesCharacters(String name, String town, String paid, int houseid){
        this.name = name;
        this.town = town;
        this.paid = paid;
        this.houseid = houseid;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }
}
