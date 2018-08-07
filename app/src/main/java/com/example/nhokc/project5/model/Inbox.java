package com.example.nhokc.project5.model;

import java.io.Serializable;

public class Inbox implements Serializable{
    String name;
    String content;
    String date;

    public Inbox(String name, String content, String date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
