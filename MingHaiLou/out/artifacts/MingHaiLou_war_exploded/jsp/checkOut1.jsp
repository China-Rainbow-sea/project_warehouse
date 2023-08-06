<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>结账</title>
</head>
<body>
    <h2 align="center">结账</h2>
    <form action="<%=request.getContextPath()%>/jsp/rollback/checkOut1Servlet01" method="post">
        <table align="center">
            <th>请选择你要结账的餐桌位号: </th>
            <td>
                <select name="diningId" size="1">
                    <!-- multiple= "multiple" 支持多选的,size 设置显示的条目数,没有写明的话默认是为一条-->
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <!-- 有多少个选项,就定义多少个 option-->
                </select>
            </td>
            <tr>
                <td><input type="submit" value="提交" /></td>
            </tr>
            <tr>
                <td><input type="button" value="返回主菜单" onclick="window.history.back()"></td>
            </tr>
        </table>
    </form>

</body>
</html>
