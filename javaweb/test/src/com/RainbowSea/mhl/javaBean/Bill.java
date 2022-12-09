package com.RainbowSea.mhl.javaBean;

/**
 * 对应 bill 账单数据表
 */
public class Bill {
    private int id;           // 账单ID,主键
    private String billId;    // 账单号
    private String billDate;  // 账单生成时间
    private int diningId;     // 餐桌位
    private int menuId;       // 菜编号
    private String menuName;  // 菜名
    private int menuNums;     // 菜的个数
    private double menuMoney; // 当前菜的总价(数量*单价)
    private String billState; // 当前账单的状态(未结账,微信/支付宝/银行卡/现金)
    private double menuPrice; // 当前菜的单价

    public Bill() {
        //必须 创建无参构造器,用于apche-dbutils底层的使用
    }


    public Bill(int id, String billId, String billDate, int diningId, int menuId, String menuName, int menuNums, double menuMoney, String billState, double menuPrice) {
        this.id = id;
        this.billId = billId;
        this.billDate = billDate;
        this.diningId = diningId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuNums = menuNums;
        this.menuMoney = menuMoney;
        this.billState = billState;
        this.menuPrice = menuPrice;
    }

    // 必须创建ser/get 用于apche-dutils底层的反射赋值，取值
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

    public int getMenuNums() {
        return menuNums;
    }

    public void setMenuNums(int menuNums) {
        this.menuNums = menuNums;
    }

    public double getMenuMoney() {
        return menuMoney;
    }

    public void setMenuMoney(double menuMoney) {
        this.menuMoney = menuMoney;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
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
                "\t\t" + menuNums +
                "\t\t\t" + menuPrice +
                "\t\t" + menuMoney +
                "\t\t" + billState;
    }

}
