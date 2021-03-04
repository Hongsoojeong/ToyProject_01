package com.example.Data;

//데이터 리스트

import com.google.gson.Gson;

public class ItemData {

    private String date="";
    private String title="";
    private String number="";
    private String content = "";

    public ItemData() {
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String name) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String image) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String description) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public ItemData(String name, String date, String title, String content) {
        this.number = name;
        this.date = date;
        this.title = title;
        this.content= content;
    }

}
