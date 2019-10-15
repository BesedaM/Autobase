<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.08.2019
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var="urlPrefix" value="${pageContext.request.contextPath}" scope="page"/>
<c:set var="locale" value="${sessionScope.locale_file}" scope="session"/>
<c:set var="lang" value="${sessionScope.language}" scope="page"/>

<c:set var="request_text" value="${sessionScope.new_request_text}" scope="page"/>
<c:set var="car_busy_dates_map" value="${sessionScope.car_busy_dates}" scope="session"/>
<c:set var="car_id" value="${requestScope.car_id}" scope="request"/>
<c:set var="tasks" value="${sessionScope.task_list}" scope="request"/>
<c:set var="cars" value="${sessionScope.cars_list}" scope="request"/>
<c:set var="id" value="${sessionScope.new_request_id}" scope="session"/>
<c:set var="current_route" value="${sessionScope.current_route}" scope="session"/>

<fmt:bundle basename="${locale}" prefix="request.">
    <fmt:message key="route_name" var="route_name"/>
    <fmt:message key="add_route" var="add_route"/>
    <fmt:message key="car_list" var="car_list"/>
    <fmt:message key="task_list" var="task_list"/>
    <fmt:message key="content" var="request_content"/>
    <fmt:message key="enter_route_name" var="enter_route_name"/>
    <fmt:message key="cars_for_route" var="cars_for_route"/>
    <fmt:message key="request_id" var="request_id"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="task.">
    <fmt:message key="date" var="date"/>
    <fmt:message key="time" var="time"/>
    <fmt:message key="country" var="country"/>
    <fmt:message key="district" var="district"/>
    <fmt:message key="city" var="city"/>
    <fmt:message key="street" var="street"/>
    <fmt:message key="house" var="house"/>
    <fmt:message key="building" var="building"/>
    <fmt:message key="add_task" var="add_task"/>
    <fmt:message key="reset" var="reset"/>
    <fmt:message key="details" var="details"/>
    <fmt:message key="required_info" var="required_info"/>
    <fmt:message key="new_task" var="new_task"/>
</fmt:bundle>

<fmt:bundle basename="${locale}">
    <fmt:message key="colon" var="colon"/>
    <fmt:message key="required_sign" var="required_sign"/>
    <fmt:message key="submit" var="submit"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="new_requests_page" var="new_requests_page"/>
    <fmt:message key="cars_selected_message" var="cars_selected_message"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>Add new route</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
    <script src="../../../javascript/js.js"></script>

</head>
<body>
<%@include file="../../header/registered_user.jsp" %>

<p class="small_header">${request_id}${id}</p>
<p class="small_header">${request_content}${colon}</p>
<p>${request_text}</p>

<c:if test="${current_route==null}">
    <form name="NewRoute" method="post" action="${urlPrefix}/controller" onsubmit="return requiredInCheckbox()">
        <input type="hidden" name="command" value="add_new_route"/>
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
        <fieldset>
            <legend>${add_route}</legend>
            <table class="for_form">
                <tr>
                    <td><label for="route_name">${route_name}${colon}</label></td>
                    <td><input type="text" name="route_name" id="route_name" required
                               placeholder="${enter_route_name}"/>
                    </td>
                </tr>
            </table>

            <h4>${cars_for_route}</h4>
            <c:forEach items="${car_busy_dates_map}" var="car_dates">
                <c:set var="car" value="${car_dates.key}"/>

                <label>
                    <input class="cars" type="checkbox" name="cars_id" value="${car.id}" id="${car.id}"/>
                        ${car.id}. ${car.model}
                </label><br/>
            </c:forEach>
            <p class="information">${cars_selected_message}</p>
            <p><input type="submit" value="${submit}"/></p>

        </fieldset>

    </form>

    <table>
        <tr>
            <td class="information">
                <%@include file="../cars/cars_in_routes_info.jsp" %>
            </td>
        </tr>
    </table>
</c:if>

<c:if test="${current_route!=null}">

    <form name="NewTask" method="post" action="${urlPrefix}/controller">
        <input type="hidden" name="command" value="add_change_task"/>
        <input type="hidden" name="route_id" value="${id}"/>
        <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
        <fieldset>
            <legend>${task_list}</legend>
            <c:if test="${fn:length(tasks)>0}">
                <table>
                    <c:forEach var="task" items="${tasks}" varStatus="index">
                        <tr>
                            <td class="borders left_side">${index.count}</td>
                            <td class="borders left_side"><fmt:formatDate type="both" value="${task.time.getTime()}"
                                                pattern="dd-MM-yyyy HH:mm"/></td>
                            <td class="borders left_side">${task.address}</td>
                            <td class="borders left_side">${task.details}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </fieldset>

        <fieldset>
            <legend>${new_task}</legend>
            <%@include file="../../route/part_task_form.jsp"%>
        </fieldset>
    </form>
</c:if>

<form name="NewRequestsRedirect" method="post" action="${urlPrefix}/admin/new_requests">
    <input type="submit" value="${new_requests_page}"/>
</form>

<%@include file="../../footer/footer.jsp" %>
</body>
</html>
