package com.ssg.board.controller;

import com.ssg.board.dto.PostDTO;
import com.ssg.board.service.PostService;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "postEditFormServlet", urlPatterns = "/posts/edit")
@Log4j2
public class PostEditFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/posts/edit doGet() 메서드 호출: 기존 값에 바인딩된 폼 출력");

        long postID = Long.parseLong(req.getParameter("id"));
        PostDTO targetPost = PostService.INSTANCE.getDetail(postID);

        req.setAttribute("formActionType", "editForm");
        req.setAttribute("dto", targetPost);

        req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
    }
}
