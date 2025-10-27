<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2025-10-23
  Time: 오후 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Post Form</title>
    </head>
    <body>
        <h1>Post Form Page</h1>
        <form method="post">
            <fieldset>
                <legend>${formActionType == 'writeForm' ? '게시글 등록' : '게시글 수정'}</legend>
                <div>
                    <c:if test="${formActionType == 'editForm'}">
                        <input type="text" name="id" value="${dto.postId}" readonly/>
                    </c:if>
                </div>
                <div>
                    <c:if test="${formActionType == 'writeForm'}">
                        <input type="text" name="title" placeholder="제목" required>
                    </c:if>
                    <c:if test="${formActionType == 'editForm'}">
                        <input type="text" name="title" value="${dto.title}" placeholder="제목" required>
                    </c:if>
                    <p>제목은 최소 2글자, 최대 200글자까지 입력 가능합니다.</p>
                </div>
                <div>
                    <c:if test="${formActionType == 'writeForm'}">
                        <input type="text" name="writer" placeholder="작성자" required>
                    </c:if>
                    <c:if test="${formActionType == 'editForm'}">
                        <input type="text" name="writer" value="${dto.writer}" placeholder="작성자" readonly required>
                    </c:if>
                    <p>작성자명은 최소 1글자, 최대 50글자까지 입력 가능합니다.</p>
                </div>
                <div>
                    <c:if test="${formActionType == 'writeForm'}">
                        <input type="text" name="content" placeholder="내용">
                    </c:if>
                    <c:if test="${formActionType == 'editForm'}">
                        <input type="text" name="content" value="${dto.content}" placeholder="내용">
                    </c:if>
                    <p>게시글 내용은 최소 5글자 이상이어야 합니다.</p>
                </div>
                <div>
                    <input type="password" name="passPhrase" placeholder="게시글 비밀번호" autocomplete="false" required>
                    <p>게시글 비밀번호는 최소 4글자, 최대 20글자 이상이어야 합니다.</p>
                </div>
            </fieldset>
            <fieldset>
                <div>
                    <c:if test="${formActionType == 'writeForm'}">
                        <button type="reset">초기화</button>
                        <button type="submit" formaction="save">작성</button>
                    </c:if>
                    <c:if test="${formActionType == 'editForm'}">
                        <button type="submit" formaction="update">수정</button>
                        <button type="submit" formaction="delete">삭제</button>
                    </c:if>
                </div>
            </fieldset>
        </form>
        <c:if test="${formActionType == 'writeForm'}">
            <button onclick="location.href='../posts'">이전으로</button>
        </c:if>
        <c:if test="${formActionType == 'editForm'}">
            <button onclick="location.href='../posts/view?id=${dto.postId}'">이전으로</button>
        </c:if>
    </body>
</html>
