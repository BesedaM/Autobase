<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.07.2019
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="lang" value="${sessionScope.language}"/>

<fmt:bundle basename="${locale}" prefix="header.">
    <fmt:message key="change_language" var="change_language"/>
    <fmt:message key="locale.en" var="english"/>
    <fmt:message key="locale.ru" var="russian"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>Locale</title>
    <link rel="stylesheet" href="../../css/styles.css" type="text/css"/>
</head>
<body>
 <form name="LocaleChanger" method="post" action="${urlPrefix}/change_locale">
        <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
        <label for="language_select">${change_language}</label>
        <select id="language_select" name="language_select"  onchange='this.form.submit()'>
            <option value="en"
                    <c:if test="${lang=='en'}">selected</c:if>
            >${english}</option>
            <option value="ru"
                    <c:if test="${lang=='ru'}">selected</c:if>
            >${russian}</option>
        </select>
    </form>
</body>
</html>
