package com.RainbowSea.mhl.javaBean;

/**
 * 对应menu 菜谱数据表
 */
public class Menu {
    private int menuId;        // 菜的编号，主键
    private String menuName;   // 菜名
    private String menuType;   // 菜的种类
    private double menuPrice;  // 菜的单价


    public Menu() {
        // 定义无参构造器,用于apache-dbuitls的底层调用
    }

    public Menu(int menuId, String menuName, String menuType, double menuPrice) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuType = menuType;
        this.menuPrice = menuPrice;
    }

    // 定义 set/get 用于 apache-dbutils底层的反射赋值，取值操作


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

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }


    @Override
    public String toString() {
        return  menuId +
                "\t\t\t" + menuName +
                "\t\t" + menuType +
                "\t\t"+ menuPrice;
    }
}
