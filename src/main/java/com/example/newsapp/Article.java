package com.example.newsapp;

public class Article {

    private  String title;
    private  String description;
    private  String urlToImage;
    private  String url;
    private  String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(String urlToImage, String title, String description, String url, String content) {
        this.urlToImage = urlToImage;
        this.title = title;
        this.description = description;
        this.url = url;
        this.content = content;
    }
}
