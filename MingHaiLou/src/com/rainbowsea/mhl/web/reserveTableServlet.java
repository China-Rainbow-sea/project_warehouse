package com.rainbowsea.mhl.web;


import com.rainbowsea.mhl.javaBean.Dining;
import com.rainbowsea.mhl.service.DiningServiceImpl;
import com.rainbowsea.mhl.service.impl.DiningService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 预定餐桌，处理
 */

@WebServlet("/jsp/rollback/reserveTableServlet")
public class reserveTableServlet extends HttpServlet {
    private DiningService diningService = new DiningServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String diningIds = request.getParameter("diningId"); // 餐桌编号
        int diningId = Integer.parseInt(diningIds);   // 将字符串转换为 数值类型

        String orderName = request.getParameter("orderName");  // 预定人名
        String orderTel = request.getParameter("orderTel");    // 预定的电话

        HttpSession session = request.getSession();  // 获取session用于存储(预定成功，还是失败)

        String bools = "";   // 存储成功失败的提示字符串

        // 判断用户输入的 人名和电话是否为空
        if("".equals(orderName.trim()) || "".equals(orderTel.trim())){
            bools = "姓名和电话不可以为空";
        } else {

            // 通过餐桌编号,查看餐桌是否已经被预定了,已经被预定了的无法再预定了。
            // 已被预定,返回 true
//            boolean bool = diningService.getDiningByIdAndState(diningId);
            // 输入餐桌编号,判断餐桌是的状态是否为 "空"，空才可以预定，为就餐中，已预定都无法预定
            Dining dining = diningService.getDiningById(diningId);
            boolean bool =  "空".equals(dining.getDiningState()) ;
            // 为空可以预定
            if(bool) {
                // 没有被预定，以及就餐中，可以预定
                // 更新餐桌状态,并将数据更新过去
                boolean bool2 = diningService.updateDining(diningId, orderName, orderTel);


                if(bool2) {
                    bools = "预定成功";
                } else {
                    bools = "预定失败";
                }

            } else {
                bools = "抱歉，该餐桌已经被预定了或者正在就餐中";
            }

        }

        session.setAttribute("bools",bools);

        // 转发
        //response.sendRedirect(request.getContextPath()+"/jsp/tableError.jsp");
        request.getRequestDispatcher("/jsp/tableError.jsp").forward(request,response);


    }
}
