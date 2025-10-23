package com.ssg.board.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "postDeleteServlet", urlPatterns = "/posts/delete")
public class PostDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/posts/delete doPost() 메서드 호출: 지정한 게시글 비밀번호 확인 후 삭제");
        super.doPost(req, resp);
    }
}
