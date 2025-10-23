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
    <title>Post Form</title>
</head>
<body>
    <h1>Post Form Page</h1>
    <form method="post">
        <button type="submit" formaction="save">작성</button>
        <button type="submit" formaction="update">수정</button>
        <button type="submit" formaction="delete">삭제</button>
    </form>
</body>
</html>
