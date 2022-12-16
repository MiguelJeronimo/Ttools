package com.example.ttools.APISERVER.models;

import com.example.ttools.APISERVER.models.News.News;

import java.util.ArrayList;

public class ApiNewsTicker {
    ArrayList<News> news = new ArrayList<>();

    public ArrayList<News> getNews() {
        return news;
    }
}
