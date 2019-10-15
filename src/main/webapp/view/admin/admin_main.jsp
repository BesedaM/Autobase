<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.08.2019
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="urlPrefix" value="${pageContext.request.contextPath}"/>
<c:set var="locale" value="${sessionScope.locale_file}" scope="session"/>
<c:set var="lang" value="${sessionScope.language}" scope="page"/>

<c:set var="request_list" value="${sessionScope.request_list}" scope="request"/>


<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="new_requests" var="new_requests"/>
    <fmt:message key="current_requests" var="current_requests"/>
    <fmt:message key="fulfilled_requests" var="fulfilled_requests"/>
    <fmt:message key="rejected_requests" var="rejected_requests"/>
    <fmt:message key="car_list" var="car_list"/>
    <fmt:message key="driver_list" var="driver_list"/>
    <fmt:message key="client_list" var="client_list"/>
    <fmt:message key="requests" var="requests"/>
    <fmt:message key="menu" var="menu"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>Admin main</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>
<%@include file="../header/registered_user.jsp" %>

<form name="AdminMenu" method="post">
    <table class="menu">
        <tr>
            <td class="header" colspan="2">
                ${menu}
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" formaction="/admin/new_requests">${new_requests}</button>
            </td>
            <td></td>
        </tr>
        <tr>
            <td>
                <button type="submit" formaction="/admin/current_requests">${current_requests}</button>
            </td>
            <td>
                <button type="submit" name="command" value="car_list_redirect">${car_list}</button>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" formaction="/admin/fulfilled_requests">${fulfilled_requests}</button>
            </td>
            <td>
                <button type="submit" name="command" value="driver_list_redirect">${driver_list}</button>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" formaction="/admin/rejected_requests">${rejected_requests}</button>
            </td>
            <td>
                <button type="submit" name="command" value="client_list_redirect">${client_list}</button>
            </td>
        </tr>
    </table>


</form>
<%@include file="../footer/footer.jsp" %>

</body>
</html>
