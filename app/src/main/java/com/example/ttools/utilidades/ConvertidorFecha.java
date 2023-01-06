package com.example.ttools.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ConvertidorFecha {
    String expiryDateString;
    Date date;

    public void setExpiryDateString(String expiryDateString) {
        this.expiryDateString = expiryDateString;
    }

    public String getExpiryDateString() {
        return expiryDateString;
    }
    public void convertirFecha(){
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        formatter.setTimeZone(TimeZone.getTimeZone("America/Tijuana"));
        try {
            date = formatter.parse(getExpiryDateString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public String getFechaConvertida(){
        return String.valueOf(date);
    }
}
