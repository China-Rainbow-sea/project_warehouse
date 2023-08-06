
<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>预定成功与否的错误提示</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");  // 设置字符编码
    //response.setContentType("text/html;charset=UTF-8");
    //request.setCharacterEncoding("UTF-8");
    //response.setCharacterEncoding("utf-8");
    //response.setContentType("text/html;charset=utf-8");


    String bool = (String) (session.getAttribute("bools"));  // Object 强制转换一下

%>

<h2 align="center"><%=bool%></h2> <br>
<%--<h2>测试</h2>--%>
<div align="center">
    <input type="button" value="返回重新预定" onclick="window.history.back()" />
</div>
</body>
</html>
