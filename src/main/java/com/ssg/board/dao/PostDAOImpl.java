package com.ssg.board.dao;

import com.ssg.board.dto.PostDTO;
import java.util.List;
import java.util.Optional;

public enum PostDAOImpl implements PostDAO {

    INSTANCE;

    @Override
    public List<PostDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public boolean countAll() {
        return false;
    }

    @Override
    public Optional<PostDTO> findById(long id) {
        return Optional.empty();
    }

    @Override
    public long save(PostDTO post) {
        return 0;
    }

    @Override
    public boolean update(PostDTO post) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean checkPassphrase(long id, String passphrase) {
        return false;
    }
}
