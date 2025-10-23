package com.ssg.board.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "postEditFormServlet", urlPatterns = "/posts/edit")
public class PostEditFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/posts/edit doGet() 메서드 호출: 기존 값에 바인딩된 폼 출력");
        req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
    }
}
