package com.example.TibiaTools.APISERVER.models.Houses.house_list;

import androidx.annotation.Keep;

@Keep
public class GuildhallList extends HouseList{

    public GuildhallList(String name, int house_id, int size, int rent, boolean rented) {
        super(name, house_id, size, rent, rented);
    }
}
