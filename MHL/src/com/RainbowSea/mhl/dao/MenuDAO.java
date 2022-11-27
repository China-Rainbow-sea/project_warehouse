package com.RainbowSea.mhl.dao;

import com.RainbowSea.mhl.javaBean.Menu;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 继承JDBCUtilsByDruid<Menu>父类，调用其中的方法
 * 对 menu (菜谱)数据表进行增删改查操作的封装
 */
public class MenuDAO extends JDBCUtilsByDruid<Menu> {
    // 如有其他需要，可以自行添加
}
