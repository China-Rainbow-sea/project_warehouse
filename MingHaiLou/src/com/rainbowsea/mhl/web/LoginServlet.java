package com.rainbowsea.mhl.web;


import com.rainbowsea.mhl.javaBean.Employee;
import com.rainbowsea.mhl.service.EmployeeServiceImpl;
import com.rainbowsea.mhl.service.impl.EmployeeService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * 处理登录验证的操作
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // 创建EmployeeDAO 实例对象，用于调用其中的方法

    private EmployeeService employeeService = new EmployeeServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // post 方式：设置字符编码方式，必须在定义在第一行，才行
        response.setCharacterEncoding("UTF-8");  // 处理request.转发的中文编码

        PrintWriter out = response.getWriter();   // 用于在前端页面显示。
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("password");

        // 获取到用户是否勾选了 10 天免登录的操作
        String exempt = request.getParameter("exempt");


        Employee employee = employeeService.getEmployeeNameByPwd(userName, userPwd);


        if (employee != null) {
//            request.getRequestDispatcher("/WEB-INF/jsp/test2.jsp").forward(request,response);
/*
            服务器内部转发: request.getRequestDispatcher("...").forward(request,response);
            一次请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的，同时地址栏没有改变
*/
            // 用于判断用户，是否登录过，进行一个登录判断
            HttpSession session = request.getSession();  // 服务器当中没有 session 会话域自动创建
            session.setAttribute("employee", employee);  // 将用户名存储到 session 会话域当中



            // 判断用户是否勾选了 10 天免登录的操作，勾选了的话，将账号和密码存储到Cookie当中
            // 用户 10 天免登录
            if ("true".equals(exempt)) {
                // 创建 Cookie 对象存储登录名
                Cookie cookie = new Cookie("username", userName);
                // 创建Cookie 对象存储登录密码
                Cookie cookie2 = new Cookie("password", userPwd);

                // 设置 cookie 的有效期为 10 天
                cookie.setMaxAge(60 * 60 * 24 * 10);// 单位时 s 秒
                cookie2.setMaxAge(60 * 60 * 24 * 10);

                // 设置Cookie 关联的path(只要访问时这个应用，这个项目，浏览器一定要携带两个 cookie信息发送给服务器)
                cookie.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());  // 动态获取到项目名，的根路径


                // 将服务器生成的 cookie 信息 响应到浏览器端
                response.addCookie(cookie);
                response.addCookie(cookie2);  // 注意是 response 服务器端将 cookie的信息响应到客户端，客户端接收并存储起来
                // session 会话时，服务器创建的 ，客户端发送request  请求获取到该 session 对象的 sessionID

            }

            // 登录成功跳转，到主界面
            response.sendRedirect(request.getContextPath() + "/jsp/menuView.jsp");

/*            客户端重定向:  response.sendRedirect("");
             两次请求响应的过程，客户端肯定知道请求了多少次,url在地址栏上会发生改变*/
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/loginError.jsp");
        }


    }

}
