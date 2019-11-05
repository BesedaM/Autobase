<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="urlPrefix" value="${pageContext.request.contextPath}" />
<c:set var="locale" value="${sessionScope.locale_file}" scope="session" />
<c:set var="lang" value="${sessionScope.language}" scope="page" />

<fmt:bundle basename="${locale}" prefix="build.">
	<fmt:message key="timestamp" var="timestamp" />
</fmt:bundle>

<html lang="${lang}">
<head>
<meta charset="UTF-8">
<title></title>
    <link rel="stylesheet" href="../../css/styles.css" type="text/css"/>
</head>
<body>
	<p></p>
	<p class="information">${timestamp}</p>
</body>
</html>