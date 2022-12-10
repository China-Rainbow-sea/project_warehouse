package com.RainbowSea.mhl.servlet;

import com.RainbowSea.mhl.Service.BillService;
import com.RainbowSea.mhl.Service.DiningService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理结账2，提交的支付方式，
 */
public class checkOut2Servlet extends HttpServlet {
    private BillService billService = new BillService();
    private DiningService diningService = new DiningService();
    @Override    // form 表单提交的方式是 post
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        request.setCharacterEncoding("UTF-8");
        String billState = request.getParameter("billState");  // 获取到表单的支付方式

        String diningIds = request.getParameter("diningId");   // 获取到表单中隐藏域中存放的餐桌位号
        // 注意因为该字符串返回的是 1.0,2.0的浮点数,无法使用 Integer
        int diningId = (int)Double.parseDouble(diningIds);        // 已经是字符串了就不用再加双引号了.



        String error = "";    // 错放错误提示信息


        //  更新对应餐桌号的账单状态为: 结账方式
        if(billService.setBillStateBydiningId(diningId,billState)) {

            // 更新结账后，餐桌的状态为 "初始化，无人就餐无人预定的状态"
            if(diningService.billInit(diningId)) {
                error = "支付成功";
            } else {
                error = "支付失败:  餐桌初始化失败";
            }
        } else {
            error = "支付失败：账单支付方式修改失败";
        }


         // 将信息存储到请求域中
        request.setAttribute("error",error);

        // 转发到提示信息页面:checkOutError.jsp
        request.getRequestDispatcher("WEB-INF/jsp/checkOutError.jsp").forward(request,response);

    }
}
