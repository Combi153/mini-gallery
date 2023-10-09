package huchu.post.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import huchu.post.application.PostServiceFacade;
import huchu.post.dto.PostResponse;
import huchu.post.dto.PostSaveRequest;
import huchu.post.dto.PostSaveResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostServiceFacade postServiceFacade;

    @PostMapping(value = "/posts", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PostSaveResponse> save(
            @RequestPart(value = "dto") PostSaveRequest postSaveRequest,
            @RequestPart(value = "file") MultipartFile file
    ) {
        PostSaveResponse response = postServiceFacade.save(postSaveRequest, file);
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/posts")
    public List<PostResponse> readAll() {
        return postServiceFacade.readAll();
    }
}
