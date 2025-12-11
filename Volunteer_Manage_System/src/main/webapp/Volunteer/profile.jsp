<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人信息完善 - 志愿者管理系统</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"], input[type="tel"], select, textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        textarea {
            height: 100px;
            resize: vertical;
        }
        .btn {
            background-color: #007bff;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .message {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>完善个人信息</h1>
        
        <c:if test="${not empty param.success}">
            <div class="message success">个人信息更新成功！</div>
            <script>
                if(confirm("个人信息更新成功！是否返回主页？")) {
                    window.location.href = "index.jsp";
                }
            </script>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/service/Volunteer/profile" method="post">
            <div class="form-group">
                <label for="realName">姓名：</label>
                <input type="text" id="realName" name="realName" 
                       value="${volunteer.realName}" required>
            </div>
            
            <div class="form-group">
                <label for="gender">性别：</label>
                <select id="gender" name="gender" required>
                    <option value="">请选择性别</option>
                    <option value="男" ${volunteer.gender eq '男' ? 'selected' : ''}>男</option>
                    <option value="女" ${volunteer.gender eq '女' ? 'selected' : ''}>女</option>

                </select>
            </div>
            
            <div class="form-group">
                <label for="phone">电话：</label>
                <input type="tel" id="phone" name="phone" 
                       value="${volunteer.phone}" required>
            </div>
            
            <div class="form-group">
                <label for="address">地址：</label>
                <input type="text" id="address" name="address" 
                       value="${volunteer.address}" required>
            </div>
            
            <div class="form-group">
                <label for="experience">经历：</label>
                <textarea id="experience" name="experience" 
                          placeholder="请输入您的志愿服务经历...">${volunteer.experience}</textarea>
            </div>
            
            <button type="submit" class="btn">保存信息</button>
        </form>
    </div>
</body>
</html>
