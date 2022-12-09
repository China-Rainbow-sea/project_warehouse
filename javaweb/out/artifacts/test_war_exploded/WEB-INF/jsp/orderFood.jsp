<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>点餐服务</title>
    <script type="text/javascript">
        function confirms() {
            var ok = window.confirm("是否确认点餐 ?")
            if(ok) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</head>
<body>
<h2 align="center">点餐服务</h2>
<div>
    <form action="orderFoodServlet" method="post">
        <table width="500" border="1" align="center">
            <tr>
                <th>请选择要点餐的餐桌位: </th>
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
                <th>请选择对应从菜品: </th>
                <td>
                    <select name="menuId" size="1">
                        <!-- multiple= "multiple" 支持多选的,size 设置显示的条目数,没有写明的话默认是为一条-->
                        <option value="1">八宝饭</option>
                        <option value="2">叉烧包</option>
                        <option value="3">宫保鸡丁</option>
                        <option value="4">山药</option>
                        <option value="5">银丝卷</option>
                        <option value="6">水煮鱼</option>
                        <option value="7">甲鱼汤</option>
                        <!-- 有多少个选项,就定义多少个 option-->
                    </select>
                </td>
            </tr>
            <tr>
                <th>请输入菜的数量: </th>
                <td><input type="text" value="1" name="menuNums" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="确定点餐" onclick="return confirms() "> </td>
                <td><a href="orderFood">再次点餐</a></td>
            </tr>
            <tr>
                <td> <!--window.history.back()返回上一个窗口-->
                    <input type="button" value="返回主菜单" onclick="window.history.back()" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
