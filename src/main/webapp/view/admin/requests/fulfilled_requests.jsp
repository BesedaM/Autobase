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

<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="admin_main_redirect" var="admin_main_redirect"/>
    <fmt:message key="fulfilled_requests" var="fulfilled_requests"/>
    <fmt:message key="cars_on_route" var="cars"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="request.">
    <fmt:message key="id" var="request_id"/>
    <fmt:message key="date" var="request_date"/>
    <fmt:message key="customer_data" var="customer_data"/>
    <fmt:message key="delete_request" var="delete_request"/>
    <fmt:message key="change_route" var="change_route"/>
</fmt:bundle>

<fmt:bundle basename="${locale}">
    <fmt:message key="colon" var="colon"/>
    <fmt:message key="dot" var="dot"/>
    <fmt:message key="hyphen" var="hyphen"/>
</fmt:bundle>

<c:set var="request_customer_map" value="${sessionScope.request_customer_map}" scope="session"/>

<html lang="${lang}">
<head>
    <title>Fulfilled requests</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>
<%@include file="../../header/registered_user.jsp" %>


<table class="main">
    <tr>
        <th>${fulfilled_requests}</th>
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
                    <c:set var="route" value="${request.route}"/>
                    <tr>
                        <td colspan="3"></td>
                    </tr>
                    <tr>
                        <td>${request.id}</td>
                        <td><fmt:formatDate type="date" value="${request.creationTime.getTime()}"
                                            pattern="dd-MM-yyyy"/></td>
                        <td> ${customer.id}${dot}
                            <c:if test="${customer.customerType=='юр.лицо'}">
                                ${customer.companyName}<br/>
                            </c:if>
                                ${customer.name} ${customer.surname}
                        </td>
                    </tr>

                    <tr>
                        <td colspan="3" class="left_side">
                                ${cars}${colon}&nbsp
                            <c:forEach var="car" items="${route.getCarsList()}">
                                ${car.id}${dot} ${car.model}&nbsp&nbsp&nbsp
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <table class="inner">
                                <c:forEach var="task" items="${route.getTasksList()}" varStatus="index">
                                    <tr>
                                        <td class="borders left_side">${index.count}</td>
                                        <td class="borders left_side"><fmt:formatDate type="both"
                                                                                      value="${task.time.getTime()}"
                                                                                      pattern="dd-MM-yyyy HH:mm"/></td>
                                        <td class="borders left_side">${task.address}</td>
                                        <td class="borders left_side">${task.details}</td>
                                    </tr>
                                </c:forEach>
                            </table>
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

<%@include file="../../footer/footer.jsp" %>

</body>
</html>
