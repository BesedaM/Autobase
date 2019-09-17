<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.08.2019
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tr>
    <td>${route.id}</td>
    <td colspan="3">${route.name}</td>
</tr>
<c:forEach var="task" items="${route.getTasksList()}">
    <tr>
        <td></td>
        <td><fmt:formatDate type="both" value="${task.time.getTime()}"
                            pattern="dd-MM-yyyy HH:mm"/></td>
        <td>${task.address}</td>
        <td>${task.details}</td>
    </tr>
</c:forEach>



<%--we have 4 columns--%>