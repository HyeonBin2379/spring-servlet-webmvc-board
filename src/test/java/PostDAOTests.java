import com.ssg.board.dao.PostDAO;
import com.ssg.board.dao.PostDAOImpl;
import com.ssg.board.domain.PostVO;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
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
        Optional<PostVO> optionalPostVO = dao.findById(1L);
        optionalPostVO.ifPresent(log::info);
    }

    @Test
    public void testSave() throws Exception {
        PostVO postDataInput = PostVO.builder()
                .title("Spring 연습")
                .content("Spring")
                .writer("tester")
                .passphrase("12345")
                .build();
        log.info(dao.save(postDataInput));
    }

    @Test
    public void testUpdate() throws Exception {
        PostVO updatedByDTO = PostVO.builder()
                .postId(3L)
                .title("Updated Todo Title...")
                .content("Updated Todo Content...")
                .updatedAt(LocalDateTime.now())
                .build();
        log.info(dao.update(updatedByDTO));
    }

    @Test
    public void testDelete() throws Exception {
        log.info(dao.delete(3L));
    }
}
