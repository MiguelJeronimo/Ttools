package com.example.TibiaTools.APISERVER.models;

import com.example.TibiaTools.APISERVER.models.News.News;

import java.util.ArrayList;

public class ApiNewsTicker {
    ArrayList<News> news = new ArrayList<>();

    public ArrayList<News> getNews() {
        return news;
    }
}
