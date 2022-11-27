package com.RainbowSea.mhl.javaBean;

/**
 * menu数据表的javaBean实例(orm映射)
 */
public class Menu {
    private int id;       // 菜名编号
    private String name;  // 菜名
    private String type;  // 菜品的种类
    private Double price; // 菜的单价

    public Menu() {
        // 无参构造器，必须创建用于apache-dbutils底层的调用
    }

    public Menu(int id, String name, String type, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    // set/get 也必须创建用于 apache-dbutils底层反射的赋值，取值
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id +
                "\t\t\t" + name +
                "\t\t" + type +
                "\t\t" + price;
    }
}
