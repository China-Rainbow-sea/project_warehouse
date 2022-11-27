package com.RainbowSea.mhl.utils;

import java.sql.Date;

public class Actor {
    private Integer id;
    private String name;
    private String sex;
    private Date birth;
    private String phone;

    public Actor() {
        // 必须要有无参构造器，用于 apache-dbutils中底层的调用，
    }

    public void setId(Integer id) {
        // 必须要有set和get方法用于 dbtuils底层反射的赋值，取值操作
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                '}';
    }
}
