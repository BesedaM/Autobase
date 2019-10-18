<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2019
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var="urlPrefix" value="${pageContext.request.contextPath}" scope="page"/>
<c:set var="locale" value="${sessionScope.locale_file}" scope="session"/>
<c:set var="lang" value="${sessionScope.language}" scope="page"/>

<c:set var="car_busy_dates_map" value="${sessionScope.car_busy_dates}" scope="request"/>
<c:set var="route" value="${sessionScope.changing_route}" scope="request"/>
<c:set var="tasks" value="${route.getTasksList()}" scope="request"/>

<c:set var="change_car" value="${sessionScope.change_car}" scope="request"/>
<c:set var="task_to_change" value="${sessionScope.task_to_change}" scope="request"/>
<c:set var="add_task_flag" value="${sessionScope.add_task_flag}" scope="session"/>

<fmt:bundle basename="${locale}">
    <fmt:message key="colon" var="colon"/>
    <fmt:message key="dot" var="dot"/>
    <fmt:message key="required_sign" var="required_sign"/>
    <fmt:message key="submit" var="submit"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="task.">
    <fmt:message key="delete_task" var="delete"/>
    <fmt:message key="change_task" var="change"/>
    <fmt:message key="add_task" var="add_new_task"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="cars_on_route" var="cars_on_route"/>
    <fmt:message key="cars_selected_message" var="cars_selected_message"/>
    <fmt:message key="go_to_current_requests" var="go_to_current_requests"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="request.">
    <fmt:message key="change_car_list" var="change_car_list"/>
    <fmt:message key="cars_for_route" var="cars_for_route"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>Change route</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
    <script src="../../../javascript/js.js"></script>
</head>
<body>
<%@include file="../../header/registered_user.jsp" %>
<table class="main align_left">
    <tr>
        <th>${route.id}</th>
        <th colspan="4">${route.name}</th>
    </tr>
    <tr>
        <td colspan="2">${cars_on_route}</td>
        <td colspan="2">
            <c:forEach var="car" items="${route.getCarsList()}">
                ${car.id}${dot} ${car.model}&nbsp&nbsp&nbsp
            </c:forEach>

        </td>
        <td>
            <form name="CarListChanger" method="post" action="${urlPrefix}/admin/cars/change_cars_list" class="right_side">
                <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
                <input type="hidden" name="id" value="${route.id}">
                <input type="submit" value="${change_car_list}">
            </form>
        </td>
    </tr>
    <c:forEach var="task" items="${tasks}" varStatus="index">
        <tr>
            <td>${index.count}</td>
            <td><fmt:formatDate type="both" timeZone="UTC" value="${task.time.getTime()}"
                                pattern="dd-MM-yyyy HH:mm"/></td>
            <td>${task.address}</td>
            <td>${task.details}</td>
            <td>
                <form name="ProcessTask" method="post">
                    <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
                    <input type="hidden" name="id" value="${task.id}"/>
                    <button type="submit" formaction="/admin/tasks/delete_task">${delete}</button>
                    <button type="submit" formaction="/admin/tasks/change_task">${change}</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5">
            <form name="AddNewTask" method="post" action="${urlPrefix}/admin/tasks/add_task" class="right_side">
                <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
                <input type="hidden" name="route_id" value="${route.id}"/>
                <input type="submit" value="${add_new_task}"/>
            </form>
        </td>
    </tr>
</table>
<br/>

<form name="CurrentsRequestsRedirect" method="post" action="${urlPrefix}/admin/current_requests_redirect">
    <input type="hidden" name="command" value="current_requests_redirect">
    <input type="submit" value="${go_to_current_requests}"/>
</form>

<%--Add part for cars changing--%>
<c:if test="${change_car!=null}">
    <form name="ChangeCars" method="post" action="${urlPrefix}/admin/cars/change_cars" onsubmit="return requiredInCheckbox()">
        <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
        <h4>${cars_for_route}</h4>
        <c:forEach items="${car_busy_dates_map}" var="car_dates">
            <c:set var="car" value="${car_dates.key}"/>

            <label>
                <input class="cars" type="checkbox" name="cars_id" value="${car.id}" id="${car.id}"
                    <c:if test="${route.getCarsList().contains(car)}"> checked </c:if> />
                    ${car.id}${dot} ${car.model}
            </label><br/>
        </c:forEach>
        <p class="information">${cars_selected_message}</p>
        <p><input type="submit" value="${submit}"/></p>
    </form>

    <%@include file="../cars/cars_in_routes_info.jsp" %>
</c:if>


<%--Add part for task changing--%>
<c:if test="${task_to_change!=null||add_task_flag!=null}">

    <c:set var="task" value="${task_to_change}"/>

<form name="TaskChanger" method="post" action="${urlPrefix}/admin/tasks/process_task">
    <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
    <input type="hidden" name="route_id" value="${route.id}"/>
    <%@include file="../../route/part_task_form.jsp"%>
</form>

</c:if>
<%@include file="../../footer/footer.jsp" %>

</body>
</html>
