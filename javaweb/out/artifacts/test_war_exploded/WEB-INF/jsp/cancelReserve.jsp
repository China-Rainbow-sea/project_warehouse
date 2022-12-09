<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>取消预定</title>
    <script type="text/javascript">
        function confirms() {
            return window.confirm("您,确定要取消预定吗 ?")
        }
    </script>
</head>

<body>
<h2 align="center">取消预定</h2>
<form action="cancelReserveService" method="post">
    <table align="center">
        <tr>
            <th>选择你要取消预定的餐桌位: </th>
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
        </tr>
        <tr>
            <td><input type="button" value="返回主菜单" onclick="window.history.back()" /></td>
            <td></td>
        </tr>
        <tr>
            <td><input type="submit" value="确定取消" onclick="return confirms() " /></td>
        </tr>
    </table>
</form>
</body>
</html>
