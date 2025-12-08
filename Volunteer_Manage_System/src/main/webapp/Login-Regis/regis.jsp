<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <%-- 引入外部CSS文件 --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login-Regis/LandR.css">
</head>
<body>

<div class="auth-container">
    <h2>创建新账号</h2>

    <%-- 错误信息 --%>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="alert alert-error"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <form action="${pageContext.request.contextPath}/RegisterController" method="post">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" name="username" id="username" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" name="password" id="password" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="email">邮箱</label>
            <input type="email" name="email" id="email" class="form-control">
        </div>

        <button type="submit" class="btn btn-primary">注 册</button>

        <%-- 返回按钮，使用 type="button" 防止触发表单提交 --%>
        <button type="button" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/LoginController'">
            返回登录
        </button>
    </form>
</div>

</body>
</html>