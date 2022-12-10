<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>结账错误信息</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");

        // request.setAttribute("error",errors); 获取对应请求域，中字符串名一致的数据
        String error =  (String)request.getAttribute("error");  // 返回类型为 Object 强制转换一下
    %>

    <h2 align="center"><%=error%></h2><br><br>
    <table align="center">
        <tr>  <!-- onclick="window.history.back()返回上一个页面-->
            <td> <input type="button" value="返回" onclick="window.history.back()" /> </td>
        </tr>
    </table>

</body>
</html>
