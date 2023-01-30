package com.example.TibiaTools.APISERVER.models.News;

import androidx.annotation.Keep;

@Keep
public class News {
    String id, date, news, category, type, url;

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getNews() {
        return news;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
