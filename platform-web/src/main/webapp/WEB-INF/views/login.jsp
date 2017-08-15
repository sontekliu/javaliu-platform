<%--
  Created by IntelliJ IDEA.
  User: sontek
  Date: 2017/8/15
  Time: 下午11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<form action="<%=path%>/login" method="post">
    <input type="text" name="username"> <br>
    <input type="password" name="password"> <br>
    <input type="submit" name="submit" value="登陆"> <br>
</form>
</body>
</html>
