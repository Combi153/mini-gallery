package huchu.post.application;

import huchu.file.application.S3Service;
import huchu.post.dto.PostResponse;
import huchu.post.dto.PostSaveRequest;
import huchu.post.dto.PostSaveResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class PostServiceFacade {

    private final S3Service s3Service;
    private final PostService postService;
    private final PostUploader postUploader;

    public PostSaveResponse save(PostSaveRequest postSaveRequest, MultipartFile file) {
        String fileName = postSaveRequest.fileName();

        String uploadedUrl = postUploader.upload(fileName, file);
        return postService.save(fileName, uploadedUrl);
    }

    public List<PostResponse> readAll() {
        return postService.readAll();
    }
}
