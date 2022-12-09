package com.RainbowSea.mhl.servlet;

import com.RainbowSea.mhl.Service.DiningService;
import com.RainbowSea.mhl.javaBean.Dining;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 处理查询显示所有的餐桌状态
 */
public class showTableServlet extends HttpServlet {
    private DiningService diningService = new DiningService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<Dining> dinings = diningService.allDining();

//        将一个集合放到请求域当中
        request.setAttribute("diningList",dinings);

        // 注意是转发(不可以 response.sendRedirect("");，因为重定向无法，方法WEB-INF中的资源)
        request.getRequestDispatcher("/WEB-INF/jsp/showTable.jsp").forward(request,response);

    }
}
