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


/**
 * 处理10天免登录的效果
 */
@WebServlet("/exemptServlet")
public class exemptServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        request.setCharacterEncoding("UTF-8");  // post 方式：设置字符编码方式，必须在定义在第一行，才行
        response.setCharacterEncoding("UTF-8");  // 处理request.转发的中文编码

        // 获取到 客户端发送的 Cookie 信息
        Cookie[] cookies = request.getCookies();


        // 注意如果为客户端没有发送 cookie 信息，返回的是 null ,不是数组为 0
        String userName = null;
        String userPasswrod = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Cookie 是一个Map集合类似
                String name = cookie.getName();

                // 获取到Cookie 当中存储的用户名信息
                if ("username".equals(name)) {  // username 服务器创建 Cookie 是的 name key值
                    userName = cookie.getValue();
                } else if ("password".equals(name)) {
                    userPasswrod = cookie.getValue(); // 获取到 Cookie 当中存储用户名密码
                }
            }
        }

        // 判断从 Cookie 当中获取到用户名和密码是否正确
        // 首先判断是获取到该存储到 Cookie 当中的用户名和密码信息，
        // 注意可能用户根本就没有登录过，所以无法获取到是为 null的
        if (userName != null && userPasswrod != null) {
            Employee employee = employeeService.getEmployeeNameByPwd(userName, userPasswrod);

            // 该用户信息查询到，说明存储在Cookie当中的账号和密码是正确的
            // 免登录，直接跳转到主界面
            if(employee != null) {


                // 用于判断用户，是否登录过，进行一个登录判断
                HttpSession session = request.getSession();  // 服务器当中没有 session 会话域自动创建
                session.setAttribute("employee", employee);  // 将用户名存储到 session 会话域当中

                // 登录成功跳转，到主界面
                response.sendRedirect(request.getContextPath() + "/jsp/menuView.jsp");
            }
        } else {  // 用户没有 10 天免登录/Cookie打当中的密码错误，在重新登录。
            //response.sendRedirect(request.getContextPath()+"/login");
            request.getRequestDispatcher("/login").forward(request,response);
        }
    }
}
