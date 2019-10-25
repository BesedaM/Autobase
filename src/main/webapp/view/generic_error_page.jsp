<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.05.2019
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="urlPrefix" value="${pageContext.request.contextPath}" scope="page"/>

<html>
<head>
    <title>Error page</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>

<p>Exception: ${exception.message}</p>

<form name="LoginRedirect" method="get" action="${urlPrefix}/login">
    <input type="submit" value="Return to login"/>
</form>

</body>
</html>
