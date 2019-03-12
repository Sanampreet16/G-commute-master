package com.example.g_commute.data;

import com.google.firebase.Timestamp;

public class Post {

    private int creator;
    private String desc;
    private String title;
    private int groupId;
    private int postId;
    private Timestamp time;


    public Post() {
    }


    public Post(int creator, String desc, int groupId, int postId, String title, Timestamp time) {
        this.creator = creator;
        this.desc = desc;
        this.groupId = groupId;
        this.postId = postId;
        this.title = title;
        this.time = time;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
