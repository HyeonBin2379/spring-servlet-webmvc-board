import com.ssg.board.dao.PostDAO;
import com.ssg.board.dao.PostDAOImpl;
import com.ssg.board.domain.PostVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
