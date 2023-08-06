package com.rainbowsea.mhl.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 对用户是否登录过，登录成功过，进行一个安全验证登录。
 */
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        // 判断用户是否登录过
        HttpSession session = request.getSession(false);  // 获取到服务器当中的session ，没有不会创建的

        //System.out.println("执行了");

        /**
         * 说明这里我们通过 session 会话机制，判断用户是否登录过，如果用户没有登录就想要访问
         * 到其信息，不可以，因为我们这里判断了一次是否登录过，只有登录入过了，才会将中登录到
         * 用户名为 “username” 的信息存储到 session 会话当中，如果没有的话是查询不到的，返回的是 null
         * 需要注意的一点就是，我们的jsp 当中的内置对象，是会自动创建一个 session 会话对象的，但是
         * 因为这里我们进行了一个 双重的判断机制。注意：需要先将对应的 xx_jsp.java 生成才行。同时
         * 使用 <%@page session = false %> 指令的话，需要所有会被访问，生成的 Jsp 文件都需要设置。
         *
         *   jakarta.servlet.http.HttpSession session = null;
         *   session = pageContext.getSession();
         *   注意这里的 session.getAttribute("employee") 是从：com.rainbowsea.mhl.web.LoginServlet当中
         *   登录成功存储起来的，session会话信息
         */
        if (session != null && session.getAttribute("employee") != null) {
            // 进入这里，说明用户登录过，所以可以直接访问该页面
            // 重定向到主界面
            //response.sendRedirect(request.getContextPath() + "/jsp/menuView.jsp");
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            // 用户没有登录成功过，返回重定向到：重新登录界面
            response.sendRedirect(request.getContextPath() + "/login/main/login.jsp");
        }


    }

    @Override
    public void destroy() {

    }
}
