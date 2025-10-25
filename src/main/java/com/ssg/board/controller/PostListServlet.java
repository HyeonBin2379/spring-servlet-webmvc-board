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

@WebServlet(name = "postListServlet", urlPatterns = "/posts")
@Log4j2
public class PostListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/posts doGet() 메서드 호출: 최신순 목록 조회");

        List<PostDTO> postDTOList = PostService.INSTANCE.getList();

        req.setCharacterEncoding("UTF-8");
        req.setAttribute("dtoList", postDTOList);
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }
}
