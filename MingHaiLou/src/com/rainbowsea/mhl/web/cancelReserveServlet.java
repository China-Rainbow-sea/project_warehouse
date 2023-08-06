package com.rainbowsea.mhl.web;


import com.rainbowsea.mhl.service.DiningServiceImpl;
import com.rainbowsea.mhl.service.impl.DiningService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 处理取消预定的业务
 */
@WebServlet("/jsp/rollback/cancelReserveService")
public class cancelReserveServlet extends HttpServlet {
    private DiningService diningService = new DiningServiceImpl();
    @Override   // 对应form提交方式是 post
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {


        request.setCharacterEncoding("UTF-8");

        String diningIds = request.getParameter("diningId");
        int diningId = Integer.parseInt(diningIds);     // 将字符串转换为数值类型

        String error = "";   // 存储错误，失败，成功的提示信息

        // 判断餐桌是否为 "已预定的" 只有为已经预定的才可以取消，为"就餐中的"，"空” 的无法取消预定
        if(diningService.getDiningByIdAndState(diningId)) {

            // 是已预定的状态:可以取消预定,将餐桌的状态置为 初始化 “无人的状态”
            if(diningService.billInit(diningId)) {
                error = "取消预定成功";
            } else {
                error = "取消预定失败，餐桌初始化失败";
            }

        } else {
            error = "该餐桌，正在就餐中 或者 为 空 的状态为 无法取消预定";
        }


        // 将提示信息，存储到请求域当中，
        request.setAttribute("error",error);

        // 转发到WEB-INF 显示错误信息的Jsp页面中
        request.getRequestDispatcher("/jsp/cancelReserveError.jsp").forward(request,response);
    }
}
