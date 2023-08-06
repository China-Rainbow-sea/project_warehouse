<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>登录失败</title>
</head>
<body>
    <div align="center">
        <h3>密码或账号错误</h3> <br>
        <a href="<%=request.getContextPath()%>/login/main/login.jsp" >请重新登录</a>
    </div>
</body>
</html>
