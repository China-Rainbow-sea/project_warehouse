package com.RainbowSea.mhl.dao;


import com.RainbowSea.mhl.javaBean.Menu;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 通过继承 父类 JDBCUtilsByDruid<T>  调用其中父类的方法
 * 对 menu 菜谱数据表进行一个“增删改查”的操作
 */
public class MenuDAO extends JDBCUtilsByDruid<Menu> {

}
