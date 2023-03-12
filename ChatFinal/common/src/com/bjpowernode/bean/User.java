package com.bjpowernode.bean;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String picture;

    public User() {
    }

    public User(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return name;
    }
}
