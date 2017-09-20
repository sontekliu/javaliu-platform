<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
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
<%=request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME)%>
</body>
</html>
