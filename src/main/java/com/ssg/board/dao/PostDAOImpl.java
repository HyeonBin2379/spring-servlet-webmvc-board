package com.ssg.board.dao;

import com.ssg.board.dto.PostDTO;
import com.ssg.board.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public enum PostDAOImpl implements PostDAO {

    INSTANCE;

    @Override
    public List<PostDTO> findAll(int page, int size) {
        String sql = "select * from board limit ?";
        try (Connection connection = DBUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
