package com.bjpowernode.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    //用户名
    private String name;
    //消息类型
    private MessageType type;
    //消息内容
    private String msg;
    //在线用户
    private ArrayList<User> onlineUsers;

    public String getPicture() {
        return picture;
    }

    private String picture;

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(ArrayList<User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }


    public void setPicture(String picture) {
        this.picture = picture;
    }


}
