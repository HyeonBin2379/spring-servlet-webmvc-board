package com.ssg.board.dao;

import com.ssg.board.domain.PostVO;
import com.ssg.board.util.DBUtil;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public enum PostDAOImpl implements PostDAO {

    INSTANCE;

    @Override
    public List<PostVO> findAll() {
        String sql = "select * from board_post";
        List<PostVO> postList = new ArrayList<>();
        try (Connection connection = DBUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PostVO postVO = PostVO.builder()
                        .postId(rs.getLong("post_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .writer(rs.getString("writer"))
                        .passphrase(rs.getString("passphrase"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .build();
                postList.add(postVO);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return postList;
    }

    @Override
    public Optional<PostVO> findById(long id) {
        String sql = "select * from board_post where post_id = ?";
        PostVO postVO = null;
        try (Connection connection = DBUtil.INSTANCE.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                postVO = PostVO.builder()
                        .postId(rs.getLong("post_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .writer(rs.getString("writer"))
                        .passphrase(rs.getString("passphrase"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                        .build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(postVO);
    }

    @Override
    public long save(PostVO post) {
        String sql = "insert into board_post values(?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBUtil.INSTANCE.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, post.getPostId());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());
            pstmt.setString(4, post.getWriter());
            pstmt.setString(5, post.getPassphrase());
            pstmt.setTimestamp(6, Timestamp.valueOf(post.getCreatedAt()));
            pstmt.setTimestamp(7, Timestamp.valueOf(post.getUpdatedAt()));

            int affected = pstmt.executeUpdate();
            return affected > 0 ? post.getPostId() : -1;
        } catch (Exception e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    @Override
    public boolean update(PostVO post) {
        String sql = "update board_post set title = ?, content = ?, updated_at = now() where post_id = ?";
        try (Connection connection = DBUtil.INSTANCE.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setLong(3, post.getPostId());

            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from board_post where post_id = ?";
        try (Connection connection = DBUtil.INSTANCE.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean checkPassphrase(long id, String passphrase) {
        String sql = "select * from board_post where post_id = ? and passphrase = ?";
        try (Connection connection = DBUtil.INSTANCE.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            pstmt.setLong(1, id);
            pstmt.setString(2, passphrase);

            return rs != null && rs.next();
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
