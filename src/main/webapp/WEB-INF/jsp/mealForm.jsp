<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<section>

    <h3><a href="${pageContext.request.contextPath}"><spring:message code="app.home"/></a></h3>
    <hr>
    <h2>
        <c:choose>
            <c:when test="${action eq 'create'}">
                <spring:message code='meal.add'/>
            </c:when>
            <c:otherwise>
                <spring:message code='meal.update'/>
            </c:otherwise>
        </c:choose>
    </h2>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="${pageContext.request.contextPath}/meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt><spring:message code='meal.date'/></dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt><spring:message code='meal.description'/></dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt><spring:message code='meal.calories'/></dt>
            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
        </dl>
        <button type="submit"><spring:message code='button.save'/></button>
        <button onclick="window.history.back()" type="button"><spring:message code='button.cancel'/></button>
    </form>
</section>
</body>
</html>