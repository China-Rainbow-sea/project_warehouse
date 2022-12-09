package com.RainbowSea.mhl.javaBean;

/**
 * 对应 dining 餐卓数据表
 */
public class Dining {
    private int diningId;         // 餐桌位的编号
    private String diningState;   // 餐桌的状态
    private String orderName;     // 预定该餐桌的用户名
    private String orderTel;      // 预定该餐桌的联系电话

    public Dining() {
        //必须 创建无参构造器,用于apche-dbutils底层的使用
    }

    public Dining(int diningId, String diningState, String orderName, String orderTel) {
        this.diningId = diningId;
        this.diningState = diningState;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    // 必须创建ser/get 用于apche-dutils底层的反射赋值，取值


    public int getDiningId() {
        return diningId;
    }

    public void setDiningId(int diningId) {
        this.diningId = diningId;
    }

    public String getDiningState() {
        return diningState;
    }

    public void setDiningState(String diningState) {
        this.diningState = diningState;
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
        return diningId +
                "\t\t\t" + diningState +
                "\t\t" + orderName +
                "\t\t" + orderTel;
    }
}
