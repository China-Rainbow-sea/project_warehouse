package com.RainbowSea.mhl.servlet;

import com.RainbowSea.mhl.Service.BillService;
import com.RainbowSea.mhl.javaBean.Bill;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 显示所有的账单信息
 */
public class showAllBillServlet extends HttpServlet {
    private BillService billService = new BillService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        List<Bill> bills = billService.allBill();

        // 将获取到的数据，存储的请求域当中去
        request.setAttribute("allbill",bills);

        // 使用转发()不是重定向,response.sendRedirect();重定向无法获取到WEB-INF中的数据
        request.getRequestDispatcher("WEB-INF/jsp/showAllBill.jsp").forward(request,response);


    }
}
