<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.07.2019
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:bundle basename="${locale}" prefix="header.">
    <fmt:message key="trucking_company" var="trucking_company"/>
    <fmt:message key="welcome_message" var="welcome_message"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>header</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
</head>
<body>
<h1>${trucking_company}</h1>

<%@include file="part/locale_changer.jsp" %>

<h3>${welcome_message}</h3>
</body>
</html>
