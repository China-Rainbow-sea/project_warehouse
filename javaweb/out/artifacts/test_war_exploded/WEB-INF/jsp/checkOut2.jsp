<%--
  Created by IntelliJ IDEA.
  User: huo
  Date: 2022/12/9
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ page import="com.RainbowSea.mhl.javaBean.Bill" %>
<html>
<head>
    <title>结账2 支付金额</title>
    <script type="text/javascript">
        function confirms() {
            return window.confirm("您,确定支付吗 ?")  // 返回的是 确认返回true,取消返回false
        }
    </script>
</head>
<body>
    <div>
        <table align="center" width="50%" border="1">
            <tr>
                <th>账单编号</th>
                <th>日期时间</th>
                <th>餐桌号</th>
                <th>菜品号</th>
                <th>菜名</th>
                <th>数量</th>
                <th>单价</th>
                <th>总价</th>
                <th>结账状态</th>
            </tr>
            <%
                request.setCharacterEncoding("UTF-8");
                // 获取到查询结果存放到，请求域中的数据  request.setAttribute("billLists",billByDiningIds);
                List<Bill> billList = (List<Bill>) request.getAttribute("billLists");   // 注意字符串名需要对应上，才能获取到其中的值

                // 获取到查询到该餐桌未结账的消费总金额: request.setAttribute("sumMoneys",sumMoneys);
                double sumMoneys = (double) request.getAttribute("sumMoneys");  // 消费总金额

                double diningId = 0;   // 存储餐桌位,用于后端的根据餐桌位进行结账更新数据表

                // 循环链表取值,
                for(Bill b : billList) {
                    diningId = b.getDiningId();    // 获取到显示的餐桌位号

            %>
            <tr>
                <td><%=b.getId()%></td>         <!-- 账单编号-->
                <td><%=b.getBillDate()%></td>   <!-- 生成的账单日期时间-->
                <td><%=b.getDiningId()%></td>   <!-- 餐桌号-->
                <td><%=b.getMenuId()%></td>     <!-- 菜品号-->
                <td><%=b.getMenuName()%></td>   <!-- 菜名-->
                <td><%=b.getMenuNums()%></td>   <!-- 菜的数量-->
                <td><%=b.getMenuPrice()%></td>  <!-- 菜品的单价-->
                <td><%=b.getMenuMoney()%></td>  <!-- 菜品的总价-->
                <td><%=b.getBillState()%></td>  <!-- 结账状态-->
            </tr>

            <%
                }   // 循环结束
            %>
            <tr>
                <th>总共消费: <%=sumMoneys%>¥</th>
            </tr>
        </table>
        <form action="checkOut2Servlet" method="post">
            <table align="center" width="50%">
                <tr>
                    <th>请选择支付方式: </th>
                    <td>
                        <select name="billState" size="1">
                            <!-- multiple= "multiple" 支持多选的,size 设置显示的条目数,没有写明的话默认是为一条-->
                            <option value="现金">现金</option>
                            <option value="支付宝">支付宝</option>
                            <option value="微信">微信</option>
                            <option value="银行卡">银行卡</option>
                            <!-- 有多少个选项,就定义多少个 option-->
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="确认支付" onclick=" return confirms()"></td>
                    <td>   <!-- 第一个隐藏域，用于提交该账单对应的，餐桌位号，用于后端结账处理，更新数据表-->
                        <input type="hidden" name="diningId" value="<%=diningId%>" />
                    </td>
                </tr>
                <tr>
                    <td> <!-- window.history.back()返回上一页界面 -->
                        <input type="button" value="重新选择餐桌位" onclick="window.history.back()">
                    </td>
                </tr>
            </table>
        </form>

    </div>

</body>
</html>
