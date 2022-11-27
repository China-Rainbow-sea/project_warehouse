package com.RainbowSea.mhl.javaBean;


/**
 *mhl 数据库 dining数据表的javaBean实例(ORM)映射
 */
public class Dining {
    private int id;             // 餐桌编号
    private String state;       // 餐桌状态(空，就餐中，预定中)
    private String orderName;   // 该预定餐桌的用户名，注意数据库中的命名格式不同：order_name需要使用别名
    private String orderTel;    // 该预定餐桌的电话

    public Dining() {
        // 无参构造器，必须要有，用于apache-dbutils底层的调用
    }

    public Dining(int id, String state, String orderName, String orderTel) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    // set/get 必须要有，用于apache-dbutils底层反射的赋值，取值
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + state;
    }
}
