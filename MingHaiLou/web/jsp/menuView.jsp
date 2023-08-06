<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 7:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>主页菜单</title>


    <script type="text/javascript">
        function exit() {
            return window.confirm("您,确定要退出满汉楼吗 ?")
        }
    </script>

</head>
<%--导入外部 css 样式--%>
<link rel ="stylesheet"type="text/css" href="./style.css"/>

<body>
<!-- 页眉 -->
<section>
    <div class="a">
        <div style="color: #0081ff">当前在线用户数: <%=request.getServletContext().getAttribute("onlinecount")%></div>
        <div><a href="<%=request.getContextPath()%>/jsp/showTableServlet">显示所有餐桌的状态</a></div>
        <div><a href="<%=request.getContextPath()%>/jsp/reserveTable.jsp">预定餐桌</a></div>
        <div><a href="<%=request.getContextPath()%>/jsp/showAllMenu">显示所有菜品</a></div>
        <div><a href="<%=request.getContextPath()%>/jsp/orderFood.jsp">点餐服务</a></div>
        <div><a href="<%=request.getContextPath()%>/jsp/showAllBillServlet">显示所有的账单信息</a></div>
        <div><a href="<%=request.getContextPath()%>/jsp/checkOut1.jsp">结账</a></div>
        <div><a href="<%=request.getContextPath()%>/jsp/cancelReserve.jsp">取消预定</a></div>
        <div><a href="<%=request.getContextPath()%>/jsp/exit" onclick="return exit()" >退出满汉楼</a></div>
    </div>
    <!-- 先进行一个整体的布局,三个div盒子,每个div盒子放8个枫叶-->
    <div class="set set1">
        <div><img src="leaves1.png" alt=""></div>
        <div><img src="leaves2.png" alt=""></div>
        <div><img src="leaves3.png" alt=""></div>
        <div><img src="leaves4.png" alt=""></div>
    </div>
    <div class="set set2">
        <div><img src="leaves1.png" alt=""></div>
        <div><img src="leaves2.png" alt=""></div>
        <div><img src="leaves3.png" alt=""></div>
        <div><img src="leaves4.png" alt=""></div>
    </div>
    <div class="set set3">
        <div><img src="leaves1.png" alt=""></div>
        <div><img src="leaves2.png" alt=""></div>
        <div><img src="leaves3.png" alt=""></div>
        <div><img src="leaves4.png" alt=""></div>
    </div>
</section>
</body>
</html>
