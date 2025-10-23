package com.ssg.board.service;

import com.ssg.board.dao.PostDAO;
import com.ssg.board.dao.PostDAOImpl;
import com.ssg.board.dto.PostDTO;

import java.util.List;

public class PostService {

    private static class LazyHolder {
        private static final PostService INSTANCE = new PostService();
    }
    private final PostDAO dao;

    private PostService() {
        this.dao = PostDAOImpl.INSTANCE;
    }

    public static PostService getInstance() {
        return LazyHolder.INSTANCE;
    }

    public List<PostDTO> getList(int page, int size) {
        return dao.findAll(page, size);
    }

    public PostDTO getDetail(long id) {
        return null;
    }

    public long write(PostDTO post) {
        return 0;
    }

    public void edit(PostDTO post, String passphrase) {

    }

    public void remove(long id, String passphrase) {

    }
}
