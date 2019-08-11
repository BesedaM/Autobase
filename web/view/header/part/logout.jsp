<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.07.2019
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--<c:set var="urlPrefix" value="${pageContext.request.contextPath}"/>--%>
<%--<c:set var="locale" value="${sessionScope.locale}"/>--%>

<fmt:bundle basename="${locale}" prefix="footer.">
    <fmt:message key="logout" var="logout"/>
</fmt:bundle>

<html>
<head>
    <title>logout</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>
<form name="Logout" method="post" action="${urlPrefix}/controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="${logout}">
</form>
</body>
</html>
