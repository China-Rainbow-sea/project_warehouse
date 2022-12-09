package com.RainbowSea.mhl.view;


import com.RainbowSea.mhl.Service.MenuService;
import com.RainbowSea.mhl.javaBean.Menu;

import java.util.List;

public class MenuView {
    private MenuService menuService = new MenuService();


    /**
     * 显示所有菜谱信息
     */
    public void showMenu() {
        List<Menu> menuList = menuService.allMenu();

        // 遍历链表的方式一:
        System.out.println("菜品编号\t\t菜名\t\t菜的种类\t\t单价");
        menuList.forEach(System.out::println);

        // 遍历链表的方式二:
        /*for(Menu m : menuList) {
            System.out.println(m);
        }
        */
    }
}
