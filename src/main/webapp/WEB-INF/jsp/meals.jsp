<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<section>
    <h3><a href="${pageContext.request.contextPath}"><spring:message code="app.home"/></a></h3>
    <hr/>
    <h2><spring:message code="meal.title"/></h2>
    <form method="get" action="meals/filter">
        <dl>
            <dt><spring:message code="filter.fromdate"/></dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="filter.todate"/></dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="filter.fromtime"/></dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="filter.totime"/></dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="filter"/></button>
    </form>
    <hr/>
    <a href="${pageContext.request.contextPath}/meals/create"><spring:message code="meal.add"/></a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="meal.date"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr data-meal-excess="${meal.excess}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="${pageContext.request.contextPath}/meals/${meal.id}/update"><spring:message code="action.update"/></a></td>
                <td><a href="${pageContext.request.contextPath}/meals/delete?id=${meal.id}"><spring:message code="action.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>