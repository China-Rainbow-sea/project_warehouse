<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>点餐错误提示</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        //  request.setAttribute("errors",error); 注意需要和定义的 字符串名一致，才能获取到数据
        String error = (String)request.getAttribute("errors");  // 返回类型是 Object 强制转换一下

    %>
    <!--使用表达式中,获取变量显示-->
    <h2 align="center"><%=error%></h2>

</body>
</html>
