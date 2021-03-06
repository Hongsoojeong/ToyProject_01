package com.example.Data;

//데이터 리스트

public class ItemData {

    private String number="";
    private String date="";
    private String when="";
    private String image="";
    private String content="";
    private String aSwitch="";

    public ItemData() {
    }

    public ItemData(String number, String date, String when, String content, String image,String aSwitch) {
        this.number=number;
        this.date = date;
        this.when = when;
        this.image=image;
        this.content=content;
        this.aSwitch=aSwitch;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String name) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String description) {
        this.date = date;
    }

    public String getWhen() {
        return when;
    }
    public void setWhen(String when) {
        this.when = when;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


}
