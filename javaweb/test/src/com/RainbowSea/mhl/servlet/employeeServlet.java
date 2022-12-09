package com.RainbowSea.mhl.servlet;

import com.RainbowSea.mhl.Service.EmployeeService;

import com.RainbowSea.mhl.javaBean.Employee;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * 处理登录验证的操作
 */
public class employeeServlet extends HttpServlet {
    // 创建EmployeeDAO 实例对象，用于调用其中的方法

    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // post 方式：设置字符编码方式，必须在定义在第一行，才行
        response.setCharacterEncoding("UTF-8");  // 处理request.转发的中文编码

        PrintWriter out = response.getWriter();   // 用于在前端页面显示。
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");


        Employee employee = employeeService.getEmployeeNameByPwd(userName, userPwd);

        if(employee != null) {
//            request.getRequestDispatcher("/WEB-INF/jsp/test2.jsp").forward(request,response);
/*
            服务器内部转发: request.getRequestDispatcher("...").forward(request,response);
            一次请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的，同时地址栏没有改变
*/
             request.getRequestDispatcher("/WEB-INF/jsp/menuView.jsp").forward(request,response);

//            response.sendRedirect("test.html");
/*            客户端重定向:  response.sendRedirect("");
             两次请求响应的过程，客户端肯定知道请求了多少次,url在地址栏上会发生改变*/
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/loginError.jsp").forward(request,response);
//            response.sendRedirect("/WEB-INF/jsp/loginError.jsp");
        }


    }

}
