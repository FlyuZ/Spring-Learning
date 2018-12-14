<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>登录成功</title>
</head>
<body>
<p>登录成功</p>
<c:forEach items="${userList}" var="user">
    <table style="border: 1px solid black">
        <tr>
            <th align="center">Username:${user.user}</th>
            <th align="center">Password:${user.pw}</th>
            <th><input type="button" value="delete" onclick="del(${user.user})"/></th>
        </tr>
    </table>
</c:forEach>
</body>
</html>
