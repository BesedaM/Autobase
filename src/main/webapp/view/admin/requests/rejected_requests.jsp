<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.08.2019
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var="urlPrefix" value="${pageContext.request.contextPath}" scope="page"/>
<c:set var="locale" value="${sessionScope.locale_file}" scope="session"/>
<c:set var="lang" value="${sessionScope.language}" scope="page"/>

<c:set var="request_customer_map" value="${sessionScope.request_customer_map}" scope="session"/>

<fmt:bundle basename="${locale}" prefix="request.">
    <fmt:message key="id" var="request_id"/>
    <fmt:message key="date" var="request_date"/>
    <fmt:message key="customer_data" var="customer_data"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="rejected_requests" var="rejected_requests"/>
    <fmt:message key="admin_main_redirect" var="admin_main_redirect"/>
</fmt:bundle>

<fmt:bundle basename="${locale}">
    <fmt:message key="dot" var="dot"/>
</fmt:bundle>

<html lang="${lang}">
<head>
    <title>Rejected requests</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>
<%@include file="../../header/registered_user.jsp" %>

<table class="main">
    <tr>
        <th>${rejected_requests}</th>
    </tr>

    <tr>
        <td>
            <table class="main">
                <tr>
                    <th>${request_id}</th>
                    <th>${request_date}</th>
                    <th>${customer_data}</th>
                </tr>
                <c:forEach items="${request_customer_map}" var="request_customer">
                    <c:set var="request" value="${request_customer.key}"/>
                    <c:set var="customer" value="${request_customer.value}"/>
                    <tr>
                        <td colspan="3"></td>
                    </tr>
                    <tr>
                        <td>${request.id}</td>
                        <td><fmt:formatDate type="date" value="${request.creationTime.getTime()}"
                                            pattern="dd-MM-yyyy"/></td>
                        <td>${customer.id}${dot}
                            <c:if test="${customer.customerType=='юр.лицо'}">
                                ${customer.companyName}<br/>
                            </c:if>
                                ${customer.name} ${customer.surname}
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <form name="AdminMainRedirect" method="post" action="${urlPrefix}/controller">
                <input type="hidden" name="command" value="admin_main_redirect"/>
                <input type="submit" value="${admin_main_redirect}"/>
            </form>
        </td>
    </tr>
</table>

</body>
</html>
