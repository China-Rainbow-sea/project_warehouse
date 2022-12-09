package com.RainbowSea.mhl.dao;


import com.RainbowSea.mhl.javaBean.Dining;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 继承JDBCUtilsByDruid<T> 父类，通过调用其父类中的方法
 * 对 dining数据表进行一个“增删改查 ”操作
 */
public class DiningDAO extends JDBCUtilsByDruid<Dining> {
    // 其他子类独有的方法可以自行添加

}
