<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.05.2019
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error page</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} failed<br/>
Servlet name: ${pageContext.errorData.servletName}<br/>
Status code: ${pageContext.errorData.statusCode}<br/>
Exception: ${pageContext.exception}<br/>
Message from exception: ${pageContext.exception.message}
</body>
</html>
