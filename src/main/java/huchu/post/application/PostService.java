package huchu.post.application;

import huchu.post.domain.Post;
import huchu.post.domain.PostRepository;
import huchu.post.dto.PostResponse;
import huchu.post.dto.PostSaveResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostSaveResponse save(String fileName, String uploadedUrl) {
        Post post = postRepository.save(new Post(fileName, uploadedUrl));
        return new PostSaveResponse(post.id());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> readAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::from)
                .toList();
    }
}
