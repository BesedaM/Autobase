<%--@elvariable id="errorMessage" type="java.lang.String"--%>
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
<%--   <script type="text/javascript" src="../javascript/register.js"></script>--%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<form name="LoginForm" method="post" action="controller">
    <input type="hidden" name="command" value="login"/>
    <p>Enter your user's data</p>

    <table>
        <tr>
            <td>
                <label for="login">Login:</label>
            </td>
            <td>
                <input id="login" name="login" type="text" pattern="^[\w]{4,}$"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">Password:</label>
            </td>
            <td>
                <input id="password" name="password" type="password" pattern="^[\w]{4,}$">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p class="error-message">${errorMessage}</p>
            </td>
        </tr>
    </table>
    <input type="submit" value="Log in"/>
</form>
<br/>
<p>First time here?</p>
<a href="${pageContext.request.contextPath}/view/register/customer.jsp"><input type="button" value="Register"/></a>

</body>
</html>
