package com.ssg.board.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "postSaveServlet", urlPatterns = "/posts/save")
public class PostSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/posts/save doPost() 메서드 호출: 작성한 게시글 검증");
        super.doPost(req, resp);
    }
}
