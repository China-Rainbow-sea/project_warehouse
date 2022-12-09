<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ page import="com.RainbowSea.mhl.javaBean.Dining" %>
<html>
<head>
    <title>显示餐桌状态</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");  // 设置字符编码

        // 获取到存入请求域中的数据，注意是通过你在传request.setAttribute("diningList",dinings);时的字符串名获取的
       List<Dining> diningList = (List<Dining>) request.getAttribute("diningList"); // 注意返回的是Object类型强转一下
    %>

    <table align="center" width="500" border="1">
        <h2 align="center">显示所有餐桌的状态</h2>
        <tr>
            <th>餐桌位</th>
            <th>餐桌状态</th>
            <th>预定人</th>
            <th>联系电话</th>
        </tr>

        <%
            // 遍历链表: 取出数据，放到表格中显示处理
            for(Dining d : diningList) {

        %>
            <%-- 表达时取值--%>
        <tr>
            <td><%=d.getDiningId()%></td>
            <td><%=d.getDiningState()%></td>
            <td><%=d.getOrderName()%></td>
            <td><%=d.getOrderTel()%></td>
        </tr>

        <%
            }
        %>

    </table>


</body>
</html>
