package com.RainbowSea.mhl.javaBean;


import java.util.Date;

/**
 * bill数据表的javaBean的实例(ORM映射)
 */
public class Bill {
    private int id;            // 主键
    private String billId;     // 订单号 UNIQUE唯一约束
    private String billDate;   // 订单日期，因为在bill数据表中是 DATETIME(日期时间类型)而Java当中的DATE不兼容(改用了字符串)
    private int diningId;      // 餐桌编号
    private int menuId;        // 菜的编号
    private String menuName;   // 菜名
    private int nums;          // 菜的数量
    private double money;      // 当前菜的总价(数量 * 单价)
    private String state;      // 该订单的状态  (未结账,现金，支付宝，微信)
    private double menuPrice;  // 当前菜的单价(多表查询,虽然对应数据表中没有该字段信息，但是可以javaBean实例存储多表查询结果)
    // 注意数据库的命名格式 使用别名


    public Bill() {
        // 无参构造器，必须定义用于apache-dbutils底层的调用
    }

    public Bill(int id, String billId, String billDate, int diningId, int menuId, String menuName, int nums,
                double money, String state, double menuPrice) {
        this.id = id;
        this.billId = billId;
        this.billDate = billDate;
        this.diningId = diningId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.nums = nums;
        this.money = money;
        this.state = state;
        this.menuPrice = menuPrice;
    }

    // set/get 必须定义用于 apache-dbutils底层反射赋值，取值的操作

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public int getDiningId() {
        return diningId;
    }

    public void setDiningId(int diningId) {
        this.diningId = diningId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }

    @Override
    public String toString() {
        return id +
                "\t\t" + billDate +
                "\t\t\t" + diningId +
                "\t\t\t" + menuId +
                "\t\t\t" + menuName +
                "\t\t" + nums +
                "\t\t\t" + menuPrice +
                "\t\t" + money +
                "\t\t" + state;
    }
}
