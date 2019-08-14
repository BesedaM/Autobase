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
    <fmt:message key="menu" var="menu"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>Admin main</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>
<%@include file="../header/registered_user.jsp" %>

<p>${menu}</p>

<form name="AdminMenu" method="get" action="${urlPrefix}/controller">
    <button type="submit" name="command" value="new_requests_redirect">${new_requests}</button>
    <button type="submit" name="command" value="current_requests_redirect">${current_requests}</button>
    <button type="submit" name="command" value="fulfilled_requests_redirect">${fulfilled_requests}</button>
    <button type="submit" name="command" value="rejected_requests_redirect">${rejected_requests}</button>
    <button type="submit" name="command" value="car_list_redirect">${car_list}</button>
    <button type="submit" name="command" value="driver_list_redirect">${driver_list}</button>
    <button type="submit" name="command" value="client_list_redirect">${client_list}</button>
</form>





<%--<h1>${current_requests}</h1>--%>
<%--<table class="main">--%>
    <%--<tr>--%>
        <%--<th>${request_id}</th>--%>
        <%--<th>${request_date}</th>--%>
        <%--<th>${request_status}</th>--%>
        <%--<th></th>--%>
    <%--</tr>--%>
    <%--<c:forEach items="${request_list}" var="request">--%>
        <%--<c:set var="route" value="${request.getRoute()}"/>--%>
        <%--<tr><td colspan="4"></td></tr>--%>
        <%--<tr>--%>
            <%--<td>${request.id}</td>--%>
            <%--<td><fmt:formatDate type="both" timeZone="UTC" value="${request.creationTime.getTime()}"--%>
                                <%--pattern="dd-MM-yyyy HH:mm:ss"/></td>--%>
            <%--<td>${request.status}</td>--%>
            <%--<td>--%>
                <%--<c:if test="${request.status=='отклонена'}">--%>
                    <%--<form class="right_side" name="DeleteRequest" method="post" action="${urlPrefix}/controller">--%>
                        <%--<input type="hidden" name="command" value="delete_request"/>--%>
                        <%--<input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>--%>
                        <%--<input type="hidden" name="id" value="${request.id}"/>--%>
                        <%--<input type="submit" value="${delete}"/>--%>
                    <%--</form>--%>
                <%--</c:if>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<c:if test="${route!=null}">--%>
            <%--<c:set var="drivers" value="${driver_map[request.id]}" scope="request"/>--%>
            <%--<c:set var="cars" value="${route.getCarsList()}" scope="request"/>--%>
            <%--<tr>--%>
                <%--<td colspan="4" class="small_header">--%>
                        <%--${route_description}--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<%@include file="../route/route_info.jsp" %>--%>
            <%--<tr>--%>
                <%--<td colspan="4">--%>
                    <%--<c:forEach items="${drivers}" var="driver">--%>
                        <%--${your_driver}${colon} ${driver.name} ${driver.surname} ${phone} ${driver.phone}<br/>--%>
                    <%--</c:forEach>--%>
                    <%--<c:forEach items="${cars}" var="car">--%>
                        <%--${your_car_number}${colon} ${car.number}--%>
                        <%--<c:if test="${fn:length(drivers)>1}">--%>
                            <%--<br/>--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>
                    <%--<p class="information">${message_on_drivers}</p>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:if>--%>
    <%--</c:forEach>--%>
    <%--<c:if test="${fn:length(request_list)==0}">--%>
        <%--<p class="information">${no_active_requests}</p>--%>
    <%--</c:if>--%>
<%--</table>--%>


<%--<br/>--%>
<%--<h1>${leave_request}</h1>--%>
<%--<c:if test="${sessionScope.new_request==null}">--%>
    <%--<form name="AddNewRequest" method="post" action="${urlPrefix}/controller">--%>
        <%--<input type="hidden" name="command" value="add_request"/>--%>
        <%--<input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>--%>
        <%--<p>${new_request_info}</p>--%>
        <%--<textarea id="new_request" name="new_request_text" cols="100" rows="10" maxlength="500" required></textarea>--%>
        <%--<p class="information">${message_on_request_text}</p>--%>
        <%--<input type="submit" value="${send}"/>--%>
    <%--</form>--%>
<%--</c:if>--%>

<%--<c:if test="${sessionScope.new_request!=null}">--%>
    <%--<c:set var="new_request" value="${sessionScope.new_request}"/>--%>
    <%--<p class="important_info">${new_request_id_message}--%>
        <%--<span>${new_request.id}</span> ${from}--%>
        <%--<span><fmt:formatDate type="both" timeZone="UTC" value="${new_request.creationTime.getTime()}"--%>
                              <%--pattern="dd-MM-yyyy"/></span><br/>--%>
            <%--${new_request_message}</p>--%>
    <%--<p class="information" id="msg"></p>--%>
    <%--<br/>--%>

    <%--<form name="AddAnotherRequest" method="get" action="${urlPrefix}/controller">--%>
        <%--<input type="hidden" name="command" value="update_customer_request_list"/>--%>
        <%--<input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>--%>
        <%--<input type="submit" value="${add_another_request}"/>--%>
    <%--</form>--%>
<%--</c:if>--%>


</body>
</html>
