<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>ID</td>
        <td>NAME</td>
    </tr>
    <c:forEach items="${list}" var="document" varStatus="status">
        <tr>
            <td>${document.id}</td>
            <td>${document.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
