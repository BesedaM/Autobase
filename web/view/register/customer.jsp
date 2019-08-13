<%--@elvariable id="errorRegisterPassword" type="java.lang.String"--%>
<%--@elvariable id="errorRegisterLogin" type="java.lang.String"--%>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.05.2019
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="urlPrefix" value="${pageContext.request.contextPath}"/>
<c:set var="locale" value="${sessionScope.locale_file}" scope="session"/>
<c:set var="lang" value="${sessionScope.language}" scope="page"/>

<fmt:bundle basename="${locale}" prefix="registration.">
    <fmt:message key="colon" var="colon"/>
    <fmt:message key="customer.title" var="title"/>
    <fmt:message key="user.form" var="user_form"/>
    <fmt:message key="user.login" var="login"/>
    <fmt:message key="user.login_requirements" var="login_requirements"/>
    <fmt:message key="user.password" var="password"/>
    <fmt:message key="user.password_requirements" var="password_requirements"/>
    <fmt:message key="user.password_confirm" var="password_confirm"/>
    <fmt:message key="company.legend" var="company_legend"/>
    <fmt:message key="company.name" var="company_name"/>
    <fmt:message key="company.info_message" var="company_info"/>
    <fmt:message key="user.personal_data" var="personal_data"/>
    <fmt:message key="user.name" var="user_name"/>
    <fmt:message key="user.surname" var="user_surname"/>
    <fmt:message key="user.phone_number" var="phone"/>
    <fmt:message key="user.email" var="email"/>
    <fmt:message key="company.info_message" var="company_info"/>
    <fmt:message key="form.submit" var="submit"/>
    <fmt:message key="form.reset" var="reset"/>
    <fmt:message key="redirect.back_to_login" var="back_to_login"/>
</fmt:bundle>


<fmt:bundle basename="${locale}" prefix="error.">
    <fmt:message key="login_exists" var="login_exists"/>
    <fmt:message key="password_mismatch" var="password_mismatch"/>
</fmt:bundle>


<html lang="${lang}">
<head>
    <title>${title}</title>
    <meta http-equiv="Content-Type" content="text/html">
    <link rel="stylesheet" type="text/css" href="${urlPrefix}/css/styles.css">
</head>
<body>

<%@include file="../header/non_registered_user.jsp" %>

<form name="customerRegister" action="${urlPrefix}/controller" method="POST">
    <input type="hidden" name="command" value="customer_register"/>
    <fieldset>
        <legend>${user_form}</legend>
        <table class="registration">
            <tr>
                <td class="registration"><label for="login">${login}${colon}</label></td>
                <td>
                    <input id="login" name="login" type="text" pattern="^[\w]{8,}$" required/>
                </td>
                <td></td>
            </tr>

            <c:if test="${errorRegisterLogin!=null}">
                <tr>
                    <td colspan="2"><p class="error-message">${login_exists}</p></td>
                    <td></td>
                </tr>
            </c:if>
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
            <c:if test="${errorRegisterPassword!=null}">
                <tr>
                    <td colspan="2">
                        <p class="error-message">${password_mismatch}</p>
                    </td>
                    <td></td>
                </tr>
            </c:if>
            <c:if test="${unsafePassword!=null}">
                <tr>
                    <td colspan="2">
                        <p class="error-message">${password_requirements}</p>
                    </td>
                    <td></td>
                </tr>
            </c:if>
            <tr>
                <td colspan="3">
                    <br/>
                    <p class="information">${login_requirements}</p>
                    <p class="information">${password_requirements}</p>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset>
        <legend>${company_legend}</legend>
        <table class="registration">
            <tr>
                <td class="registration">
                    <label for="company">${company_name}${colon}</label>
                </td>
                <td>
                    <input id="company" name="company_name" type="text"/>
                </td>
                <td></td>
            </tr>
            <tr>
                <td colspan="2">
                    <p class="information">${company_info}</p>
                </td>
                <td></td>
            </tr>
        </table>
    </fieldset>
    <fieldset>
        <legend>${personal_data}</legend>
        <table>
            <tr>
                <td class="registration">
                    <label for="name">${user_name}${colon}</label>
                </td>
                <td>
                    <input id="name" name="name" type="text" pattern="(^[a-zA-Z-]{3,}$)|(^[А-Яа-я-]{3,}$)" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="surname">${user_surname}${colon}</label>
                </td>
                <td>
                    <input id="surname" name="surname" type="text" pattern="(^[a-zA-Z-]{3,}$)|(^[А-Яа-я-]{3,}$)"
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="phone">${phone}${colon}</label>
                </td>
                <td>
                    <input id="phone" name="phone" type="text" placeholder="(29)123-45-67"
                           pattern="^\(((29)|(33)|(44)|(25))\)[0-9]{3}-[0-9]{2}-[0-9]{2}(\s){0,}" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email">${email}${colon}</label>
                </td>
                <td>
                    <input id="email" name="email" type="email" placeholder="email@example.com"
                           pattern="([^\r\n\t\f\v\@]){2,}\@{1}[\w-]{2,}\.{1}[a-z]{2,}" required/>
                </td>
            </tr>
        </table>
    </fieldset>
    <br/>
    <input type="submit" value="${submit}"/>
    <input type="reset" value="${reset}"/>
</form>
<br/>

<form name="LoginRedirect" method="get" action="${urlPrefix}/controller">
    <input type="hidden" name="command" value="login_page">
    <span>&nbsp;&nbsp;&nbsp;</span>
    <input type="submit" value="${back_to_login}"/>
</form>
</body>
</html>

