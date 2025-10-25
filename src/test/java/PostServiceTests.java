import com.ssg.board.dto.PostDTO;
import com.ssg.board.service.PostService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Log4j2
public class PostServiceTests {

    private PostService postService;

    @BeforeEach
    public void ready() {
        this.postService = PostService.INSTANCE;
    }

    @Test
    public void testGetList() throws Exception {
        List<PostDTO> postDTOList = postService.getList();
        postDTOList.forEach(log::info);
    }

    @Test
    public void testGetDetails() throws Exception {
        long postID = 1L;
        PostDTO postDTO = postService.getDetail(postID);
        log.info(postDTO);
    }

    @Test
    public void testWrite() throws Exception {
        PostDTO newPost = PostDTO.builder()
                .title("Spring 연습")
                .content("Spring")
                .writer("tester")
                .passphrase("12345")
                .build();
        log.info("{}번째 todo 추가", postService.write(newPost));
    }

    @Test
    public void testEdit() throws Exception {
        long postID = 3L;
        String passPhrase = "12345";

        PostDTO updatedPost = PostDTO.builder()
                .postId(postID)
                .title("Update Post Title...")
                .content("Update Post Content...")
                .updatedAt(LocalDateTime.now())
                .build();

        postService.edit(updatedPost, passPhrase);
    }

    @Test
    public void testRemove() throws Exception {
        long postID = 3L;
        String passPhrase = "12345";
        postService.remove(postID, passPhrase);
    }
}
