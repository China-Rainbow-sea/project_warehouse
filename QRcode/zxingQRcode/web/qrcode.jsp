<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en" >
<head>
    <meta charset="UTF-8"/>
    <title>生成Log二维码</title>
</head>
<body>
<%-- 大数据用: multipart/form-data--%>
<form action="/zxingQRcode/generateWithLogon" method="post" enctype="multipart/form-data">
    请输入文本内容: <input type="text" name="url"><br>
    请选择图片: <input type="file" name="logo"><br>
    <input type="submit" value="生成带Logo的二维码">
</form>

</body>
</html>
