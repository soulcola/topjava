<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vizuser
  Date: 2024-06-12
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h3><a href="./" target="_blank">Home</a></h3>
<hr>
<form action="./meals" method="post">
    Meal ID : <label>
    <input type="text" readonly="readonly" name="id"
           value="<c:out value="${requestScope.meal.id}" />"/>
</label> <br/><br/>

    DateTime: <label for="date"></label><input type="datetime-local" id="date" name="date"
<c:if test="${requestScope.meal != null}"> value="${requestScope.meal.dateTime}" </c:if>>
    <br><br>

    Description: <label for="desc"></label><input type="text" id="desc" name="desc"
<c:if test="${requestScope.meal != null}"> value="${requestScope.meal.description}"</c:if>>
    <br><br>

    Calories: <label for="cal"></label><input type="number" id="cal" name="cal"
<c:if test="${requestScope.meal != null}"> value="${requestScope.meal.calories}" </c:if>><br><br>

    <input type="submit"
           value='<c:out value="${not empty requestScope.meal ? 'Update' : 'Create'}"/>'
    />
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>

</body>
</html>
