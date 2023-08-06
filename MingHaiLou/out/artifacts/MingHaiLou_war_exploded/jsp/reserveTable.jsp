<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>

<html>
<head>
    <title>预定餐桌</title>
    <script type="text/javascript">
        function del(thisNode) {
            var userformElt = document.getElementById("userform")
            var ok = window.confirm("你确定要预定吗 ?")
            if(ok) {
                return true;
            } else {
                return false;
            }

        }
    </script>
</head>
<body>
<div>
    <form action="<%=request.getContextPath()%>/jsp/rollback/reserveTableServlet" method="post" id="userform">
        <h3 align="center">预定餐桌</h3>
        <table align="center" width="500" border="1">
            <tr>
                <th>请选择要预定的餐桌号: </th>
                <td>
                    <select name="diningId" size="1" >
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
            </tr>
            <tr>
                <th>预定人的姓名: </th>
                <td><input type="text" name="orderName" required /></td>
            </tr>
            <tr>
                <th>预定人的电话: </th>
                <td><input type="text" name="orderTel" maxlength="11" required /></td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="返回主菜单" onclick="window.history.back()" />
                </td>
                <%--<td><a href="#">返回主菜单</a></td>--%>
                <!--使用 js代码提交数据,使用window.confirm()确认窗口-->
                <td><input type="submit" value="预定" onclick=" return del(this)" /></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
