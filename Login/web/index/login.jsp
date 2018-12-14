<%--
  Created by IntelliJ IDEA.
  User: Flyuz
  Date: 2018/12/14
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String msg = (String) session.getAttribute("msg");
    if (msg == null) {
        msg = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" media="screen" href="../static/css/login.css">
    <link rel="stylesheet" media="screen" href="../static/css/a.css">
</head>

<body>
<form class="login_form" action="../../loginServlet?type=login" method="post">
    <ul>
        <li>
            <h2>登录</h2>
            <span class="required_notification"><%=msg %></span>
        </li>
        <li>
            <label for="login_name">用户名：</label>
            <input id="login_name" name="user" placeholder="请填写您的用户名" required title="请填写您的用户名"/>
            <span class="form_hint">请填写您的用户名</span>
        </li>
        <li>
            <label for="login_password">密码：</label>
            <input id="login_password" name="pw" required="required" type="password" placeholder="请填写您的密码"/>
            <span class="form_hint">请填写您的密码</span>
        </li>
        <li>
            <button class="submit" type="submit">登录</button>
        </li>
        <li>
            <a href="index/register.jsp">注册</a>
        </li>
    </ul>
</form>
</body>
</html>
