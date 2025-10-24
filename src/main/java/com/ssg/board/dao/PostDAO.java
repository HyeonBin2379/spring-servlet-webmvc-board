package com.ssg.board.dao;

import com.ssg.board.domain.PostVO;
import com.ssg.board.dto.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostDAO {

    //
    List<PostVO> findAll();
    Optional<PostVO> findById(long id);
    long save(PostVO post);
    boolean  update(PostVO post);
    boolean  delete(long id);
    boolean checkPassphrase(long id, String passphrase);
}
