<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ page import="com.rainbowsea.mhl.javaBean.Bill" %>

<html>
<head>
    <title>显示所有的账单信息</title>
</head>
<body>
    <h2 align="center">显示所有账单信息</h2>
    <table align="center" width="50%" border="1">
        <tr>
            <th>账单编号</th>
            <th>日期时间</th>
            <th>餐桌号</th>
            <th>菜品号</th>
            <th>菜名</th>
            <th>数量</th>
            <th>单价</th>
            <th>总价</th>
            <th>结账状态</th>
        </tr>

        <%
            request.setCharacterEncoding("UTf-8");

            // 获取到存储到请求域中的数据，request.setAttribute("allbill",bills); 与字符串名对应上
           List<Bill> billList =  (List<Bill>) request.getAttribute("allbill"); // 返回类型是 Object

            // 循环链表,取出数据
           for(Bill b : billList) {

        %>

        <tr>
            <td><%=b.getId()%></td>         <!-- 账单编号-->
            <td><%=b.getBillDate()%></td>   <!-- 生成的账单日期时间-->
            <td><%=b.getDiningId()%></td>   <!-- 餐桌号-->
            <td><%=b.getMenuId()%></td>     <!-- 菜品号-->
            <td><%=b.getMenuName()%></td>   <!-- 菜名-->
            <td><%=b.getMenuNums()%></td>   <!-- 菜的数量-->
            <td><%=b.getMenuPrice()%></td>  <!-- 菜品的单价-->
            <td><%=b.getMenuMoney()%></td>  <!-- 菜品的总价-->
            <td><%=b.getBillState()%></td>  <!-- 结账状态-->
        </tr>

        <%
            }
        %>

        <tr> <!-- onclick="window.history.back() 返回上一页 -->
            <td><input type="button" value="返回主菜单" onclick="window.history.back()"/></td>
        </tr>
    </table>

</body>
</html>
