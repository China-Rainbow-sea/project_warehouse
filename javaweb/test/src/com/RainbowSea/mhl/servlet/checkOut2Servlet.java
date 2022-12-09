package com.RainbowSea.mhl.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理结账2，提交的支付方式，
 */
public class checkOut2Servlet extends HttpServlet {
    @Override    // form 表单提交的方式是 post
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        request.setCharacterEncoding("UTF-8");
        String billState = request.getParameter("billState");  // 获取到表单的支付方式

        String error = "";    // 错放错误提示信息

        // 将信息存储到请求域中
//        request.setAttribute("error",error);
//        request.getAttribute("");   // 获取到请求域中的信息，这里不用
        System.out.println(billState);

        // xml已经配置好了.

        // 注意错误jsp提示页面已经有了 checkOutError.jsp

    }
}
