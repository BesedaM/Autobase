<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.05.2019
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>


<c:set var="urlPrefix" value="${pageContext.request.contextPath}"/>
<c:set var="locale" value="${sessionScope.locale_file}" scope="session"/>
<c:set var="lang" value="${sessionScope.language}" scope="page"/>

<fmt:bundle basename="${locale}" prefix="login.">
    <fmt:message key="colon" var="colon"/>
    <fmt:message key="title" var="title"/>
    <fmt:message key="prompt" var="prompt"/>
    <fmt:message key="login" var="login"/>
    <fmt:message key="password" var="password"/>
    <fmt:message key="log_in" var="log_in"/>
    <fmt:message key="whether_registered" var="whether_registered"/>
    <fmt:message key="register" var="register"/>
</fmt:bundle>

<fmt:bundle basename="${locale}" prefix="error.">
    <fmt:message key="incorrect_login_or_password" var="incorrect_login_or_password"/>
</fmt:bundle>

<html lang="${lang}">
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<@spring.url '/css/styles.css'/>" type="text/css"/>
</head>
<body>

<%@include file="header/non_registered_user.jsp" %>

<form name="LoginForm" method="post" action="${urlPrefix}/first_page">

    <p>${prompt}</p>
    <table>
        <tr>
            <td class="login">
                <label for="login">${login}${colon}</label>
            </td>
            <td>
                <input id="login" name="login" type="text" required/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">${password}${colon}</label>
            </td>
            <td>
                <input id="password" name="password" type="password" required/>
            </td>
        </tr>
        <c:if test="${errorMessage!=null}">
            <tr>
                <td colspan="2">
                    <p id="login_error-message">${incorrect_login_or_password}</p>
                </td>
            </tr>
        </c:if>
    </table>
    <input type="submit" value="${log_in}"/>
</form>
<br/>
<p>${whether_registered}</p>


<form name="CustomerRegisterRedirect" method="get" action="${urlPrefix}/register/customer">
    <input type="submit" value="${register}"/>
</form>


<%@include file="footer/footer.jsp" %>

</body>
</html>
