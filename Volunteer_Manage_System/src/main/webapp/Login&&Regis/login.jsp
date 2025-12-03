<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<h2>用户登录</h2>

<%-- 显示错误信息 --%>
<% if (request.getAttribute("errorMessage") != null) { %>
<div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
<% } %>

<%-- 显示成功信息 --%>
<% if (request.getAttribute("successMessage") != null) { %>
<div style="color: green;"><%= request.getAttribute("successMessage") %></div>
<% } %>

<form action="${pageContext.request.contextPath}/LoginRegisController?action=login_submit" method="post">
    用户名: <input type="text" name="username" id="username" required><br><br>
    密码: <input type="password" name="password" id="password" required><br><br>
    <input type="submit" value="登入">
</form>

<p>没有账号？ <a href="${pageContext.request.contextPath}/LoginRegisController?action=regis">立即注册</a></p>

</body>
</html>