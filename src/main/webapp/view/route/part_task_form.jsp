<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.09.2019
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


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
    <fmt:message key="change_task" var="change_task"/>
</fmt:bundle>

<table class="for_form">
    <tr>
        <td><label for="date">${date}${required_sign}${colon}</label></td>
        <td><input type="date" name="date" id="date"
                <c:if test="${task_to_change!=null}">
                    value="<fmt:formatDate type="both" value="${task.time.getTime()}" timeZone="UTC"
                                           pattern="yyyy-MM-dd"/>" </c:if>
                   required/></td>
    </tr>
    <tr>
        <td><label for="time">${time}${required_sign}${colon}</label></td>
        <td><input type="time" name="time" id="time"
                <c:if test="${task_to_change!=null}">
                    value="<fmt:formatDate type="both" value="${task.time.getTime()}" timeZone="UTC"
                                           pattern="HH:mm"/>" </c:if>
                   required/></td>
    </tr>
    <tr>
        <td><label for="country">${country}${colon}</label></td>
        <td><input type="text" name="country" id="country"
                <c:if test="${task_to_change!=null}"> value="${task.address.country}" </c:if>
                   <c:if test="${task_to_change==null}">value="Беларусь"</c:if> /></td>
    </tr>
    <tr>
        <td><label for="district">${district}${colon}</label></td>
        <td><input type="text" name="district" id="district"
                <c:if test="${task_to_change!=null}"> value="${task.address.district}" </c:if>
                   <c:if test="${task_to_change==null}">value="Минский"</c:if> /></td>
    </tr>
    <tr>
        <td><label for="city">${city}${required_sign}${colon}</label></td>
        <td><input type="text" name="city" id="city"
                <c:if test="${task_to_change!=null}"> value="${task.address.city}" </c:if>
                   required/></td>
    </tr>
    <tr>
        <td><label for="street">${street}${required_sign}${colon}</label></td>
        <td><input type="text" name="street" id="street"
                <c:if test="${task_to_change!=null}"> value="${fn:escapeXml(task.address.street)}" </c:if>
                   required/></td>
    </tr>
    <tr>
        <td><label for="house">${house}${colon}</label></td>
        <td><input type="text" name="house" id="house"
                <c:if test="${task_to_change!=null&&task.address.house!=0}"> value="${task.address.house}" </c:if>
                   pattern="^[0-9]{0,5}$"/></td>
    </tr>
    <tr>
        <td><label for="building">${building}${colon}</label></td>
        <td><input type="text" name="building" id="building"
                <c:if test="${task_to_change!=null&&task.address.building!='-'}"> value="${task.address.building}" </c:if>
        /></td>
    </tr>
    <tr>
        <td><label for="details">${details}${required_sign}${colon}</label></td>
        <td><input type="text" name="details" id="details"
                <c:if test="${task_to_change!=null}"> value="${task.details}" </c:if>
        /></td>
    </tr>

    <tr>
        <td colspan="2"><input type="submit"
                <c:if test="${task_to_change!=null}">
                    value="${change_task}"
                </c:if>
                <c:if test="${task_to_change==null}">
                    value="${add_task}"
                </c:if>
        />
            <input type="reset" value="${reset}"/></td>
    </tr>
</table>
<p class="information">${required_sign}${required_info}</p>
