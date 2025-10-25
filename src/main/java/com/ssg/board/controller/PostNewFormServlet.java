package com.ssg.board.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "postNewFormServlet", urlPatterns = "/posts/new")
public class PostNewFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/posts/new doGet() 메서드 호출: 게시글 작성 폼 출력(제목/작성자/내용)");

        req.setAttribute("formActionType", "writeForm");
        req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
    }
}
