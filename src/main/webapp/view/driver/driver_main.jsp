<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.07.2019
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="urlPrefix" value="${pageContext.request.contextPath}" />
<c:set var="locale" value="${sessionScope.locale_file}" scope="session" />
<c:set var="lang" value="${sessionScope.language}" scope="page" />

<c:set var="route_status_list" value="${sessionScope.route_status}"
	scope="session" />
<c:set var="car_state_list" value="${sessionScope.car_state}"
	scope="session" />

<c:set var="route_list" value="${sessionScope.route_list}"
	scope="request" />
<c:set var="driver" value="${sessionScope.user}" scope="session" />
<c:set var="car" value="${sessionScope.car}" scope="session" />
<c:set var="car_type" value="${sessionScope.car_type}" scope="session" />

<c:forEach items="${route_list}" var="route">
	<c:if test="${route.status=='на выполнении'}">
		<c:set var="active_route" value="${route}" scope="request" />
	</c:if>
</c:forEach>

<fmt:bundle basename="${locale}" prefix="driver.">
	<fmt:message key="personal_data_header" var="personal_data" />
	<fmt:message key="routes_header" var="routes_header" />
	<fmt:message key="route_status" var="route_status" />
	<fmt:message key="future_routes" var="future_routes" />

	<fmt:message key="colon" var="colon" />
	<fmt:message key="car_state" var="car_state" />
	<fmt:message key="car_number" var="number" />
	<fmt:message key="phone_number" var="phone" />
	<fmt:message key="no_current_route" var="no_current_route_message" />
	<fmt:message key="active_route" var="active_route_header" />
	<fmt:message key="no_planned_routes" var="no_planned_routes" />
	<fmt:message key="submit" var="submit" />
</fmt:bundle>


<html lang="${lang}">
<head>
<title>Driver main</title>
<link rel="stylesheet" type="text/css"
	href="${urlPrefix}/css/styles.css">
</head>
<body>
	<%@include file="../header/registered_user.jsp"%>

	<table class="main">
		<tr>
			<th>${routes_header}</th>
			<th>${personal_data}</th>
		</tr>
		<tr>
			<td>
				<table>
					<c:if test="${active_route!=null}">
						<tr>
							<td colspan="4" class="header">${active_route_header}</td>
						</tr>
						<c:set var="route" value="${active_route}" scope="request" />
						<%@include file="../route/route_info.jsp"%>
						<tr>
							<td></td>
							<td colspan="3">
								<form name="RouteStatusCarStateChanger" method="post"
									action="${urlPrefix}/controller" class="right_side">
									<input type="hidden" name="command"
										value="change_route_status_car_state" /> <input type="hidden"
										name="car_id" value="${car.id}" /> <input type="hidden"
										name="current_page" value="${pageContext.request.requestURI}" />
									<input type="hidden" name="route_id" value="${active_route.id}" />
									<label>${route_status}${colon} <select
										name="route_status_changer" required>
											<c:forEach var="item" items="${route_status_list}">
												<c:if
													test="${item.value!='новый'&&item.value!='запланирован'}">
													<option value="${item.value}"
														<c:if test="${active_route.status==item.value}">selected</c:if>>${item.value}</option>
												</c:if>
											</c:forEach>
									</select>
									</label> <br />
									<br /> <label>${car_state}${colon} <select
										name="car_state_changer">
											<c:forEach var="item" items="${car_state_list}">
												<option value="${item.value}">${item.value}</option>
											</c:forEach>
									</select>
									</label> <br />
									<br /> <input type="submit" value="${submit}" />
								</form>
							</td>
						</tr>
					</c:if>
					<tr>
					<c:if test="${active_route==null}">
						<p class="information">${no_current_route_message}</p>
					</c:if>

					</tr>

					<tr>
						<td colspan="4"><br /></td>
					</tr>
					<tr>
						<td colspan="4" class="header">${future_routes}</td>
					</tr>

					<tr>
						<td colspan="4" class="information">${no_planned_routes}</td>
					</tr>

					<c:forEach items="${route_list}" var="route">
						<c:if test="${route.status=='запланирован'}">

							<%@include file="../route/route_info.jsp"%>
							<tr>
								<td></td>
								<td colspan="3"><c:if test="${active_route==null}">
										<form name="RouteStatusChanger" method="post"
											action="${urlPrefix}/controller" onchange="submit()"
											class="right_side">
											<input type="hidden" name="command"
												value="change_route_status" /> <input type="hidden"
												name="current_page"
												value="${pageContext.request.requestURI}" /> <input
												type="hidden" name="route_id" value="${route.id}" /> <label>${route_status}${colon}
												<select name="route_status_changer" required>
													<c:forEach var="item" items="${route_status_list}">
														<c:if
															test="${item.value!='новый'&&item.value!='выполнен'}">
															<option value="${item.value}"
																<c:if test="${route.status==item.value}">selected</c:if>>${item.value}</option>
														</c:if>
													</c:forEach>
											</select>
											</label>
										</form>
									</c:if></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>

			</td>
			<td class="personal_data">${driver.surname} ${driver.name}<br />
				${phone}${colon} ${driver.phone}<br />
			<br /> ${car.model}<br /> ${number}${colon} ${car.number}
			</td>
		</tr>
	</table>

</body>
</html>
