package com.RainbowSea.mhl.service;

import com.RainbowSea.mhl.dao.MenuDAO;
import com.RainbowSea.mhl.javaBean.Menu;

import java.util.List;

/**
 * 处理 menu (菜单)数据表的业务，统一在这里
 */
public class MenuService {
    // 定义MenuDAO实例对象，用于调用其中的方法，完成业务
    private MenuDAO menuDAO = new MenuDAO();


    /**
     * 查询显示 menu所有的记录
     * @return  List<Menu> 链表
     */
    public List<Menu> allMenu() {
        String sql = "select * from menu";  // 测试一下
        List<Menu> menus = menuDAO.selectMany(sql, Menu.class);  // 可变参数可以不传参数，但不要传null
        return menus;
    }



    /**
     * 通过查询菜id,判断该菜是否存在(应用层外键约束)
     * @param id 菜的编号
     * @return Menu javaBean 实例对象
     */
    public Menu getMenuById(int id) {
        String sql = "select * from menu where id = ?";  // 测试一下
        Menu menu = menuDAO.selectSingle(sql, Menu.class,id);
        return menu;
    }

}
