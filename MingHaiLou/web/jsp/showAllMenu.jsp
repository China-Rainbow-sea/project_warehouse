<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ page import="com.rainbowsea.mhl.javaBean.Menu" %>


<html>
<head>
    <title>显示所有的菜品信息</title>
</head>
<body>
    <h2 align="center">显示所有菜品</h2>
    <table width="500" border="1" align="center">
        <tr>
            <th>菜品编号</th>
            <th>菜名</th>
            <th>菜的种类</th>
            <th>单价</th>
        </tr>

        <%
            request.setCharacterEncoding("UTF-8");
            // 从request存储的请求域中获取对于存储的数据信息
            // 注意:  request.setAttribute("menu",menuList); 存储时的字符串名要对应上
            List<Menu> menuList = (List<Menu>) request.getAttribute("menu");  // 返回的是 Object

            for(Menu m : menuList) {

        %>
        <tr>
            <td><%=m.getMenuId()%></td      <!-- 菜的编号-->
            <td><%=m.getMenuName()%></td>   <!--菜名-->
            <td><%=m.getMenuType()%></td>   <!--菜的种类-->
            <td><%=m.getMenuPrice()%></td>  <!--菜的单价-->
        </tr>

        <%
            }
        %>

        <tr>
            <td></td>
            <td>
                <!--window.history.back() 返回上一页-->
                <input type="button" value="返回主菜单" onclick="window.history.back()"/>
            </td>
        </tr>
    </table>
</body>
</html>
