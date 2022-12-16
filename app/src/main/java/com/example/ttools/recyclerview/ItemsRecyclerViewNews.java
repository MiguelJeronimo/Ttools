package com.example.ttools.recyclerview;

public class ItemsRecyclerViewNews {
    private String id;
    private String date;
    private String news;
    private String category;
    private String type;
    private String url;

    public ItemsRecyclerViewNews(
            String id,
            String date,
            String news,
            String category,
            String type,
            String url
    ){
        this.id=id;
        this.date=date;
        this.news=news;
        this.category=category;
        this.type=type;
        this.url=url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
