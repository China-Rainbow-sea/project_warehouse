package com.rainbowsea.mhl.web;


import com.rainbowsea.mhl.javaBean.Menu;
import com.rainbowsea.mhl.service.MenuServiceImpl;
import com.rainbowsea.mhl.service.impl.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 显示所有的菜品,显示所有的菜单
 */
@WebServlet("/jsp/showAllMenu")
public class showAllMenu extends HttpServlet {
    private MenuService menuService = new MenuServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        List<Menu> menuList = menuService.allMenu();
        request.setCharacterEncoding("utf-8");


        // 将查询到的数据，存储到请求域当中
        request.setAttribute("menu",menuList);

        // 注意使用的是转发(不是重定向) response.send
        // Redirect("");，因为重定向无法，方法WEB-INF中的资源)
        //request.getRequestDispatcher("/jsp/showAllBill.jsp").forward(request,response);

        request.getRequestDispatcher("/jsp/showAllMenu.jsp").forward(request,response);
        // response.sendRedirect(request.getContextPath()+"/jsp/showAllMenu.jsp");

    }
}
