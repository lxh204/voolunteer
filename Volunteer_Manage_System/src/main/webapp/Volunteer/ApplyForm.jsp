<!-- applyForm.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>活动报名</title>
</head>
<body>
<h2>报名活动：${activity.title}</h2>

<form action="${pageContext.request.contextPath}/ApplicationServlet?action=apply" method="post">
    <input type="hidden" name="activityId" value="${activity.id}">

    <table>
        <tr>
            <td>活动名称:</td>
            <td>${activity.title}</td>
        </tr>
        <tr>
            <td>活动地点:</td>
            <td>${activity.location}</td>
        </tr>
        <tr>
            <td>活动时间:</td>
            <td>${activity.startTime} 至 ${activity.endTime}</td>
        </tr>
        <tr>
            <td>报名理由:</td>
            <td><textarea name="applyReason" rows="6" cols="50" required></textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交报名">
                <input type="button" value="取消" onclick="history.back()">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
