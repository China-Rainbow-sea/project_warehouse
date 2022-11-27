package com.RainbowSea.mhl.dao;

import com.RainbowSea.mhl.javaBean.Bill;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 继承JDBCUtilsByDruid<Bill>父类，调用父类中的方法，
 * 对 bill(账单)数据表进行增删改查操作
 */
public class BillDAO extends JDBCUtilsByDruid<Bill> {
    // 如有其他，业务需求，可以自行增加
}
