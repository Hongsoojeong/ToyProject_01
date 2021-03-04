package com.example.Data;

//데이터 리스트

public class ItemData {

    private String number="";
    private String date="";
    private String when="";

    public ItemData() {
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String name) {
        this.number = number;
    }

    public String getWhen() {
        return when;
    }
    public void setWhen(String when) {
        this.when = when;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String description) {
        this.date = date;
    }


    public ItemData(String number, String date, String when) {
        this.number=number;
        this.date = date;
        this.when = when;
    }

}
