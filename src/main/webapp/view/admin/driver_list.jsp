<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.08.2019
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="urlPrefix" value="${pageContext.request.contextPath}" />

<fmt:bundle basename="${locale}" prefix="admin.">
    <fmt:message key="admin_main_redirect" var="admin_main_redirect"/>
</fmt:bundle>

<html>
<head>
<title>Title</title>
</head>
<body>
	Not supported!!!
	<form name="AdminMainRedirect" method="post"
		action="${urlPrefix}/controller">
		<input type="hidden" name="command" value="admin_main_redirect" /> <input
			type="submit" value="${admin_main_redirect}" />
	</form>
	<%@include file="../footer/footer.jsp" %>
</body>
</html>
