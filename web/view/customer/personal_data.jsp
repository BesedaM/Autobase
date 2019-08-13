<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.08.2019
  Time: 7:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="urlPrefix" value="${pageContext.request.contextPath}"/>
<c:set var="locale" value="${sessionScope.locale_file}" scope="session"/>
<c:set var="lang" value="${sessionScope.language}" scope="page"/>

<fmt:bundle basename="${locale}" prefix="registration.">
    <fmt:message key="colon" var="colon"/>
    <fmt:message key="user.password" var="password"/>
    <fmt:message key="user.password_requirements" var="password_requirements"/>
    <fmt:message key="user.password_confirm" var="password_confirm"/>
    <fmt:message key="company.legend" var="company_legend"/>
    <fmt:message key="company.name" var="company_name"/>
    <fmt:message key="user.personal_data" var="personal_data"/>
    <fmt:message key="user.name" var="user_name"/>
    <fmt:message key="user.surname" var="user_surname"/>
    <fmt:message key="user.phone_number" var="phone"/>
    <fmt:message key="user.email" var="email"/>
    <fmt:message key="company.info_message" var="company_info"/>

</fmt:bundle>


<fmt:bundle basename="${locale}" prefix="customer.">
    <fmt:message key="change_password" var="change_password"/>
    <fmt:message key="new_password" var="new_password"/>
    <fmt:message key="change_data" var="change_data"/>
    <fmt:message key="back_to_customer_main" var="back_to_customer_main"/>
    <fmt:message key="password_change_secceed" var="password_change_secceed"/>
    <fmt:message key="personal_data_changed" var="data_changed"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="error.">
    <fmt:message key="password_mismatch" var="password_mismatch"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>Personal data</title>
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>
<%@include file="../header/registered_user.jsp" %>

<form name="CustomerPasswordChanger" action="${urlPrefix}/controller" method="POST">
    <input type="hidden" name="command" value="change_customer_password"/>
    <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
    <fieldset>
        <legend>${change_password}</legend>
        <table class="registration">
            <tr>
                <td>
                    <label for="new_password">${new_password}${colon}</label>
                </td>
                <td>
                    <input id="new_password" name="new_password" type="password" required/>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>
                    <label for="passw1">${password}${colon}</label>
                </td>
                <td>
                    <input id="passw1" name="password" type="password" required/>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>
                    <label for="passw2">${password_confirm}${colon}</label>
                </td>
                <td>
                    <input id="passw2" name="password_confirm" type="password" required/>
                </td>
                <td></td>
            </tr>
            <c:if test="${requestScope.errorMessage!=null}">
                <tr>
                    <td colspan="2">
                        <p class="error-message">${password_mismatch}. <br/>${password_requirements}</p>
                    </td>
                    <td></td>
                </tr>
            </c:if>
            <c:if test="${requestScope.passwordChanged}">
                <tr>
                    <td colspan="2">
                        <p class="succeed">${password_change_secceed}</p>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td colspan="3">
                    <br/>
                    <p class="information">${password_requirements}</p>
                </td>
            </tr>
        </table>
        <input type="submit" value="${change_password}">
    </fieldset>
</form>


<form name="CustomerData" action="${urlPrefix}/controller" method="POST">
    <input type="hidden" name="command" value="change_customer_data"/>
    <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>

    <c:if test="${user.customerType=='юр.лицо'}">
        <fieldset>
            <legend>${company_legend}</legend>
            <table class="registration">
                <tr>
                    <td class="registration">
                        <label for="company">${company_name}${colon}</label>
                    </td>
                    <td>
                        <input id="company" name="company_name" type="text" placeholder="${user.getCompanyName()}"/>
                    </td>
                    <td></td>
                </tr>
            </table>
        </fieldset>
    </c:if>

    <fieldset>
        <legend>${personal_data}</legend>
        <table>
            <tr>
                <td class="registration">
                    <label for="name">${user_name}${colon}</label>
                </td>
                <td>
                    <input id="name" name="name" type="text" pattern="(^[a-zA-Z-]{3,}$)|(^[А-Яа-я-]{3,}$)"
                           placeholder="${user.name}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="surname">${user_surname}${colon}</label>
                </td>
                <td>
                    <input id="surname" name="surname" type="text" pattern="(^[a-zA-Z-]{3,}$)|(^[А-Яа-я-]{3,}$)"
                           placeholder="${user.surname}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="phone">${phone}${colon}</label>
                </td>
                <td>
                    <input id="phone" name="phone" type="text" placeholder="${user.phone}"
                           pattern="^\(((29)|(33)|(44)|(25))\)[0-9]{3}-[0-9]{2}-[0-9]{2}(\s){0,}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email">${email}${colon}</label>
                </td>
                <td>
                    <input id="email" name="email" type="email" placeholder="${user.email}"
                           pattern="([^\r\n\t\f\v\@]){2,}\@{1}[\w-]{2,}\.{1}[a-z]{2,}"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <br/>
    <c:if test="${requestScope.dataChanged!=null}">
        <tr>
            <td colspan="2">
                <p class="succeed">${data_changed}</p>
            </td>
        </tr>
    </c:if>
    <input type="submit" value="${change_data}"/>
</form>

<form name="GoToCustomerMain" action="${urlPrefix}/controller" method="get">
    <input type="hidden" name="command" value="customer_main_page"/>
    <input type="submit" value="${back_to_customer_main}"/>
</form>

</body>
</html>
