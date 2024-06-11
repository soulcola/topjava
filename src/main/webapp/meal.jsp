<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vizuser
  Date: 2024-06-12
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="./meals" method="post">
    Meal ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${meal.id}" />"/> <br/>

    <label for="date">DateTime:</label><br>
    <input type="datetime-local" id="date" name="date" <c:if test="${requestScope.meal != null}">
           value="${requestScope.meal.dateTime}}" </c:if>> <br><br>

    <label for="desc">Description:</label><br>
    <input type="text" id="desc" name="desc" <c:if test="${requestScope.meal != null}">
           value="${requestScope.meal.description}" </c:if>><br><br>

    <label for="cal">Calories:</label><br>
    <input type="number" id="cal" name="cal" <c:if test="${requestScope.meal != null}">
           value="${requestScope.meal.calories}" </c:if>><br><br>

    <input type="submit" value='<c:out value="${not empty requestScope.meal ? 'Update' : 'Create'}"/>'/>
</form>

</body>
</html>
