<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2025-10-23
  Time: 오후 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Post Error</title>
</head>
<body>
    <h1>Post Error Page</h1>
    <c:forEach var="entry" items="${errorMap.entrySet()}">
        <p>${entry.getKey()} : ${entry.getValue()}</p>
    </c:forEach>
    <c:if test="${errorMap.containsKey('saveError')}">
        <button onclick="location.href='../posts'">뒤로 가기</button>
    </c:if>
    <c:if test="${errorMap.containsKey('updateError')}">
        <button onclick="location.href='../posts/view?id=${id}'">뒤로 가기</button>
    </c:if>
</body>
</html>
