
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录加注册</title>
    <!-- 导入css样式 -->
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container">
        <h1>登录</h1>
        <h3>输入你的账号和密码</h3>
        <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="userName" placeholder="用户名" required>
                <!--required 表示必须填写-->
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="密码" required>
            </div>
            <div class="from-group">
                <%--  用户勾选了该单选框表示用户要 10 天免登录--%>
                <input type="checkbox" name="exempt" value="true">
                <span class="chk-box">10天免登录</span>
            </div>
            <div class="from-group">
                <input type="submit" value="登录" class="submit">
            </div>
            <div class="sign-up">
                <div>没有账户 ?</div>
                <a href="../../signin/main/signin.html">注册</a>
            </div>
        </form>
    </div>
</body>

</html>