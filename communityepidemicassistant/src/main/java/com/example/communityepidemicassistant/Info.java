package com.example.communityepidemicassistant;

public class Info {
    private String date;
    private String name;
    private String idCard;
    private String phone;
    private String address;
    private String ifFever;
    private String tem;
    private String ifTouch;
    private String touchName;
    private String touchPhone;
    private String touchDate;

    public Info(String date, String name, String idCard, String phone, String address, String ifFever,
                String tem, String ifTouch, String touchName, String touchPhone, String touchDate) {
        this.date = date;
        this.name = name;
        this.idCard = idCard;
        this.phone = phone;
        this.address = address;
        this.ifFever = ifFever;
        this.tem = tem;
        this.ifTouch = ifTouch;
        this.touchName = touchName;
        this.touchPhone = touchPhone;
        this.touchDate = touchDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIfFever(String ifFever) {
        this.ifFever = ifFever;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public void setIfTouch(String ifTouch) {
        this.ifTouch = ifTouch;
    }

    public void setTouchName(String touchName) {
        this.touchName = touchName;
    }

    public void setTouchPhone(String touchPhone) {
        this.touchPhone = touchPhone;
    }

    public void setTouchDate(String touchDate) {
        this.touchDate = touchDate;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getIfFever() {
        return ifFever;
    }

    public String getTem() {
        return tem;
    }

    public String getIfTouch() {
        return ifTouch;
    }

    public String getTouchName() {
        return touchName;
    }

    public String getTouchPhone() {
        return touchPhone;
    }

    public String getTouchDate() {
        return touchDate;
    }
}
