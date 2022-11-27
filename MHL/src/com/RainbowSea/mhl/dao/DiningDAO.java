package com.RainbowSea.mhl.dao;

import com.RainbowSea.mhl.javaBean.Dining;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 继承了 JDBCUtilsByDruid<Dining>父类，调用其中的方法对
 * dining(餐桌)数据表进行增删改查操作
 */
public class DiningDAO extends JDBCUtilsByDruid<Dining> {
    // 如果有特别的操作，可以自行添加
}
