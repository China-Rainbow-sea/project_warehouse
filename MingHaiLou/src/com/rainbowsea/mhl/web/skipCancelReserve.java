package com.rainbowsea.mhl.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跳转到 WEB-INF的中的CancelReserve  取消预定页面
 */
public class skipCancelReserve extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        request.setCharacterEncoding("UTF-8");

        // 使用转发(url不会变化), 不要用:response.sendRedirect("");重定向无法访问WEB-INF的的数据
        request.getRequestDispatcher("WEB-INF/jsp/cancelReserve.jsp").forward(request,response);


    }
}
