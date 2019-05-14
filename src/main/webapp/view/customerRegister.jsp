<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.05.2019
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p><font color="red">${errorRegister}</font></p>

<form action="RegisterServlet" method="POST">
    <p> Registration form </p>
    <p> Enter login : <input name="newLoginName" customerType="text"/>
    </p>
    <p> Enter password : <input name="newPassword" customerType="password"/>
    </p>
    <input customerType="submit" value="Register"/>

</form>
</body>
</html>

