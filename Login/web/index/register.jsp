<%--
  Created by IntelliJ IDEA.
  User: Flyuz
  Date: 2018/12/14
  Time: 19:40
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
    <title>注册</title>
    <link rel="stylesheet" media="screen" href="../static/css/login.css">
    <link rel="stylesheet" media="screen" href="../static/css/a.css">
</head>

<body>
<form class="login_form" action="../../loginServlet?type=reg" method="post">
    <ul>
        <li>
            <h2>注册</h2>
            <span class="required_notification"><%=msg %></span>
        </li>
        <li>
            <label for="reg_name">用户名：</label>
            <input id="reg_name"  name="user" required="required" type="text"
                   placeholder="请填写注册用户名，1-20位任意字符" maxlength="20"/>
            <span class="form_hint">用户名格式：1-20位任意字符</span>
        </li>
        <li>
            <label for="reg_password">密码：</label>
            <input id="reg_password" name="pw" required="required" type="password" pattern="[A-z0-9]{6,20}"
                   placeholder="请填写注册密码，6至20位英文字母或数字"/>
            <span class="form_hint">密码格式：6至20位英文字母或数字</span>
        </li>
        <li>
            <button type="submit" class="submit">注册</button>
        </li>
    </ul>
</form>
</body>
</html>

