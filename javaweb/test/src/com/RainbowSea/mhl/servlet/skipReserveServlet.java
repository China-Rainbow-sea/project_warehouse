package com.RainbowSea.mhl.servlet;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 对预定页面的跳转,链接使用 get方式
 */
public class skipReserveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 注意是转发(不可以 response.sendRedirect("");，因为重定向无法，方法WEB-INF中的资源)
        request.getRequestDispatcher("/WEB-INF/jsp/reserveTable.jsp").forward(request,response);


    }


}