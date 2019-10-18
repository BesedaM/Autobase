<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.08.2019
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="urlPrefix" value="${pageContext.request.contextPath}" scope="request"/>

<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="cars_available" var="cars_available"/>
    <fmt:message key="car" var="car"/>
    <fmt:message key="busy_day" var="busy_day"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>Cars routes info</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>
<table class="available_cars_info">
    <caption>${cars_available}</caption>
    <tr>
        <td>${car}</td>
        <td>${busy_day}</td>
    </tr>
    <tr>
        <td>
            <form name="CarsInRoutes" method="post" action="${urlPrefix}/admin/cars/reload_cars_in_routes" onchange="submit()">
                <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
                <c:forEach items="${car_busy_dates_map}" var="car_dates">
                    <c:set var="car" value="${car_dates.key}"/>
                    <label>
                        <input type="radio" name="car_id" value="${car.id}" id="${car.id}"
                                <c:if test="${car_id==car.id}"> checked </c:if>
                        />${car.id}. ${car.model}</label>
                    <br/>
                </c:forEach>
            </form>
        </td>
        <td>
            <c:if test="${car_id!=null}">
                <table>
                    <c:forEach items="${car_busy_dates_map}" var="car_dates">
                        <c:if test="${car_dates.key.id==car_id}">
                            <c:set var="dates_set" value="${car_dates.value}" scope="request"/>
                        </c:if>
                    </c:forEach>

                    <c:forEach items="${dates_set}" var="date">
                        <tr>
                            <td>
                                <fmt:formatDate type="date" value="${date}" pattern="dd-MM-yyyy"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </td>
    </tr>
</table>

</body>

</html>