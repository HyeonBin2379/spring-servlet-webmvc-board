package com.ssg.board.controller;

import com.ssg.board.dto.PostDTO;
import com.ssg.board.service.PostService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "postUpdateServlet", urlPatterns = "/posts/update")
@Log4j2
public class PostUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/posts/update doPost() 메서드 호출: 비밀번호 확인 후 업데이트");
        req.setCharacterEncoding("UTF-8");

        long postID = Long.parseLong(req.getParameter("id"));
        String passPhrase = req.getParameter("passPhrase");
        PostDTO updatedPost = PostDTO.builder()
                .postId(postID)
                .title(req.getParameter("title"))
                .content(req.getParameter("content"))
                .updatedAt(LocalDateTime.now())
                .build();

        Map<String, String> errors = validate(updatedPost);
        try {
            if (!errors.isEmpty()) {
                throw new IllegalArgumentException("수정할 게시글 데이터가 유효하지 않습니다.");
            }
            PostService.INSTANCE.edit(updatedPost, passPhrase);
            resp.sendRedirect("/posts/view?id=" + postID);
        } catch (IllegalArgumentException e) {
            errors.put("updateError", e.getMessage());
            req.setAttribute("errorMap", errors);
            req.setAttribute("id", postID);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    private Map<String, String> validate(PostDTO written) {
        Map<String, String> errors = new HashMap<>();

        if (!written.getTitle().trim().matches(".{2,200}")) {
            errors.put("title", "게시글 제목의 길이가 유효하지 않습니다.");
            log.error(errors.get("title"));
        }
        if (!written.getContent().trim().matches(".{5,}")) {
            errors.put("content", "내용의 길이가 유효하지 않습니다.");
            log.error(errors.get("content"));
        }

        return errors;
    }
}
