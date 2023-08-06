package com.rainbowsea.mhl.service.impl;


import com.rainbowsea.mhl.javaBean.Menu;

import java.util.List;

/**
 * 处理有关 菜谱数据表的业务逻辑，统一在这里处理
 */
public interface MenuService {


    /**
     * 查询所有 menu 数据表中的信息
     *
     * @return List<Menu>
     */
    public List<Menu> allMenu();


    /**
     * 通过菜品号，查询该菜，从而判断出该菜品号是否存在,不存在返回 null,存在返回 Menu
     *
     * @param menuId 菜品号
     * @return Menu
     */
    public Menu getMenuById(int menuId);

}
