<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>取消预定提醒信息</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");

        // 获取到存储在请求域中的提示信息 request.setAttribute("error",error);注意要和字符串名一致
        String error = (String)request.getAttribute("error");   // 返回的是Object,强制转换一下

    %>

    <div align="center">
        <h2><%=error%></h2> <br>
        <input type="button" value="返回重新取消预定" onclick="window.history.back()"/>
    </div>

</body>
</html>
