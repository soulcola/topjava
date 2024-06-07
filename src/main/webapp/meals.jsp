<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.06.2024
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h2>HTML Table</h2>
<table>
    <tr>
        <th>Company</th>
        <th>Contact</th>
        <th>Country</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.meals}">
        <c:if test="${meal.excess}">
            <tr style="color:red">
        </c:if>
        <td>${meal.dateTime}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="/meals/" + ${meal.id}>update</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
