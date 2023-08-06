package com.rainbowsea.mhl.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跳转到 WEB-INF 中的 checkOUt1 结账1 页面
 */
public class skipCheckOut1Servlet extends HttpServlet {
    @Override   // 链接使用 get
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

       // 使用转发，不可以重定向 response.sendRedirect(); 该方法无法访问 WEB-INF的资源
        request.getRequestDispatcher("WEB-INF/jsp/checkOut1.jsp").forward(request,response);

    }
}
