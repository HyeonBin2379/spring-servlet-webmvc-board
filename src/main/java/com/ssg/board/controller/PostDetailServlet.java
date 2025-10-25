package com.ssg.board.controller;

import com.ssg.board.dto.PostDTO;
import com.ssg.board.service.PostService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "postDetailServlet", urlPatterns = "/posts/view")
@Log4j2
public class PostDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/posts/view doGet() 메서드 호출: 특정 id를 갖는 게시물 조회");

        HttpSession session = req.getSession();
        String errorMsg = (String) session.getAttribute("error");
        if (errorMsg != null) {
            log.error(errorMsg);
            req.setAttribute("error", errorMsg);
            session.removeAttribute("error");
        }

        long postID = Long.parseLong(req.getParameter("id"));
        PostDTO postDTO = PostService.INSTANCE.getDetail(postID);

        req.setAttribute("dto", postDTO);
        req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req, resp);
    }
}
