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
    <title>Post Detail</title>
</head>
<body>
    <h1>Post View Page</h1>
    <c:if test="${param.result == 'error'}">
        <p>${error}</p>
    </c:if>
    <form action="edit?id=${dto.postId}">
        <div>
            <input type="text" name="id" value="${dto.postId}" readonly />
        </div>
        <div>
            <input type="text" name="title" value="${dto.title}" readonly disabled />
        </div>
        <div>
            <input type="text" name="writer" value="${dto.writer}" readonly disabled />
        </div>
        <div>
            <input type="text" name="content" value="${dto.content}" readonly disabled />
        </div>
        <div>
            <input type="datetime-local" name="createdAt" value="${dto.createdAt}" readonly disabled>
        </div>
        <div>
            <button type="submit" formmethod="get">수정/삭제</button>
        </div>
    </form>
</body>
</html>
