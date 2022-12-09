package com.RainbowSea.mhl.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 提出满汉楼
 */
public class exitMhlServlet extends HttpServlet {
    @Override   // 链接使用 doget
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");


        // 因为我们使用的是 Apache-DButils以及Druid(德鲁伊) 的方式连接数据库的，
        // 其中在 JDBCUtilsByDruid 中对应的每一个 DDL ,DQL 操作都关闭了资源，
        // 所以这里不需要关闭资源，是要跳转到登录界面就可以了

        // 这里就不要使用转发了，因为这个访问的不是WEB-INF内部的资源,而是外部的资源
        // 这里需要改变 url(统一资源定位符)地址栏上的位置，才合理
        // 转发是服务器内部的操作，客户端不知道，转发了多次，做了什么
        // 而重定向客户端是知道，转发了多次的。因为 url 会发生改变。
//        request.getRequestDispatcher("/show").forward(request,response);

        // 重定向
        response.sendRedirect("/mhl/index.html");

    }
}
