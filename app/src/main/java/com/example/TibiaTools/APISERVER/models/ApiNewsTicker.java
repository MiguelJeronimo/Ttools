package com.example.TibiaTools.APISERVER.models;

import androidx.annotation.Keep;

import com.example.TibiaTools.APISERVER.models.News.News;

import java.util.ArrayList;

@Keep
public class ApiNewsTicker {
    ArrayList<News> news = new ArrayList<>();

    public ArrayList<News> getNews() {
        return news;
    }
}
