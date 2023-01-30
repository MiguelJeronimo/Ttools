package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.Houses.House;

@Keep
public class ApiHousesInformation {
    House house;

    public House getHouse() {
        return house;
    }
}
