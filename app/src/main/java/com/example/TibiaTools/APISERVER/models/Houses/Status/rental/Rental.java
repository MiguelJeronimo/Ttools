package com.example.TibiaTools.APISERVER.models.Houses.Status.rental;

import androidx.annotation.Keep;

@Keep
public class Rental {
    String owner,owner_sex,paid_until,moving_date,transfer_receiver;
    int transfer_price;
    Boolean transfer_accept;

    public String getTransfer_receiver() {
        return transfer_receiver;
    }

    public String getOwner_sex() {
        return owner_sex;
    }

    public String getOwner() {
        return owner;
    }

    public String getMoving_date() {
        return moving_date;
    }

    public int getTransfer_price() {
        return transfer_price;
    }

    public String getPaid_until() {
        return paid_until;
    }

    public Boolean getTransfer_accept() {
        return transfer_accept;
    }
}
