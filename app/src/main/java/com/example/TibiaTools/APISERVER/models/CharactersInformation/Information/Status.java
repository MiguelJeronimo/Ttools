package com.example.TibiaTools.APISERVER.models.CharactersInformation.Information;

public class Status {

    private int http_code;
    private String message;


    public int getHttpCode() {
        return http_code;
    }

    public void setHttpCode(int http_code) {
        this.http_code = http_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
