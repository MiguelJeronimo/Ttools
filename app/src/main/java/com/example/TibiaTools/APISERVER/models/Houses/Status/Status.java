package com.example.TibiaTools.APISERVER.models.Houses.Status;

import androidx.annotation.Keep;

@Keep
public class Status {
    Boolean is_auctioned,is_rented,is_moving,is_transfering;
    String original;

    public String getOriginal() {
        return original;
    }

    public Boolean isTransfering() {
        return is_transfering;
    }

    public Boolean isMoving() {
        return is_moving;
    }

    public Boolean isRented() {
        return is_rented;
    }

    public Boolean isAuctioned() {
        return is_auctioned;
    }
}
