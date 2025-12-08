<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <%-- 引入外部CSS文件 --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login-Regis/LandR.css">
</head>
<body>

<div class="auth-container">
    <h2>用户登录</h2>

    <%-- 错误信息 --%>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="alert alert-error"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <%-- 成功信息 --%>
    <% if (request.getAttribute("successMessage") != null) { %>
    <div class="alert alert-success"><%= request.getAttribute("successMessage") %></div>
    <% } %>

    <form action="${pageContext.request.contextPath}/LoginController" method="post">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" name="username" id="username" class="form-control" placeholder="请输入您的用户名" required>
        </div>

        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="请输入您的密码" required>
        </div>

        <div class="form-group">
            <label for="captcha">验证码</label>
            <div style="display: flex; gap: 10px;">
                <input type="text" name="captcha" id="captcha" class="form-control" placeholder="请输入验证码" required style="flex: 1;">
                <img src="${pageContext.request.contextPath}/captcha" id="captchaImg" alt="验证码" style="height: 40px; cursor: pointer;" onclick="refreshCaptcha()" title="点击刷新验证码">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">登 入</button>
    </form>

    <div class="auth-footer">
        <p>还没有账号？ <a href="${pageContext.request.contextPath}/RegisterController">立即注册</a></p>
    </div>
</div>

<script>
    function refreshCaptcha() {
        document.getElementById('captchaImg').src = '${pageContext.request.contextPath}/captcha?' + Math.random();
    }
</script>

</body>
</html>