package com.RainbowSea.mhl.servlet;

import com.RainbowSea.mhl.Service.MenuService;
import com.RainbowSea.mhl.javaBean.Menu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 显示所有的菜品,显示所有的菜单
 */
public class showAllMenu extends HttpServlet {
    private MenuService menuService = new MenuService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        List<Menu> menuList = menuService.allMenu();

        // 将查询到的数据，存储到请求域当中
        request.setAttribute("menu",menuList);

        // 注意使用的是转发(不是重定向) response.sendRedirect("");，因为重定向无法，方法WEB-INF中的资源)
        request.getRequestDispatcher("/WEB-INF/jsp/showAllMenu.jsp").forward(request,response);

    }
}
