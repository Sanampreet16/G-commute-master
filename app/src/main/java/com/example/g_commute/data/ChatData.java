package com.example.g_commute.data;

import com.google.firebase.Timestamp;


public class ChatData {

    private int id;
    private int group;
    private String message;
    private Timestamp time;


    public ChatData() {
    }

    public ChatData(int id, int group, String message, Timestamp time) {
        this.id = id;
        this.group = group;
        this.message = message;
        this.time = time;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
