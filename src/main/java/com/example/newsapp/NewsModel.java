package com.example.newsapp;

import java.util.ArrayList;

public class NewsModel {
    private  int totalResult;
    private String status;
    private ArrayList<Article> sources;

    public NewsModel(int totalResult, String status, ArrayList<Article> sources) {
        this.totalResult = totalResult;
        this.status = status;
        this.sources = sources;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Article> getArticles() {
        return sources;
    }

    public void setArticles(ArrayList<Article> sources) {
        this.sources = sources;
    }

}
