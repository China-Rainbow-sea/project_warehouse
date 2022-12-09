package com.RainbowSea.mhl.dao;


import com.RainbowSea.mhl.javaBean.Bill;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 继承JDBCUtilsByDruid<T> 父类，通过调用其父类中的方法
 * 对 bill 账单数据表进行一个“增删改查 ”操作
 */
public class BillDAO extends JDBCUtilsByDruid<Bill> {
    //其中子类独有的方法也可以自行添加上去。
}
