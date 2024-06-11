<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.06.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<h2>Meals</h2>
<h3>
    <a href="./meals?action=create">Create new meal</a>
</h3>
<table>
    <tr>
        <th>id</th>
        <th>Datetime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.meals}">
        <c:if test="${meal.excess}">
            <tr style="color:red">
        </c:if>
        <td>${meal.id}</td>
        <td>${requestScope.formatter.format(meal.dateTime)}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="./meals?action=update&id=${meal.id}">update</a></td>
        <td><a href="./meals?action=delete&id=${meal.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
