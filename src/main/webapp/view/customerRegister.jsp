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

<p class="error_message">${errorRegister}</p>

<form action="RegisterServlet" method="POST">
    <p> Registration form </p>
<label>Enter login :  <input name="newLoginName" type="text"/></label>
<label>Enter password : <input name="newPassword" type="password"/></label>
    <br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>

