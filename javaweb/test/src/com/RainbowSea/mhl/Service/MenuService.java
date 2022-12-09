package com.RainbowSea.mhl.Service;


import com.RainbowSea.mhl.dao.MenuDAO;
import com.RainbowSea.mhl.javaBean.Menu;

import java.util.List;

/**
 * 处理有关 菜谱数据表的业务逻辑，统一在这里处理
 */
public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();


    /**
     * 查询所有 menu 数据表中的信息
     * @return  List<Menu>
     */
    public List<Menu> allMenu() {
        String sql = "select menu_id as menuId,menu_name as menuName,menu_type as menuType,menu_price as menuPrice " +
                "from menu";
        List<Menu> menuList = menuDAO.selectMany(sql, Menu.class);  // 可变参数可以不传参数，但不要传null,防止null引用

        return menuList;
    }



    /**
     * 通过菜品号，查询该菜，从而判断出该菜品号是否存在,不存在返回 null,存在返回 Menu
     * @param menuId 菜品号
     * @return  Menu
     */
    public Menu getMenuById(int menuId) {
        String sql = "select menu_id as menuId,menu_name as menuName,menu_type as menuType,menu_price as menuPrice " +
                "from menu where menu_id = ?";
        Menu menu = menuDAO.selectSingle(sql, Menu.class, menuId);
        return menu;
    }
}
