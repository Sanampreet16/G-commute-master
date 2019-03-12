package com.example.g_commute.data;

public class Group {


    private String user;
    private String name;

    public Group() {
    }

    public Group(String user, String name) {
        this.user = user;
        this.name = name;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
