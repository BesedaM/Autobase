<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.05.2019
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>AUTOBASE authentification</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<form name="LoginForm" method="post" action="controller">
    <input type="hidden" name="command" value="Login"/>
    <p>Enter your user's data</p><label>Login:
        <input name="login" type="text"/>
    </label><br/>
    <label>Password:
        <input name="password" type="password">
    </label><br/>
    <p class="error">${errorMessage}</p>
    <input type="submit" value="Log in"/>
</form>
<br/>
<div>
    <p>First time here?</p>
    <p><a>Register</a></p>
</div>

</body>
</html>
