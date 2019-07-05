<%--@elvariable id="errorMessage" type="java.lang.String"--%>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.05.2019
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:set var="urlPrefix" value="${pageContext.request.contextPath}"/>

<%--<fmt:bundle basename="locale">--%>
    <%--<fmt:message key="local.adminPage.setScore" var="setScore"/>--%>
    <%--<fmt:message key="local.adminPage.createOdd" var="craeteOdd"/>--%>
    <%--<fmt:message key="local.adminPage.createEvent" var="createEvent"/>--%>
    <%--<fmt:message key="local.adminPage.createEntryLabel" var="entryAddedSuccesfull"/>--%>
<%--</fmt:bundle>--%>

<html>
<head>
    <title>AUTOBASE authentification</title>
    <meta http-equiv="Content-Type" content="text/html">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="<c:url value="${urlPrefix}/css/styles.css"/>" type="text/css"/>
    <%--<style>--%>
        <%--@import "${urlPrefix}/css/styles.css";--%>
    <%--</style>--%>
</head>
<body>
<form name="LoginForm" method="post" action="${urlPrefix}/controller">         <%--  ${pageContext.request.contextPath}/ --%>
    <input type="hidden" name="command" value="login"/>

    <p>Enter your user's data</p>
    <table>
        <tr>
            <td>
                <label for="login">Login:</label>
            </td>
            <td>
                <input id="login" name="login" type="text" required/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">Password:</label>
            </td>
            <td>
                <input id="password" name="password" type="password" required/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p id="login_error-message">${errorMessage}</p>
            </td>
        </tr>
    </table>
    <input type="submit" value="Log in"/>
</form>
<br/>
<p>First time here?</p>
<form name="CustomerRegisterRedirect" method="get" action="${urlPrefix}/controller">
    <input type="hidden" name="command" value="customer_register_page">
    <input type="submit" value="Register"/>
</form>

</body>
</html>
