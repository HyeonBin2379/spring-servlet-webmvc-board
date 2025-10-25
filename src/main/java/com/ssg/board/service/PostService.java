package com.ssg.board.service;

import com.ssg.board.dao.PostDAO;
import com.ssg.board.dao.PostDAOImpl;
import com.ssg.board.domain.PostVO;
import com.ssg.board.dto.PostDTO;
import com.ssg.board.util.MapperUtil;
import java.util.Comparator;
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
                .sorted(Comparator.comparing(PostDTO::getPostId).reversed())
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

    public void edit(PostDTO post, String passphrase) throws IllegalArgumentException {
        if (!dao.checkPassphrase(post.getPostId(), passphrase)) {
            throw new IllegalArgumentException("입력한 비밀번호가 게시물의 비밀번호와 일치하지 않습니다.");
        }
        Optional<PostVO> optionalPostVO = dao.findById(post.getPostId());
        optionalPostVO.ifPresent(originalPost -> {
            // DTO를 사용하여 VO 객체의 멤버 필드 갱신
            modelMapper.map(post, originalPost);
            log.info(dao.update(originalPost) ? "포스트 변경 완료" : "포스트 변경 실패");
        });
    }

    public void remove(long id, String passphrase) throws IllegalArgumentException {
        if (!dao.checkPassphrase(id, passphrase)) {
            throw new IllegalArgumentException("입력한 비밀번호가 게시물의 비밀번호와 일치하지 않습니다.");
        }
        log.info(dao.delete(id) ? "포스트 삭제 완료" : "포스트 삭제 실패");
    }
}
