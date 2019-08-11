<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.07.2019
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route table</title>
</head>
<body>
<table>
    <tr>
        <td>${route.id}</td>
        <td colspan="2">${route.name}</td>
    </tr>
    <tr>
        <td></td>
        <td colspan="2">
            <form name="RouteStatusChanger" method="post" action="${urlPrefix}/controller"
                  onchange="submit()">
                <input type="hidden" name="command" value="change_route_status"/>
                <input type="hidden" name="current_page" value="${pageContext.request.requestURI}"/>
                <input type="hidden" name="route" value="${route}"/>
                <label>Route status: <select name="route_status_changer" required>
                    <c:forEach var="item" items="${route_status_list}">
                        <c:if test="${item!='новый'}">
                            <option value="${item}"
                                    <c:if test="${active_route.status==item}">selected</c:if>
                            >${item}</option>
                        </c:if>
                    </c:forEach>
                </select>
                </label>
            </form>
        </td>
    </tr>
    <c:forEach var="task" items="${route.tasks}">
        <tr>
            <td></td>
            <td class="task">
                <fmt:formatDate value="${task.time}"
                                pattern="dd-MM-yyyy HH:mm"/> ${task.address}<br/>
                    ${task.details}
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
