package com.ssg.board.controller;

import com.ssg.board.dto.PostDTO;
import com.ssg.board.service.PostService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "postSaveServlet", urlPatterns = "/posts/save")
@Log4j2
public class PostSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/posts/save doPost() 메서드 호출: 작성한 게시글 검증");
        req.setCharacterEncoding("UTF-8");

        PostDTO postDTO = PostDTO.builder()
                .title(req.getParameter("title"))
                .writer(req.getParameter("writer"))
                .content(req.getParameter("content"))
                .passphrase(req.getParameter("passPhrase"))
                .build();

        Map<String, String> errors = validate(postDTO);
        try {
            if (!errors.isEmpty()) {
                throw new IllegalArgumentException("등록할 게시글 데이터가 유효하지 않습니다.");
            }
            PostService.INSTANCE.write(postDTO);
            resp.sendRedirect("/posts");
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            errors.put("saveError", e.getMessage());
            req.setAttribute("errorMap", errors);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }

    private Map<String, String> validate(PostDTO written) {
        Map<String, String> errors = new HashMap<>();

        if (!written.getTitle().trim().matches(".{2,200}")) {
            errors.put("title", "게시글 제목의 길이가 유효하지 않습니다.");
            log.error(errors.get("title"));
        }
        if (!written.getWriter().trim().matches(".{1,50}")) {
            errors.put("writer", "작성자의 길이가 유효하지 않습니다.");
            log.error(errors.get("writer"));
        }
        if (!written.getContent().trim().matches(".{5,}")) {
            errors.put("content", "내용의 길이가 유효하지 않습니다.");
            log.error(errors.get("content"));
        }
        if (!written.getPassphrase().matches(".{4,20}")) {
            errors.put("passPhrase", "비밀번호의 길이가 유효하지 않습니다.");
            log.error(errors.get("passPhrase"));
        }

        return errors;
    }
}
