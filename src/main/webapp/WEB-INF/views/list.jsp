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
    <title>Post List</title>
</head>
<body>
    <h1>Post List Page</h1>
    <ul>
        <c:forEach var="dto" items="${dtoList}">
            <li>
                ${dto.postId}
                <a href="posts/view?id=${dto.postId}">${dto.title}</a>
                ${dto.writer}
                ${dto.createdAt}
            </li>
        </c:forEach>
    </ul>
    <button type="button" onclick="location.href='posts/new'">글쓰기</button>
</body>
</html>
