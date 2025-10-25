package com.ssg.board.service;

import com.ssg.board.dao.PostDAO;
import com.ssg.board.dao.PostDAOImpl;
import com.ssg.board.domain.PostVO;
import com.ssg.board.dto.PostDTO;
import com.ssg.board.util.MapperUtil;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
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
        Optional<PostVO> optionalPostVO = dao.findById(id);
        return optionalPostVO
                .map(postVO -> modelMapper.map(postVO, PostDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트를 찾을 수 없습니다."));
    }

    public long write(PostDTO post) {
        PostVO postVO = modelMapper.map(post, PostVO.class);
        return dao.save(postVO);
    }

    public void edit(PostDTO post, String passphrase) {
        if (dao.checkPassphrase(post.getPostId(), passphrase)) {
            Optional<PostVO> optionalPostVO = dao.findById(post.getPostId());
            optionalPostVO.ifPresent(originalPost -> {
                // DTO를 사용하여 VO 객체의 멤버 필드 갱신
                modelMapper.map(post, originalPost);
                log.info(dao.update(originalPost) ? "board_post 변경 완료" : "board_post 변경 실패");
            });
        }
    }

    public void remove(long id, String passphrase) {
        if (dao.checkPassphrase(id, passphrase)) {
            log.info(dao.delete(id) ? "포스트 삭제 완료" : "포스트 삭제 실패");
        }
    }
}
