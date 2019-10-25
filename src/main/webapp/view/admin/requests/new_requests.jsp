<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.08.2019
  Time: 22:38
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
<c:set var="car_list" value="${sessionScope.user}" scope="session"/>
<c:set var="car_busy_dates_map" value="${sessionScope.car_busy_dates}" scope="session"/>
<c:set var="car_id" value="${requestScope.car_id}"/>

<fmt:bundle basename="${locale}" prefix="request.">
    <fmt:message key="id" var="request_id"/>
    <fmt:message key="date" var="request_date"/>
    <fmt:message key="customer_data" var="customer_data"/>
    <fmt:message key="adopt" var="adopt"/>
    <fmt:message key="reject" var="reject"/>
    <fmt:message key="add_route" var="add_route"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="new_requests" var="new_requests"/>
    <fmt:message key="car_info" var="car_info"/>
    <fmt:message key="admin_main_redirect" var="admin_main_redirect"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>New routes</title>
    <link rel="stylesheet" href="static/css/style.css" type="text/css"/>
</head>
<body>
<%@include file="../../header/registered_user.jsp" %>

<table class="main">
    <tr>
        <th>${new_requests}</th>
        <th>${car_info}</th>
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
                        <td>
                            <c:if test="${customer.customerType=='юр.лицо'}">
                                ${customer.companyName}<br/>
                            </c:if>
                                ${customer.name} ${customer.surname}
                        </td>
                    </tr>
                    <c:if test="${request.status=='рассматривается'}">
                        <tr>
                            <td colspan="3">${request.comment}</td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <form class="right_side" name="ProcessRequest" method="post"
                                      action="${urlPrefix}/admin/new_requests/process_request">
                                    <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
                                    <input type="hidden" name="id" value="${request.id}"/>
                                    <button type="submit" name="status" value="принята">${adopt}</button>
                                    <button type="submit" name="status" value="отклонена">${reject}</button>
                                </form>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${request.status=='принята'}">
                        <tr>
                            <td colspan="3">
                                <form class="right_side" name="CreateRoute" method="post"
                                      action="${urlPrefix}/admin/create_route">
                                    <input type="hidden" name="customer_id" value="${customer.id}"/>
                                    <input type="hidden" name="request_text" value="${request.comment}"/>
                                    <input type="hidden" name="id" value="${request.id}"/>
                                    <input type="submit" value="${add_route}"/>
                                </form>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <br/>
            <form name="AdminMainRedirect" method="post" action="${urlPrefix}/admin/admin_main">
                <input type="submit" value="${admin_main_redirect}"/>
            </form>
        </td>

        <td>
            <%@include file="../cars/cars_in_routes_info.jsp" %>
        </td>
    </tr>
</table>

<%@include file="../../footer/footer.jsp" %>
</body>
</html>

