package com.example.TibiaTools.recyclerview;

public class ItemsRecyclerViewHouses {
    private String houseName;
    private String houseSize;
    private String houseRent;
    private String houseRented;
    private String houseId;

    public ItemsRecyclerViewHouses(
            String houseName,
            String houseSize,
            String houseRent,
            String houseRented,
            String houseId){
        this.houseName = houseName;
        this.houseSize = houseSize;
        this.houseRent = houseRent;
        this.houseRented = houseRented;
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(String houseSize) {
        this.houseSize = houseSize;
    }

    public String getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(String houseRent) {
        this.houseRent = houseRent;
    }

    public String getHouseRented() {
        return houseRented;
    }

    public void setHouseRented(String houseRented) {
        this.houseRented = houseRented;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
}
