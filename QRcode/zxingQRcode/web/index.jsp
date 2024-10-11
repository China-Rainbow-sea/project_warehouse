<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en" >
<head>
    <meta charset="UTF-8"/>
    <title>生成普通黑白二维码</title>
</head>
<body>
<%--url：<input type="text" id="url">--%>
<hr/>
请输入文本内容:
<textarea id="url" cols="60" rows="20"></textarea>
<button onclick="generateQRcode()">生成二维码</button>
<br>
<img id="image"/>
<script>
    function generateQRcode() {
        let url = document.getElementById("url").value;
        let i = document.getElementById("image");
        i.src = "/zxingQRcode/create?url=" + url;
    }
</script>
</body>
</html>
