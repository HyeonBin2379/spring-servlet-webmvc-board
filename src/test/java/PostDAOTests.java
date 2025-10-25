import com.ssg.board.dao.PostDAO;
import com.ssg.board.dao.PostDAOImpl;
import com.ssg.board.domain.PostVO;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

@Log4j2
public class PostDAOTests {

    private PostDAO dao;

    @BeforeEach
    public void ready() {
        dao = PostDAOImpl.INSTANCE;
    }

    @Test
    public void testFindAll() throws Exception {
        List<PostVO> postList = dao.findAll();
        postList.forEach(log::info);
    }

    @Test
    public void testFindById() throws Exception {
        long postID = 1L;
        Optional<PostVO> optionalPostVO = dao.findById(postID);
        optionalPostVO.ifPresent(postVO -> {
            log.info(postVO);
            Assertions.assertEquals(postID, optionalPostVO.get().getPostId());
        });
    }

    @Test
    public void testFindById2() throws Exception {
        long postID = 0L;
        Optional<PostVO> optionalPostVO = dao.findById(postID);
        Assertions.assertFalse(optionalPostVO.isPresent());
    }

    @Test
    public void testSave() throws Exception {
        PostVO postDataInput = PostVO.builder()
                .title("post title test...")
                .content("test...")
                .writer("tester")
                .passphrase("12345")
                .build();
        Assertions.assertTrue(dao.save(postDataInput) != -1);
    }

    @Test
    public void testUpdate() throws Exception {
        long postID = 3L;
        PostVO updatedPostByDTO = PostVO.builder()
                .postId(postID)
                .title("Update Post Title...")
                .content("Update Post Content...")
                .updatedAt(LocalDateTime.now())
                .build();
        Assertions.assertTrue(dao.update(updatedPostByDTO));
    }

    @Test
    public void testDelete() throws Exception {
        long postID = 3L;
        Assertions.assertTrue(dao.delete(postID));
    }
}
