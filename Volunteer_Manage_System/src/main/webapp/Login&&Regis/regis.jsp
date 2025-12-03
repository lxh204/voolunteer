<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<h2>用户注册</h2>

<%-- 显示错误信息 --%>
<% if (request.getAttribute("errorMessage") != null) { %>
<div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
<% } %>

<form action="${pageContext.request.contextPath}/LoginRegisController?action=regis_submit" method="post">
    用户名: <input type="text" name="username" id="username" required><br><br>
    密码: <input type="password" name="password" id="password" required><br><br>
    邮箱: <input type="email" name="email" id="email"><br><br>
    <input type="submit" value="注册">
    <input type="button" value="返回登录" onclick="location.href='${pageContext.request.contextPath}/LoginRegisController?action=login'">
</form>

</body>
</html>