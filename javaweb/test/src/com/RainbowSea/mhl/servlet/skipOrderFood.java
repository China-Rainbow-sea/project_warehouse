package com.RainbowSea.mhl.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 跳转到 WEB-INF 中的 orderFood 点餐服务,
 */
public class skipOrderFood extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");

        // 使用转发，不要用重定向(response.sendRedirect();)无法访问 WEB-INF中的数据
        request.getRequestDispatcher("WEB-INF/jsp/orderFood.jsp").forward(request,response);

    }
}
