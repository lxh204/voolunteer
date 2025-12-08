<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login-Regis/LandR.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login-Regis/LandR.css?v=<%= System.currentTimeMillis() %>">
</head>
<body>

<div class="auth-container">
    <h2>创建新账号</h2>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="alert alert-error" style="color: red;"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <form action="${pageContext.request.contextPath}/RegisterController" method="post" novalidate>

        <div class="form-group">
            <label for="username">用户名</label>
            <div id="usernameError" class="error-message" style="color: red; display: none;"></div>
            <input type="text" name="username" id="username" class="form-control" placeholder="请输入用户名">
        </div>

        <div class="form-group">
            <label for="password">密码</label>
            <div id="passwordError" class="error-message" style="color: red; display: none;"></div>
            <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码">
        </div>

        <div class="form-group">
            <label for="email">邮箱</label>
            <div id="emailError" class="error-message" style="color: red; display: none;"></div>
            <input type="email" name="email" id="email" class="form-control" placeholder="请输入邮箱">
        </div>

        <div class="form-group">
            <label for="captcha">验证码</label>
            <div id="captchaError" class="error-message" style="color: red; display: none;"></div>
            <div style="display: flex; gap: 10px;">
                <input type="text" name="captcha" id="captcha" class="form-control" placeholder="请输入验证码" style="flex: 1;">
                <img src="${pageContext.request.contextPath}/captcha" id="captchaImg" alt="验证码" style="height: 40px; cursor: pointer;" onclick="refreshCaptcha()" title="点击刷新验证码">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">注 册</button>

        <button type="button" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/LoginController'">
            返回登录
        </button>
    </form>
</div>

<script>
    function refreshCaptcha() {
        document.getElementById('captchaImg').src = '${pageContext.request.contextPath}/captcha?' + Math.random();
    }

    document.querySelector('form').addEventListener('submit', function(e) {
        let isValid = true;
        document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');

        if (document.getElementById('username').value.trim() === '') {
            const err = document.getElementById('usernameError');
            err.textContent = '用户名不能为空';
            err.style.display = 'block';
            isValid = false;
        }

        if (document.getElementById('password').value.trim() === '') {
            const err = document.getElementById('passwordError');
            err.textContent = '密码不能为空';
            err.style.display = 'block';
            isValid = false;
        }

        if (document.getElementById('email').value.trim() === '') {
            const err = document.getElementById('emailError');
            err.textContent = '邮箱不能为空';
            err.style.display = 'block';
            isValid = false;
        }

        if (document.getElementById('captcha').value.trim() === '') {
            const err = document.getElementById('captchaError');
            err.textContent = '验证码不能为空';
            err.style.display = 'block';
            isValid = false;
        }

        if (!isValid) e.preventDefault();
    });
</script>

</body>
</html>