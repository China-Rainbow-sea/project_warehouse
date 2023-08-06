package com.rainbowsea.mhl.web;


import com.rainbowsea.mhl.service.BillServiceImpl;
import com.rainbowsea.mhl.service.DiningServiceImpl;
import com.rainbowsea.mhl.service.impl.BillService;
import com.rainbowsea.mhl.service.impl.DiningService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 点餐服务，处理
 */

@WebServlet("/jsp/orderFoodServlet")
public class orderFoodServlet extends HttpServlet {
    private DiningService diningService = new DiningServiceImpl();
    private BillService billService = new BillServiceImpl();

    @Override   // 注意提交是数据提交的是 post 方式
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String diningIds = request.getParameter("diningId");  // 餐桌位
        int diningId = Integer.parseInt(diningIds);              // 将字符串转为数值
        String menuIds = request.getParameter("menuId");      // 菜品编号
        int menuId = Integer.parseInt(menuIds);
        String menuNums = request.getParameter("menuNums");   // 菜品的数量

        String error = "";  // 存放成功，失败，错误的提示信息

        if("".equals(menuNums.trim())) {
            error = "注意菜品数量不可以为空";
        } else {

            int menuNum = Integer.parseInt(menuNums); // 注意本身就是字符串不要加双引号了
            if(menuNum >= 1) {
                // 方式 integer.空转换异常

                // 走到这里，说明上述参数没有问题,更新数据表
                // 更新 dining 餐桌的状态改为 "就餐中",如果是直接就餐的，不用添加用户名和联系人''
                // 其他的则直接修改餐桌的状态,
                if(diningService.setStateById(diningId)) {
                    // 添加一条账单记录在 bill 账单表中
                    if(billService.billMenu(diningId,menuId,menuNum)) {
                        error = "点餐成功";
                    } else {
                        error = "账单更新失败";
                    }

                } else {
                    error = "点餐失败,餐桌改为 就餐中的状态，失败";
                }

            } else {
                error = "注意菜品数量的个数必须 >= 1";
            }

        }

        // 将数据存储到请求域当中去
        request.setAttribute("errors",error);

        // 使用转发(不要使用重定向,response.sendRedirect();) 该方式无法获取到 WEB-INF中的资源
        request.getRequestDispatcher("/jsp/orderFoodError.jsp").forward(request,response);
        //response.sendRedirect(request.getContextPath()+"/jsp/orderFoodError.jsp");

    }
}
