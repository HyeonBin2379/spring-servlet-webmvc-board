package com.ssg.board.service;

import com.ssg.board.dao.PostDAO;
import com.ssg.board.dao.PostDAOImpl;
import com.ssg.board.domain.PostVO;
import com.ssg.board.dto.PostDTO;
import com.ssg.board.util.MapperUtil;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum PostService {

    INSTANCE;

    private final PostDAO dao;
    private final ModelMapper modelMapper;

    PostService() {
        this.dao = PostDAOImpl.INSTANCE;
        this.modelMapper = MapperUtil.INSTANCE.get();
    }

    public List<PostDTO> getList() {
        List<PostVO> postList = dao.findAll();
        return postList.stream()
                .map(postVO -> modelMapper.map(postVO, PostDTO.class))
                .collect(Collectors.toList());
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
