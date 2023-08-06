package com.rainbowsea.mhl.web;



import com.rainbowsea.mhl.javaBean.Bill;
import com.rainbowsea.mhl.service.BillServiceImpl;
import com.rainbowsea.mhl.service.impl.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 显示所有的账单信息
 */

@WebServlet("/jsp/showAllBillServlet")
public class showAllBillServlet extends HttpServlet {
    private BillService billService = new BillServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        List<Bill> bills = billService.allBill();

        // 将获取到的数据，存储的请求域当中去
        request.setAttribute("allbill",bills);

        // 使用转发()不是重定向,response.sendRedirect();重定向无法获取到WEB-INF中的数据
        request.getRequestDispatcher("/jsp/showAllBill.jsp").forward(request,response);

        //response.sendRedirect(request.getContextPath()+"/jsp/showAllBill.jsp");

    }
}
