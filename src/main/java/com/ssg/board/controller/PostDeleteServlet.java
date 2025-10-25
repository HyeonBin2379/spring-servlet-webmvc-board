package com.ssg.board.controller;

import com.ssg.board.service.PostService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "postDeleteServlet", urlPatterns = "/posts/delete")
@Log4j2
public class PostDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/posts/delete doPost() 메서드 호출: 지정한 게시글 비밀번호 확인 후 삭제");
        long postID = Long.parseLong(req.getParameter("id"));
        String passPhrase = req.getParameter("passPhrase");

        try {
            PostService.INSTANCE.remove(postID, passPhrase);
            resp.sendRedirect("/posts");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("error", "deleteError : " + e.getMessage());
            resp.sendRedirect("/posts/view?id=" + postID +"&result=error");
        }
    }
}
