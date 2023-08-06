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
 * 处理结账1，选择结账的餐桌位号;
 */

@WebServlet("/jsp/rollback/checkOut1Servlet01")
public class checkOut1Servlet extends HttpServlet {
    private BillService billService = new BillServiceImpl();

    @Override   // form 表单的提交是使用 post 的方式
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");

        String diningIds = request.getParameter("diningId");
        // 将获取到的字符串值 转换为 数值
        int diningId = Integer.parseInt(diningIds);  // 注意已经是字符串了，不要加双引号了

        String errors = ""; // 用于存储错误，失败，成功的提示信息

        // 判断用户所选择的餐桌位，是否为，未结账的状态，只有为未结账的状态才可以结账
        // 已支付的，无人就餐的/已经预定还未点餐的，都不可以结账
        if (billService.getBillState(diningId)) {
            // 是未结账的状态，可以结账,这里处理的是打印，对应为结账的账单信息

            List<Bill> billByDiningIds = billService.getBillByDiningId(diningId);
            // 将查询到的未结账的，账单信息存入到请求域当中。
            request.setAttribute("billLists", billByDiningIds);

            // 计算出该对应未结账的餐桌号，消费的总价:
            double sumMoneys = billService.sumMoney(diningId);

            // 将查询计算到的总价,存储到请求域到中去.
            request.setAttribute("sumMoneys", sumMoneys);

            // 将数据转发到 ,结账2(支付方式)的页面处理。
            request.getRequestDispatcher("/jsp/checkOut2.jsp").forward(request, response);

        } else {
            // 不是未结账的状态，不可以结账
            // 因为我们一旦点餐了，就会生成账单,而没有点餐的仅仅只是预定了的,或者是无人就餐的，已经结账
            // 了的，我们都会更新 对应餐桌的账单的状态为，支付方式:,

            errors = "该餐桌: 已经结账了/无人就餐/预定但未点餐 :无法结账";
            // 将错误信息，存储到 请求域当中
            request.setAttribute("error", errors);
            // 将数据转发到，提示错误信息的页面当中去。

            //response.sendRedirect(request.getContextPath()+"/jsp/checkOutError.jsp");
            request.getRequestDispatcher("/jsp/checkOutError.jsp").forward(request, response);
        }


    }
}
