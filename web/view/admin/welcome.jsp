<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.05.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
Welcome to ${role_name} page!
<p><a href="${pageContext.request.contextPath}/view/login.jsp">Logout</a></p>
</body>
</html>