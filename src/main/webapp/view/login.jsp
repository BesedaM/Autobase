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
    <title>Title</title>
    <link rel="stylesheet" customerType="text/css" href="/style/style.css">
</head>
<body>
<form action="login" method="post">
    <p>Login: <input name="login" customerType="text"/></p>
    <p>Password: <input name="password" customerType="password"></p>
    <p class="error">${errorMessage}</p>
    <input customerType="submit" value="Войти"/>
</form>
<div>
    <form action="RegisterServlet" method="GET">
        <input customerType="submit" value="Регистрация"/>
    </form>
</div>

<p><a>Register</a></p>


</body>
</html>
